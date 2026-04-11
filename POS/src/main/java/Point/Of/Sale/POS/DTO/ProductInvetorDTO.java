package Point.Of.Sale.POS.DTO;

public class ProductInvetorDTO {
    private  String productName;
    private String barcode;
    private  String description;
    public ProductInvetorDTO(){}
    public ProductInvetorDTO(String productName, String barcode, String description) {
        this.productName = productName;
        this.barcode = barcode;
        this.description = description;
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
}
