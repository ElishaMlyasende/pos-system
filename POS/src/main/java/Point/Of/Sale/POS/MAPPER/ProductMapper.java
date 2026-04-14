package Point.Of.Sale.POS.MAPPER;

import Point.Of.Sale.POS.DTO.*;
import Point.Of.Sale.POS.ENTITY.Category;
import Point.Of.Sale.POS.ENTITY.Product;

import java.util.List;

public class ProductMapper {
    public static Product toEntity(ProductRequestDTO dto){
        Product product=new Product();
            product.setProductName(dto.getProductName());
            product.setBarcode(dto.getBarcode());
            product.setDescription(dto.getDescription());
            product.setId(product.getId());
            product.setUuid(product.getUuid());
            product.setCreatedAt(product.getCreatedAt());
            product.setPerishable(dto.getPerishable());
            product.setActive(product.isActive());
            return product;
    }
    public static ProductResponseDto todto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setUuid(product.getUuid());
        responseDto.setBarcode(product.getBarcode());
        responseDto.setProductName(product.getProductName());
        responseDto.setDescription(product.getDescription());
       responseDto.setPerishable(product.getPerishable());
        if (product.getCategory() != null) {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setCategoryName(product.getCategory().getCategoryName());
            responseDto.setCategory(categoryResponseDTO);
        }
        if (product.getInvetory() != null) {
            InvetoryRelationshipaDTO invetoryR = new InvetoryRelationshipaDTO();
            invetoryR.setQuantity(product.getInvetory().getQuantity());
            invetoryR.setBuyingPrice(product.getInvetory().getBuyingPrice());
            invetoryR.setDiscount(product.getInvetory().getDiscount());
            invetoryR.setSellingPrice(product.getInvetory().getSellingPrice());
            responseDto.setInvetoryRelationshipaDTO(invetoryR);
        }
        if (product.getSalesList() != null) {
            responseDto.setSalesResponseDTO( product.getSalesList().stream().map(sales -> {
                SalesResponseDTO salesResponseDTO = new SalesResponseDTO();
                salesResponseDTO.setId(sales.getId());
                salesResponseDTO.setTotalAmount(sales.getTotalAmount());
                salesResponseDTO.setTaxAmount(sales.getTaxAmount());
                salesResponseDTO.setPaymentMethod(sales.getPaymentMethod());
                salesResponseDTO.setTotalDiscount(sales.getTotalDiscount());
                if (sales.getSalesItems() != null) {
                    salesResponseDTO.setSalesItems(
                            sales.getSalesItems().stream()
                                    .map(SalesItemMapper::toDTO)
                                    .toList()
                    );
                }
                return salesResponseDTO;

            }).toList());

        }
        return  responseDto;
    }
    public  static List<ProductResponseDto> responseDtoList(List<Product> products){
        return products.stream().map(ProductMapper::todto)
                .toList();
    }
    public  static  ProductResponseDto update(Product product,ProductRequestDTO dto){
        product.setProductName(dto.getProductName());
        product.setBarcode(dto.getBarcode());
        product.setDescription(dto.getDescription());
        product.setPerishable(dto.getPerishable());
        return ProductMapper.todto(product);
    }
}
