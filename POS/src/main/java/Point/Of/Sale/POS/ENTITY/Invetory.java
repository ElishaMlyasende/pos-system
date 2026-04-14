package Point.Of.Sale.POS.ENTITY;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Entity
@Table(name="invetory")
@SQLDelete(sql = "UPDATE invetory SET active=0 WHERE id=?")
@SQLRestriction("active=1")
public class Invetory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "uuid")
    private UUID uuid;
    @OneToOne
    @JoinColumn(name="product_id")
    private  Product product;
    @Column(name="buying_price")
    private  Double buyingPrice;
    @Column(name="selling_price")
    private Double sellingPrice;
    @Column(name="quantity")
    private Double quantity;
    @Column(name="stock_level")
    private Double stockLevel;
    @Column(name="discount")
    private Double discount;
    @Column(name="active")
    private boolean active=true;

    public  Invetory(){}
    @PrePersist
    protected void onCreate(){
        if (uuid==null){
            this.uuid=UUID.randomUUID();
        }
    }
    public Invetory(Long id, UUID uuid,boolean active, Product product, Double buyingPrice, Double sellingPrice, Double quantity, Double stockLevel, Double discount) {
        this.id = id;
        this.uuid = uuid;
        this.product = product;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.stockLevel = stockLevel;
        this.discount = discount;
        this.active=active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
