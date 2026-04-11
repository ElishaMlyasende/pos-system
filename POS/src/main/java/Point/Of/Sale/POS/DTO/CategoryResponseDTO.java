package Point.Of.Sale.POS.DTO;

import Point.Of.Sale.POS.ENTITY.Product;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
import java.util.UUID;

public class CategoryResponseDTO {
    private Long id;
    private UUID uuid;
    private String categoryName;
    private String description;
    private List<ProductResponseDto> products;
    //Default Constructor
    public CategoryResponseDTO(){}
    //Constructor

    public CategoryResponseDTO(Long id,UUID uuid, String categoryName, String description,List<ProductResponseDto> products) {
        this.uuid = uuid;
        this.categoryName = categoryName;
        this.description = description;
        this.products=products;
        this.id=id;
    }
    //Getter

    public UUID getUuid() {
        return uuid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }
    //Setter
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductResponseDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseDto> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
