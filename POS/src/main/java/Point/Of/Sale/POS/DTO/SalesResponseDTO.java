package Point.Of.Sale.POS.DTO;

import Point.Of.Sale.POS.ENTITY.Product;
import Point.Of.Sale.POS.ENTITY.SalesItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

public class SalesResponseDTO {
    private Long id;
    private Double totalAmount;
    private Double totalDiscount;
    private String paymentMethod;
    private Double taxAmount;
    private LocalDateTime created_at;
    private List<SalesItemResponseDTO> salesItems;
    public SalesResponseDTO(){}

    public SalesResponseDTO(Long id, Double totalAmount, Double totalDiscount, String paymentMethod, LocalDateTime created_at,Double taxAmount, List<SalesItemResponseDTO> salesItems) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.totalDiscount = totalDiscount;
        this.paymentMethod = paymentMethod;
        this.taxAmount = taxAmount;
        this.salesItems = salesItems;
        this.created_at=created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public List<SalesItemResponseDTO> getSalesItems() {
        return salesItems;
    }

    public void setSalesItems(List<SalesItemResponseDTO> salesItems) {
        this.salesItems = salesItems;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
