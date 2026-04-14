package Point.Of.Sale.POS.ENTITY;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="sales")
@SQLDelete(sql = "UPDATE sales SET active=0 WHERE id=?")
@SQLRestriction("active=1")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalAmount;
    private Double totalDiscount;
    private String paymentMethod;
    private Double taxAmount;
    private boolean active=true;
    @ManyToOne
    @JoinColumn(name="product_id")
    private  Product product;
    @CreationTimestamp
    private LocalDateTime created_at;
    @OneToMany(mappedBy = "sales",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SalesItem> salesItems;
    public  Sales(){}

    public Sales(Double totalAmount, Long id, Double totalDiscount, String paymentMethod,
                 Double taxAmount, LocalDateTime created_at, List<SalesItem> salesItems,
                  Product product, boolean active) {
        this.totalAmount = totalAmount;
        this.id = id;
        this.totalDiscount = totalDiscount;
        this.paymentMethod = paymentMethod;
        this.taxAmount = taxAmount;
        this.created_at = created_at;
        this.salesItems = salesItems;
        this.product=product;
        this.active=active;
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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public List<SalesItem> getSalesItems() {
        return salesItems;
    }

    public void setSalesItems(List<SalesItem> salesItems) {
        this.salesItems = salesItems;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
