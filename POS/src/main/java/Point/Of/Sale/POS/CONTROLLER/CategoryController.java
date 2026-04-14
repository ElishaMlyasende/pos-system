package Point.Of.Sale.POS.CONTROLLER;

import Point.Of.Sale.POS.DTO.CategoryRequestDTO;
import Point.Of.Sale.POS.DTO.CategoryResponseDTO;
import Point.Of.Sale.POS.ENTITY.Category;
import Point.Of.Sale.POS.RESPONSE.ApiResponse;
import Point.Of.Sale.POS.SERVICE.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pos")
public class CategoryController {
    private final  CategoryService service;
    public  CategoryController(CategoryService service){
        this.service=service;
    }
    @GetMapping
    public ApiResponse<List<CategoryResponseDTO>> listAllCategory(){
        return service.listAllCategory();
    }
    @PostMapping
    public ApiResponse<CategoryResponseDTO> addCategory(@Valid @RequestBody CategoryRequestDTO request){
        return service.addCategory(request);
    }
    @PutMapping("/{id}")
    public  ApiResponse<CategoryResponseDTO> update(@Valid @RequestBody CategoryRequestDTO dto,@PathVariable("id") Long id){
        return service.update(dto,id);
    }
    @DeleteMapping("/{id}")
    public  ApiResponse<Void> deleteCategory(@PathVariable("id") Long id){
        return service.deleteCategory(id);
    }
}
