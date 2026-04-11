package Point.Of.Sale.POS.DTO;

import Point.Of.Sale.POS.ENTITY.Category;

import java.util.List;
import java.util.UUID;

public class ProductResponseDto {
    private Long id;
    private  String productName;
    private String barcode;
    private  String description;
    private UUID uuid;
    private CategoryResponseDTO category;
    private InvetoryRelationshipaDTO invetoryRelationshipaDTO;
    private List<SalesResponseDTO> salesResponseDTO;
    public ProductResponseDto(){}

    public ProductResponseDto(Long id,String productName, String barcode, String description,
                              UUID uuid, CategoryResponseDTO category,
                              InvetoryRelationshipaDTO invetoryRelationshipaDTO,
                              List<SalesResponseDTO> salesResponseDTO) {
        this.productName = productName;
        this.barcode = barcode;
        this.description = description;
        this.uuid = uuid;
        this.category = category;
        this.invetoryRelationshipaDTO=invetoryRelationshipaDTO;
        this.salesResponseDTO=salesResponseDTO;
        this.id=id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public CategoryResponseDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseDTO category) {
        this.category = category;
    }

    public InvetoryRelationshipaDTO getInvetoryRelationshipaDTO() {
        return invetoryRelationshipaDTO;
    }

    public void setInvetoryRelationshipaDTO(InvetoryRelationshipaDTO invetoryRelationshipaDTO) {
        this.invetoryRelationshipaDTO = invetoryRelationshipaDTO;
    }

    public List<SalesResponseDTO> getSalesResponseDTO() {
        return salesResponseDTO;
    }

    public void setSalesResponseDTO(List<SalesResponseDTO> salesResponseDTO) {
        this.salesResponseDTO = salesResponseDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
