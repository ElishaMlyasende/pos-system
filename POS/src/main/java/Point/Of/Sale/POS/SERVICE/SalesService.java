package Point.Of.Sale.POS.SERVICE;

import Point.Of.Sale.POS.DTO.SalesRequestDTO;
import Point.Of.Sale.POS.DTO.SalesResponseDTO;
import Point.Of.Sale.POS.RESPONSE.ApiResponse;

import java.util.List;

public interface SalesService {
    public ApiResponse<SalesResponseDTO> addSales(SalesRequestDTO dto);
    public ApiResponse<List<SalesResponseDTO>> getAllSales();
   // public  ApiResponse<SalesResponseDTO> updateSales(SalesRequestDTO dto, Long id);
    public ApiResponse<Void> deleteSales(Long id);
}
