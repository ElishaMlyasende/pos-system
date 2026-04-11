package Point.Of.Sale.POS.DTO;

import Point.Of.Sale.POS.ENTITY.Category;

public class ProductRequestDTO {
    private  String productName;
    private String barcode;
    private  String description;
    private Long categoryId;
    public ProductRequestDTO(){}
    public ProductRequestDTO(String productName, String barcode, String description,Long categoryId) {
        this.productName = productName;
        this.barcode = barcode;
        this.description = description;
        this.categoryId=categoryId;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
