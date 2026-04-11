package Point.Of.Sale.POS.SERVICE;

import Point.Of.Sale.POS.DTO.CategoryRequestDTO;
import Point.Of.Sale.POS.DTO.CategoryResponseDTO;
import Point.Of.Sale.POS.DTO.InvetoryRequestDTO;
import Point.Of.Sale.POS.DTO.InvetoryResponseDTO;
import Point.Of.Sale.POS.RESPONSE.ApiResponse;

import java.util.List;

public interface InvetoryService {
    public ApiResponse<InvetoryResponseDTO> add(InvetoryRequestDTO request);
    public ApiResponse<List<InvetoryResponseDTO>> listAll();
    public  ApiResponse<InvetoryResponseDTO> update(InvetoryRequestDTO dto,Long id);
    public ApiResponse<Void> delete(Long id);
}
