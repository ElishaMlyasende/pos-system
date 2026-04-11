package Point.Of.Sale.POS.MAPPER;

import Point.Of.Sale.POS.DTO.ProductInvetorDTO;
import Point.Of.Sale.POS.DTO.SalesItemDTO;
import Point.Of.Sale.POS.DTO.SalesItemResponseDTO;
import Point.Of.Sale.POS.ENTITY.SalesItem;

import java.util.List;

public class SalesItemMapper {
    public static SalesItem toEntity(SalesItemDTO dto){
        SalesItem salesItem=new SalesItem();
        salesItem.setId(salesItem.getId());
        salesItem.setBuyingPrice(dto.getBuyingPrice());
        salesItem.setSellingPrice(dto.getSellingPrice());
        salesItem.setQuantity(dto.getQuantity());
        salesItem.setDiscountAmount(dto.getDiscountAmount());
        salesItem.setSubTotal(dto.getSubTotal());
        return salesItem;

    }
    public static SalesItemResponseDTO toDTO(SalesItem entity){
        SalesItemResponseDTO dto=new SalesItemResponseDTO();
        dto.setId(entity.getId());
        dto.setBuyingPrice(entity.getBuyingPrice());
        dto.setSellingPrice(entity.getSellingPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setDiscountAmount(entity.getDiscountAmount());
        dto.setSubTotal(entity.getSubTotal());
        if(entity.getProduct()!=null){
            ProductInvetorDTO productInvetorDTO=new ProductInvetorDTO();
            productInvetorDTO.setProductName(entity.getProduct().getProductName());
            productInvetorDTO.setBarcode(entity.getProduct().getBarcode());
            productInvetorDTO.setDescription(entity.getProduct().getDescription());
            dto.setProductInvetorDTO(productInvetorDTO);
        }
        dto.setSaleId(entity.getSales().getId());
        return dto;
    }
    public static List<SalesItemResponseDTO> toListDTO(List<SalesItem> entityList){
       return entityList.stream().map(SalesItemMapper::toDTO)
                .toList();
    }
    public static SalesItemResponseDTO update(SalesItem entity,SalesItemDTO dto){
        entity.setQuantity(dto.getQuantity());
        entity.setSellingPrice(dto.getSellingPrice());
        entity.setBuyingPrice(dto.getBuyingPrice());
        entity.setSubTotal(dto.getSubTotal());
        entity.setDiscountAmount(dto.getDiscountAmount());
        return  toDTO(entity);
    }
}
