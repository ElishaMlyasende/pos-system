package Point.Of.Sale.POS.DTO;

import Point.Of.Sale.POS.ENTITY.Product;
import Point.Of.Sale.POS.ENTITY.Sales;
import jakarta.persistence.*;

public class SalesItemDTO {
    private Long saleId;
    private  Long productId;
    private Integer quantity;
    private  Double buyingPrice;
    private Double sellingPrice;
    private Double discountAmount;
    private Double subTotal;
    public  SalesItemDTO(){}
    public SalesItemDTO(Double discountAmount, Long saleId, Long productId, Integer quantity, Double buyingPrice, Double sellingPrice, Double subTotal) {
        this.discountAmount = discountAmount;
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.subTotal = subTotal;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
}
