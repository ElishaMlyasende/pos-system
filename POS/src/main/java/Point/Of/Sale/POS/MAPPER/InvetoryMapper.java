package Point.Of.Sale.POS.MAPPER;

import Point.Of.Sale.POS.DTO.InvetoryRequestDTO;
import Point.Of.Sale.POS.DTO.InvetoryResponseDTO;
import Point.Of.Sale.POS.DTO.ProductInvetorDTO;
import Point.Of.Sale.POS.DTO.ProductResponseDto;
import Point.Of.Sale.POS.ENTITY.Invetory;

import java.util.List;
import java.util.UUID;

public class InvetoryMapper {
    public static Invetory toEntity(InvetoryRequestDTO request) {
        Invetory invetory = new Invetory();
        invetory.setId(invetory.getId());
        invetory.setUuid(UUID.randomUUID());
        invetory.setDiscount(request.getDiscount());
        invetory.setQuantity(request.getQuantity());
        invetory.setBuyingPrice(request.getBuyingPrice());
        invetory.setSellingPrice(request.getSellingPrice());
        invetory.setStockLevel(request.getStockLevel());
        return invetory;
    }

    public static InvetoryResponseDTO toDto(Invetory entity) {
        InvetoryResponseDTO dto = new InvetoryResponseDTO();
        dto.setId(entity.getId());
        dto.setUuid(entity.getUuid());
        dto.setQuantity(entity.getQuantity());
        dto.setBuyingPrice(entity.getBuyingPrice());
        dto.setSellingPrice(entity.getSellingPrice());
        dto.setDiscount(entity.getDiscount());
        dto.setStockLevel(entity.getStockLevel());
        if (entity.getProduct() != null) {
            ProductResponseDto dto1 = new ProductResponseDto();
            dto1.setProductName(entity.getProduct().getProductName());
            dto1.setDescription(entity.getProduct().getDescription());

        }
        if(entity.getProduct()!=null){
            ProductInvetorDTO productInvetorDTO=new ProductInvetorDTO();
            productInvetorDTO.setProductName(entity.getProduct().getProductName());
            productInvetorDTO.setBarcode(entity.getProduct().getBarcode());
            productInvetorDTO.setDescription(entity.getProduct().getDescription());
            dto.setProductInvetorDTO(productInvetorDTO);
        }

        return dto;
    }
    public static List<InvetoryResponseDTO> tolistDto(List<Invetory> entity){
        return entity.stream().map(InvetoryMapper::toDto)
                .toList();
    }
    public  static InvetoryResponseDTO update(Invetory entity,InvetoryRequestDTO dto){
        entity.setStockLevel(dto.getStockLevel());
        entity.setQuantity(dto.getQuantity());
        entity.setDiscount(dto.getDiscount());
        entity.setSellingPrice(dto.getSellingPrice());
        entity.setBuyingPrice(dto.getBuyingPrice());
        return InvetoryMapper.toDto(entity);
    }

}
