package Point.Of.Sale.POS.MAPPER;

import Point.Of.Sale.POS.DTO.ProductInvetorDTO;
import Point.Of.Sale.POS.DTO.SalesItemResponseDTO;
import Point.Of.Sale.POS.DTO.SalesRequestDTO;
import Point.Of.Sale.POS.DTO.SalesResponseDTO;
import Point.Of.Sale.POS.ENTITY.Sales;

import java.util.List;

public class SalesMapper {
    public static Sales toEntity(SalesRequestDTO dto){
        Sales sales=new Sales();
        sales.setId(sales.getId());
        sales.setCreated_at(sales.getCreated_at());
        sales.setPaymentMethod(dto.getPaymentMethod());
        sales.setTaxAmount(dto.getTaxAmount());
        sales.setTotalAmount(dto.getTotalAmount());
        sales.setTotalDiscount(dto.getTotalDiscount());
        sales.setActive(sales.isActive());
        return sales;
    }
    public static SalesResponseDTO update(Sales entity, SalesRequestDTO dto){
        entity.setTotalDiscount(dto.getTotalDiscount());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setTaxAmount(dto.getTaxAmount());
        entity.setPaymentMethod(dto.getPaymentMethod());
        return toDTO(entity);

    }
    public  static SalesResponseDTO toDTO(Sales entity){
        SalesResponseDTO responseDTO=new SalesResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setPaymentMethod(entity.getPaymentMethod());
        responseDTO.setTaxAmount(entity.getTaxAmount());
        responseDTO.setTotalAmount(entity.getTotalAmount());
        responseDTO.setCreated_at(entity.getCreated_at());
        responseDTO.setTotalDiscount(entity.getTotalDiscount());
        if (entity.getSalesItems()!=null){
            responseDTO.setSalesItems(
                    entity.getSalesItems().stream().map(items->{
                        SalesItemResponseDTO salesItemResponseDTO=new SalesItemResponseDTO();
                        salesItemResponseDTO.setId(items.getId());
                        salesItemResponseDTO.setBuyingPrice(items.getBuyingPrice());
                        salesItemResponseDTO.setSellingPrice(items.getSellingPrice());
                        salesItemResponseDTO.setQuantity(items.getQuantity());
                        salesItemResponseDTO.setDiscountAmount(items.getDiscountAmount());
                        salesItemResponseDTO.setSubTotal(items.getSubTotal());
                        if (items.getProduct()!=null){
                            ProductInvetorDTO productInvetorDTO=new ProductInvetorDTO();
                            productInvetorDTO.setProductName(items.getProduct().getProductName());
                            productInvetorDTO.setBarcode(items.getProduct().getBarcode());
                            productInvetorDTO.setDescription(items.getProduct().getDescription());
                            salesItemResponseDTO.setProductInvetorDTO(productInvetorDTO);
                        }
                        if (items.getSales()!=null){
                            salesItemResponseDTO.setSaleId(items.getSales().getId());
                        }
                        return salesItemResponseDTO;
                    }).toList()
            );


        }
        return responseDTO;

    }
    public static List<SalesResponseDTO> toListDTO(List<Sales> entity){
        return  entity.stream().map(SalesMapper::toDTO)
                .toList();
    }
}
