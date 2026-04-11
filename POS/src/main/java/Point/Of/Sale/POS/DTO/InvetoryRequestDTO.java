package Point.Of.Sale.POS.DTO;

import Point.Of.Sale.POS.ENTITY.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class InvetoryRequestDTO {
    @NotNull(message = "Product Id must be filled")
    private Long productId;
    @NotNull(message = "Buying price must be filled")
    private  Double buyingPrice;
    @NotNull(message = "Selling price must be filled")
    private Double sellingPrice;
    @NotNull(message = "Please Enter quantity for this product")
    private Double quantity;
    @NotNull(message = "Enter minimum stock level indicator")
    private Double stockLevel;
    @NotNull(message = "Please set Discount")
    private Double discount;
    public  InvetoryRequestDTO(){}
    public InvetoryRequestDTO( Long productId,Double buyingPrice, Double sellingPrice, Double quantity, Double stockLevel, Double discount) {
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.stockLevel = stockLevel;
        this.discount = discount;
        this.productId=productId;
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
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
