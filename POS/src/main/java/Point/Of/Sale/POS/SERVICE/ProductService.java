package Point.Of.Sale.POS.SERVICE;

import Point.Of.Sale.POS.DTO.ProductRequestDTO;
import Point.Of.Sale.POS.DTO.ProductResponseDto;
import Point.Of.Sale.POS.RESPONSE.ApiResponse;


import java.util.List;

public interface ProductService {
    public ApiResponse<ProductResponseDto> addProduct(ProductRequestDTO request);
    public  ApiResponse<List<ProductResponseDto>>allProducts();
    public  ApiResponse<ProductResponseDto> update(ProductRequestDTO dto,Long id);
    public  ApiResponse<Void> deleteProduct(Long id);

}
