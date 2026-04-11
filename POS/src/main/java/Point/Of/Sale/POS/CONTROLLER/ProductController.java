package Point.Of.Sale.POS.CONTROLLER;

import Point.Of.Sale.POS.DTO.ProductRequestDTO;
import Point.Of.Sale.POS.DTO.ProductResponseDto;
import Point.Of.Sale.POS.RESPONSE.ApiResponse;
import Point.Of.Sale.POS.SERVICE.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service){
        this.service=service;
    }
    @PostMapping
    public ApiResponse<ProductResponseDto> addProduct(@RequestBody ProductRequestDTO request){
        return service.addProduct(request);
    }
    @GetMapping
    public  ApiResponse<List<ProductResponseDto>>allProducts(){
        return  service.allProducts();
    }
    @PutMapping("/{id}")
    public  ApiResponse<ProductResponseDto> update(ProductRequestDTO dto,@PathVariable("id") Long id){
        return service.update( dto,id);
    }
    @DeleteMapping("/{id}")
    public  ApiResponse<Void> deleteProduct(Long id){
        return service.deleteProduct(id);
    }
}
