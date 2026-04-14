package Point.Of.Sale.POS.MAPPER;

import Point.Of.Sale.POS.DTO.CategoryRequestDTO;
import Point.Of.Sale.POS.DTO.CategoryResponseDTO;
import Point.Of.Sale.POS.DTO.ProductResponseDto;
import Point.Of.Sale.POS.ENTITY.Category;

import java.util.List;

public class CategoryMapper {
    public static Category toEntity(CategoryRequestDTO dto){
        Category c=new Category();
           c.setCategoryName(dto.getCategoryName());
           c.setDescription(dto.getDescription());
           c.setActive(c.isActive());
        return  c;
    }
    public  static CategoryResponseDTO todto(Category c){
        CategoryResponseDTO dto=new CategoryResponseDTO();
           dto.setCategoryName(c.getCategoryName());
           dto.setDescription(c.getDescription());
           dto.setId(c.getId());
          dto.setUuid(c.getUuid());
          if (c.getProducts()!=null){
             dto.setProducts(c.getProducts().stream().map(product -> {
                 ProductResponseDto dto1=new ProductResponseDto();
                 dto1.setProductName(product.getProductName());
                 dto1.setDescription(product.getDescription());
                 return dto1;
             }).toList());

          }
        return  dto;
    }
    public  static CategoryResponseDTO update(Category c, CategoryRequestDTO dto){
        c.setCategoryName(dto.getCategoryName());
        c.setDescription(dto.getDescription());
        CategoryResponseDTO res= new CategoryResponseDTO();
        res.setUuid(c.getUuid());
        res.setDescription(c.getDescription());
        res.setCategoryName(c.getCategoryName());
        return  res;
    }
    public  static List<CategoryResponseDTO> toList(List<Category> c){
        return c.stream().map(CategoryMapper::todto).toList();
    }

}
