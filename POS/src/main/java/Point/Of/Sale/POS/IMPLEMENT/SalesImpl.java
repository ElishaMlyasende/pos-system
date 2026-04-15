package Point.Of.Sale.POS.IMPLEMENT;

import Point.Of.Sale.POS.DTO.SalesItemDTO;
import Point.Of.Sale.POS.DTO.SalesRequestDTO;
import Point.Of.Sale.POS.DTO.SalesResponseDTO;
import Point.Of.Sale.POS.ENTITY.Invetory;
import Point.Of.Sale.POS.ENTITY.Product;
import Point.Of.Sale.POS.ENTITY.Sales;
import Point.Of.Sale.POS.ENTITY.SalesItem;
import Point.Of.Sale.POS.MAPPER.SalesItemMapper;
import Point.Of.Sale.POS.MAPPER.SalesMapper;
import Point.Of.Sale.POS.REPOSITORY.InvetoryRepository;
import Point.Of.Sale.POS.REPOSITORY.ProductRepository;
import Point.Of.Sale.POS.REPOSITORY.SalesRepository;
import Point.Of.Sale.POS.RESPONSE.ApiResponse;
import Point.Of.Sale.POS.RESPONSE.ApiResponseBuilder;
import Point.Of.Sale.POS.SERVICE.SalesService;
import org.springframework.transaction.annotation.Transactional; // Tumia Spring Transactional kwa speed zaidi
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesImpl implements SalesService {
    private final SalesRepository salesRepository;
    private final ProductRepository productRepository;
    private final InvetoryRepository invetoryRepository;

    public SalesImpl(SalesRepository salesRepository, ProductRepository productRepository, InvetoryRepository invetoryRepository) {
        this.salesRepository = salesRepository;
        this.productRepository = productRepository;
        this.invetoryRepository = invetoryRepository;
    }

    @Override
    @Transactional
    public ApiResponse<SalesResponseDTO> addSales(SalesRequestDTO dto) {
        // 1. Tengeneza Sales Entity kwanza (Ondoa kutafuta product moja nje ya loop)
        Sales sales = SalesMapper.toEntity(dto);
        List<SalesItem> salesItems = new ArrayList<>();

        // 2. Hakikisha kuna bidhaa kwenye list
        if (dto.getSalesItemDTO() == null || dto.getSalesItemDTO().isEmpty()) {
            return ApiResponseBuilder.error("Kapu la mauzo lipo wazi!");
        }

        // 3. Loop kupitia kila bidhaa iliyoko kwenye list (Soda, Mkate, n.k.)
        for (SalesItemDTO itemDto : dto.getSalesItemDTO()) {
            // Pata Product halisi ya hii item kwa kutumia productId yake yenyewe
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Bidhaa ID: " + itemDto.getProductId() + " haijapatikana"));

            Invetory inventory = product.getInvetory();
            if (inventory == null) {
                throw new RuntimeException("Bidhaa '" + product.getProductName() + "' haina rekodi ya Stock!");
            }

            // Tengeneza SalesItem entity
            SalesItem item = SalesItemMapper.toEntity(itemDto);
            item.setProduct(product); // Sasa kila item inapata product yake sahihi
            item.setSales(sales);      // Unganisha na muamala huu mkuu
            salesItems.add(item);

            // 4. Punguza stock ya hii bidhaa mahususi moja kwa moja kwenye DB
            int rowsUpdated = invetoryRepository.punguzaStock(inventory.getId(), itemDto.getQuantity());

            if (rowsUpdated == 0) {
                throw new RuntimeException("Stock haitoshi kwa bidhaa: " + product.getProductName());
            }
        }

        // 5. Weka list ya items zote kwenye Sales entity
        sales.setSalesItems(salesItems);

        // 6. Save Sales (Hii ita-save Sales na SalesItems zote kwa pamoja kwa sababu ya Cascade)
        Sales savedSale = salesRepository.save(sales);

        return ApiResponseBuilder.create(SalesMapper.toDTO(savedSale));
    }


    @Override
    @Transactional(readOnly = true) // Inarahisisha kupata data bila kufunga tables
    public ApiResponse<List<SalesResponseDTO>> getAllSales() {
        // Nimetumia method iliyoboreshwa ya Repository (Join Fetch)
        List<Sales> salesList = salesRepository.findAll();
        return ApiResponseBuilder.success(SalesMapper.toListDTO(salesList));
    }

    /*@Override
    @Transactional
    public ApiResponse<SalesResponseDTO> updateSales(SalesRequestDTO dto, Long id) {
        Sales existingSale = salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale ID haijapatikana"));

        // 1. Rudisha Stock ya awali
        if (existingSale.getSalesItems() != null) {
            for (SalesItem item : existingSale.getSalesItems()) {
                Invetory inv = item.getProduct().getInvetory();
                if (inv != null) {
                    inv.setQuantity(inv.getQuantity() + item.getQuantity());
                    invetoryRepository.save(inv);
                }
            }
        }

        // 2. Andaa Product Mpya
        Product product = productRepository.findById(dto.getProduct_id())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Invetory inventory = product.getInvetory();

        SalesMapper.update(existingSale, dto);
        existingSale.setProduct(product);

        if (dto.getSalesItemDTO() != null) {
            existingSale.getSalesItems().clear();
            int newTotalQty = 0;

            for (SalesItemDTO itemDto : dto.getSalesItemDTO()) {
                newTotalQty += itemDto.getQuantity();
                SalesItem newItem = SalesItemMapper.toEntity(itemDto);
                newItem.setProduct(product);
                newItem.setSales(existingSale);
                existingSale.getSalesItems().add(newItem);
            }

            if (inventory.getQuantity() < newTotalQty) {
                throw new RuntimeException("Stock haitoshi!");
            }
            inventory.setQuantity(inventory.getQuantity() - newTotalQty);
        }

        Sales updatedSale = salesRepository.saveAndFlush(existingSale);
        invetoryRepository.saveAndFlush(inventory);

        return ApiResponseBuilder.updated(SalesMapper.toDTO(updatedSale));
    }*/

    @Override
    @Transactional
    public ApiResponse<Void> deleteSales(Long id) {
        Sales sales = salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale ID haijapatikana"));

        if (sales.getSalesItems() != null) {
            for (SalesItem item : sales.getSalesItems()) {
                Invetory inv = item.getProduct().getInvetory();
                if (inv != null) {
                    inv.setQuantity(inv.getQuantity() + item.getQuantity());
                    invetoryRepository.save(inv);
                }
            }
        }

        salesRepository.delete(sales);
        salesRepository.flush(); // Hakikisha ufutaji umetokea DB mara moja
        return ApiResponseBuilder.deleted();
    }
}
