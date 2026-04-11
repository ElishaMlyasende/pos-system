package Point.Of.Sale.POS.DTO;

import jakarta.persistence.Column;
public class InvetoryRelationshipaDTO {
    private  Double buyingPrice;
    private Double sellingPrice;
    private Double quantity;
    private Double discount;
    public InvetoryRelationshipaDTO(){}

    public InvetoryRelationshipaDTO(Double buyingPrice, Double sellingPrice, Double quantity, Double discount) {
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.discount = discount;
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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
