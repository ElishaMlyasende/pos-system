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
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalesImpl implements SalesService {
    private final SalesRepository salesRepository;
    private final ProductRepository productRepository;
    private final InvetoryRepository invetoryRepository;
    public SalesImpl(SalesRepository salesRepository,ProductRepository productRepository,InvetoryRepository invetoryRepository){
        this.salesRepository=salesRepository;
        this.productRepository=productRepository;
        this.invetoryRepository=invetoryRepository;
    }
    @Override
    @Transactional // Inahakikisha kila kitu kinafanyika ndani ya session moja
    public ApiResponse<SalesResponseDTO> addSales(SalesRequestDTO dto) {
        // 1. Pata Product na Inventory kwa query moja (Kama inawezekana)
        Product product = productRepository.findById(dto.getProduct_id())
                .orElseThrow(() -> new RuntimeException("Product haijapatikana"));

        Invetory inventory = product.getInvetory();
        if (inventory == null) {
            return ApiResponseBuilder.error("Bidhaa hii haina rekodi ya Stock/Inventory!");
        }

        // 2. Tengeneza Sales Entity
        Sales sales = SalesMapper.toEntity(dto);
        sales.setProduct(product);

        if (dto.getSalesItemDTO() != null && !dto.getSalesItemDTO().isEmpty()) {
            List<SalesItem> salesItems = new ArrayList<>();

            for (SalesItemDTO itemDto : dto.getSalesItemDTO()) {
                SalesItem item = SalesItemMapper.toEntity(itemDto);

                // Angalia Stock
                if (inventory.getQuantity() < item.getQuantity()) {
                    throw new RuntimeException("Stock haitoshi kwa " + product.getProductName());
                }

                // Punguza Stock na weka mahusiano
                inventory.setQuantity(inventory.getQuantity() - item.getQuantity());
                item.setProduct(product);
                item.setSales(sales);
                salesItems.add(item);
            }
            sales.setSalesItems(salesItems);
        }

        // 3. Save Kila kitu
        // Kwa sababu ya @Transactional na Cascading, ku-save sales kuta-update inventory pia
        Sales savedSale = salesRepository.save(sales);
        invetoryRepository.save(inventory); // Lazimisha ku-save mabadiliko ya stock

        // 4. Rudisha DTO (Hakikisha Mapper haigusi Lazy collections hapa)
        return ApiResponseBuilder.create(SalesMapper.toDTO(savedSale));
    }


    @Override
    public ApiResponse<List<SalesResponseDTO>> getAllSales() {
       List<Sales> salesList=salesRepository.findAll();
       return ApiResponseBuilder.success(SalesMapper.toListDTO(salesList));
    }

    @Override
    @Transactional
    public ApiResponse<SalesResponseDTO> updateSales(SalesRequestDTO dto, Long id) {
        Sales existingSale = salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale ID haijapatikana"));

        // 1. RUDISHA STOCK YA ZAMANI (Revert Inventory)
        if (existingSale.getSalesItems() != null) {
            for (SalesItem item : existingSale.getSalesItems()) {
                Invetory inv = item.getProduct().getInvetory();
                if (inv != null) {
                    inv.setQuantity(inv.getQuantity() + item.getQuantity());
                }
            }
        }

        // 2. ANDAA PRODUCT MPYA
        Product product = productRepository.findById(dto.getProduct_id())
                .orElseThrow(() -> new RuntimeException("Product Id not found"));
        Invetory inventory = product.getInvetory();

        if (inventory == null) {
            return ApiResponseBuilder.error("Hii bidhaa haina rekodi ya stoo");
        }

        // 3. UPDATE SALES INFO
        SalesMapper.update(existingSale,dto);
        existingSale.setProduct(product);
        // 4. CHAKATA SALES ITEMS MPYA NA KUKATA STOCK TENA
        if (dto.getSalesItemDTO() != null) {
            // Futa items za zamani (Clear old items)
            existingSale.getSalesItems().clear();

            for (SalesItemDTO itemDto : dto.getSalesItemDTO()) {
                if (inventory.getQuantity() < itemDto.getQuantity()) {
                    throw new RuntimeException("Stock haitoshi kwa " + product.getProductName());
                }

                SalesItem newItem = SalesItemMapper.toEntity(itemDto);
                newItem.setProduct(product);
                newItem.setSales(existingSale);

                // Kata stock mpya
                inventory.setQuantity(inventory.getQuantity() - itemDto.getQuantity());
                existingSale.getSalesItems().add(newItem);
            }
        }

        Sales updatedSale = salesRepository.save(existingSale);
        return ApiResponseBuilder.updated(SalesMapper.toDTO(updatedSale));
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteSales(Long id) {
        Sales sales = salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale ID haijapatikana"));

        // 1. RUDISHA STOCK KWENYE INVENTORY KABLA YA KUFUTA
        if (sales.getSalesItems() != null) {
            for (SalesItem item : sales.getSalesItems()) {
                Invetory inv = item.getProduct().getInvetory();
                if (inv != null) {
                    inv.setQuantity(inv.getQuantity() + item.getQuantity());
                }
            }
        }

        // 2. FUTA SALE
        salesRepository.delete(sales);
        return ApiResponseBuilder.deleted();
    }
}
