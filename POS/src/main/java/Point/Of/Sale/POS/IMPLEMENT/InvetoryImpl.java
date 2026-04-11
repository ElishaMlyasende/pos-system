package Point.Of.Sale.POS.IMPLEMENT;

import Point.Of.Sale.POS.DTO.InvetoryRequestDTO;
import Point.Of.Sale.POS.DTO.InvetoryResponseDTO;
import Point.Of.Sale.POS.ENTITY.Invetory;
import Point.Of.Sale.POS.ENTITY.Product;
import Point.Of.Sale.POS.MAPPER.InvetoryMapper;
import Point.Of.Sale.POS.REPOSITORY.InvetoryRepository;
import Point.Of.Sale.POS.REPOSITORY.ProductRepository;
import Point.Of.Sale.POS.RESPONSE.ApiResponse;
import Point.Of.Sale.POS.RESPONSE.ApiResponseBuilder;
import Point.Of.Sale.POS.SERVICE.InvetoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InvetoryImpl implements InvetoryService {
    private final InvetoryRepository invetoryRepository;
    private final ProductRepository productRepository;

    public InvetoryImpl(InvetoryRepository invetoryRepository, ProductRepository productRepository) {
        this.invetoryRepository = invetoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ApiResponse<InvetoryResponseDTO> add(InvetoryRequestDTO request) {
        Product idExist = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("This product id not found" + request.getProductId()));
        Invetory invetory = InvetoryMapper.toEntity(request);
        invetory.setProduct(idExist);
        invetoryRepository.save(invetory);
        return ApiResponseBuilder.create(InvetoryMapper.toDto(invetory));

    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<InvetoryResponseDTO>> listAll() {
        List<Invetory> entitList = invetoryRepository.findAll();
        return ApiResponseBuilder.success(InvetoryMapper.tolistDto(entitList));
    }

    @Override
    public ApiResponse<InvetoryResponseDTO> update(InvetoryRequestDTO dto, Long id) {
        Optional<Invetory> exist = invetoryRepository.findById(id);
        if (exist.isEmpty()) {
            return ApiResponseBuilder.error("No such Id exist " + id);
        }
        Product idExist = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("This product id not found" + dto.getProductId()));
        InvetoryMapper.update(exist.get(), dto);
        exist.get().setProduct(idExist);
        invetoryRepository.save(exist.get());
        return ApiResponseBuilder.updated(InvetoryMapper.toDto(exist.get()));

    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        // 1. Tafuta inventory pamoja na mahusiano yake
        Optional<Invetory> exist = invetoryRepository.findById(id);
        if (exist.isEmpty()) {
            return ApiResponseBuilder.error("No such Id exist " + id);
        }
        Invetory inventoryToDelete = exist.get();
        if (inventoryToDelete.getProduct() != null) {
            inventoryToDelete.getProduct().setInvetory(null);
            productRepository.save(inventoryToDelete.getProduct());
        }
        invetoryRepository.delete(inventoryToDelete);

        return ApiResponseBuilder.deleted();
    }
}