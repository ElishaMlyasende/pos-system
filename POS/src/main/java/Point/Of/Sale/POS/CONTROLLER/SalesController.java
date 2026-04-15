package Point.Of.Sale.POS.CONTROLLER;

import Point.Of.Sale.POS.DTO.SalesRequestDTO;
import Point.Of.Sale.POS.DTO.SalesResponseDTO;
import Point.Of.Sale.POS.RESPONSE.ApiResponse;
import Point.Of.Sale.POS.SERVICE.SalesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {
    private final SalesService salesService;
    public SalesController(SalesService salesService){
        this.salesService=salesService;
    }
    @PostMapping
    public ApiResponse<SalesResponseDTO> addSales(@RequestBody SalesRequestDTO dto){
        return salesService.addSales(dto);
    }
    @GetMapping
    public ApiResponse<List<SalesResponseDTO>> getAllSales(){
        return salesService.getAllSales();
    }
   /* @PutMapping("/{id}")
    public  ApiResponse<SalesResponseDTO> updateSales(@RequestBody SalesRequestDTO dto,@PathVariable("id") Long id){
        return salesService.updateSales(dto,id);
    }*/
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteSales(@PathVariable("id") Long id){
        return salesService.deleteSales(id);
    }

}
