package Point.Of.Sale.POS.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

public class CategoryRequestDTO {
    private Long id;
    private UUID uuid;
    @Column(name="category_name", nullable = false)
    @NotBlank(message = "Category name must be field")
    private String categoryName;
    @Column(name="description")
    @NotBlank(message = "you must add description for each category")
    private String description;
    //Default Constructor
    public  CategoryRequestDTO(){}

    //Constructor
    public CategoryRequestDTO(Long id, UUID uuid, String categoryName, String description) {
        this.id = id;
        this.uuid = uuid;
        this.categoryName = categoryName;
        this.description = description;
    }
    //Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
