package Point.Of.Sale.POS.DTO;

import Point.Of.Sale.POS.ENTITY.Invetory;
import Point.Of.Sale.POS.ENTITY.Product;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class InvetoryResponseDTO {
    private UUID uuid;
    private Long id;
    private  Double buyingPrice;
    private Double sellingPrice;
    private Double quantity;
    private Double stockLevel;
    private Double discount;
    private ProductInvetorDTO productInvetorDTO;
    public  InvetoryResponseDTO(){}
    public InvetoryResponseDTO( Double buyingPrice, Double sellingPrice,
                               Double quantity, Double stockLevel,
                               Double discount,UUID uuid,Long id,
                               ProductInvetorDTO productInvetorDTO) {

        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.stockLevel = stockLevel;
        this.discount = discount;
        this.uuid=uuid;
        this.id=id;
        this.productInvetorDTO=productInvetorDTO;

    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(Double stockLevel) {
        this.stockLevel = stockLevel;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductInvetorDTO getProductInvetorDTO() {
        return productInvetorDTO;
    }

    public void setProductInvetorDTO(ProductInvetorDTO productInvetorDTO) {
        this.productInvetorDTO = productInvetorDTO;
    }
}
