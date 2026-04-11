package Point.Of.Sale.POS.CONTROLLER;

import Point.Of.Sale.POS.DTO.InvetoryRequestDTO;
import Point.Of.Sale.POS.DTO.InvetoryResponseDTO;
import Point.Of.Sale.POS.RESPONSE.ApiResponse;
import Point.Of.Sale.POS.SERVICE.InvetoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invetory")
public class InvetoryController {
    private final InvetoryService service;
    public InvetoryController(InvetoryService service){
        this.service=service;
    }
    @PostMapping
    public ApiResponse<InvetoryResponseDTO> add(@Valid @RequestBody InvetoryRequestDTO request){
        return service.add(request);
    }
    @GetMapping
    public ApiResponse<List<InvetoryResponseDTO>> listAll(){
        return service.listAll();
    }
    @PutMapping("/{id}")
    public  ApiResponse<InvetoryResponseDTO> update(@Valid @RequestBody InvetoryRequestDTO dto, @PathVariable("id") Long id){
        return service.update(dto,id);
    }
    @DeleteMapping("/{id}")
    public  ApiResponse<Void> delete(@PathVariable("id") Long id){
        return service.delete(id);
    }
}
