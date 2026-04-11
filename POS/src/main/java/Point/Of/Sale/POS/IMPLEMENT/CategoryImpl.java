package Point.Of.Sale.POS.IMPLEMENT;

import Point.Of.Sale.POS.DTO.CategoryRequestDTO;
import Point.Of.Sale.POS.DTO.CategoryResponseDTO;
import Point.Of.Sale.POS.ENTITY.Category;
import Point.Of.Sale.POS.MAPPER.CategoryMapper;
import Point.Of.Sale.POS.REPOSITORY.CategoryRepository;
import Point.Of.Sale.POS.RESPONSE.ApiResponse;
import Point.Of.Sale.POS.RESPONSE.ApiResponseBuilder;
import Point.Of.Sale.POS.SERVICE.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImpl implements CategoryService {
    private  final CategoryRepository repository;
    public CategoryImpl(CategoryRepository repository){
        this.repository=repository;
    }
    @Override
    public ApiResponse<CategoryResponseDTO> addCategory(CategoryRequestDTO request) {
        Category category= CategoryMapper.toEntity(request);
        Category saved=repository.save(category);
        CategoryResponseDTO dto=CategoryMapper.todto(saved);
        return ApiResponseBuilder.create(dto);

    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<CategoryResponseDTO>> listAllCategory() {
       List<Category> categories1=repository.findAll();
       List<CategoryResponseDTO> dtoList=CategoryMapper.toList(categories1);
       return ApiResponseBuilder.success(dtoList);
    }

    @Override
    public ApiResponse<CategoryResponseDTO> update(CategoryRequestDTO dto, Long id) {
        Optional<Category> exist=repository.findById(id);
        if (exist.isEmpty()){
            return ApiResponseBuilder.error("Id trying to update not found "+ exist.get().getUuid());
        }
        Category category=exist.get();
        CategoryResponseDTO response= CategoryMapper.update(category,dto);
        repository.save(category);
        return ApiResponseBuilder.updated(response);

    }

    @Override
    public ApiResponse<Void> deleteCategory(Long id) {
        Optional<Category> exist=repository.findById(id);
        if (exist.isEmpty()){
            return ApiResponseBuilder.error("Id trying to update not found "+ exist.get().getUuid());
        }
        repository.deleteById(id);
        return ApiResponseBuilder.deleted();
    }
}
