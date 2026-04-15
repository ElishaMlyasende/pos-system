package Point.Of.Sale.POS.DTO;

import Point.Of.Sale.POS.ENTITY.SalesItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

public class SalesRequestDTO {
    private Double totalAmount;
    private Double totalDiscount;
    private String paymentMethod;
    private Double taxAmount;
    private List<SalesItemDTO> salesItemDTO;
    public  SalesRequestDTO(){}

    public SalesRequestDTO(Double totalAmount, Double totalDiscount, String paymentMethod, Double taxAmount, List<SalesItemDTO> salesItemDTO) {
        this.totalAmount = totalAmount;
        this.totalDiscount = totalDiscount;
        this.paymentMethod = paymentMethod;
        this.taxAmount = taxAmount;
        this.salesItemDTO = salesItemDTO;
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

    public List<SalesItemDTO> getSalesItemDTO() {
        return salesItemDTO;
    }

    public void setSalesItemDTO(List<SalesItemDTO> salesItemDTO) {
        this.salesItemDTO = salesItemDTO;
    }
}
