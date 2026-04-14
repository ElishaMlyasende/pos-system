package Point.Of.Sale.POS.DTO;

public class ProductInvetorDTO {
    private  String productName;
    private String barcode;
    private  String description;
    private Long id;
    public ProductInvetorDTO(){}
    public ProductInvetorDTO(String productName, Long id,String barcode, String description) {
        this.productName = productName;
        this.barcode = barcode;
        this.description = description;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
