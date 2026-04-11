package Point.Of.Sale.POS.SERVICE;

import Point.Of.Sale.POS.DTO.CategoryRequestDTO;
import Point.Of.Sale.POS.DTO.CategoryResponseDTO;
import Point.Of.Sale.POS.ENTITY.Category;
import Point.Of.Sale.POS.RESPONSE.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
   public ApiResponse<CategoryResponseDTO> addCategory(CategoryRequestDTO request);
   public ApiResponse<List<CategoryResponseDTO>> listAllCategory();
   public  ApiResponse<CategoryResponseDTO> update(CategoryRequestDTO dto,Long id);
   public  ApiResponse<Void> deleteCategory(Long id);
}
