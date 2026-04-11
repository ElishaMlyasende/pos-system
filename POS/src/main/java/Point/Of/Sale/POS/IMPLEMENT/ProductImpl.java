package Point.Of.Sale.POS.IMPLEMENT;

import Point.Of.Sale.POS.DTO.ProductRequestDTO;
import Point.Of.Sale.POS.DTO.ProductResponseDto;
import Point.Of.Sale.POS.ENTITY.Category;
import Point.Of.Sale.POS.ENTITY.Product;
import Point.Of.Sale.POS.MAPPER.ProductMapper;
import Point.Of.Sale.POS.REPOSITORY.CategoryRepository;
import Point.Of.Sale.POS.REPOSITORY.ProductRepository;
import Point.Of.Sale.POS.RESPONSE.ApiResponse;
import Point.Of.Sale.POS.RESPONSE.ApiResponseBuilder;
import Point.Of.Sale.POS.SERVICE.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class ProductImpl implements ProductService {
    private  final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductImpl(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
        this.productRepository=productRepository;
    }

    @Override
    public ApiResponse<ProductResponseDto> addProduct(ProductRequestDTO request) {
        Product product= ProductMapper.toEntity(request);
        Category category=categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()->new RuntimeException("This category not found"));
        product.setCategory(category);
        productRepository.save(product);
        ProductResponseDto dto= ProductMapper.todto(product);
        return ApiResponseBuilder.create(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<ProductResponseDto>> allProducts() {
        List<Product> products=productRepository.findAll();
        List<ProductResponseDto> dto=ProductMapper.responseDtoList(products);
        return ApiResponseBuilder.success(dto);
    }

    @Override
    public ApiResponse<ProductResponseDto> update(ProductRequestDTO dto, Long id) {
        Optional<Product> product=productRepository.findById(id);
        if (product.isEmpty()){
            return ApiResponseBuilder.error("Id not found "+id);
        }
        Product product1=product.get();
        ProductMapper.update(product1,dto);
        productRepository.save(product1);
        return ApiResponseBuilder.updated(ProductMapper.todto(product1));
    }

    @Override
    public ApiResponse<Void> deleteProduct(Long id) {
        Optional<Product> product=productRepository.findById(id);
        if (product.isEmpty()){
            return ApiResponseBuilder.error("Id not found "+id);
        }
        productRepository.deleteById(id);
        return ApiResponseBuilder.deleted();
    }
}
