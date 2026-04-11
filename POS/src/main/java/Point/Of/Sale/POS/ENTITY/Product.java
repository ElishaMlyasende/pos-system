package Point.Of.Sale.POS.ENTITY;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private  String productName;
    private String barcode;
    private  String description;
    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name="category_id",nullable = false)
    private Category category;
    @CreationTimestamp
    @Column(name = "create_at",updatable = false)
    private LocalDateTime createdAt;
    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    private Invetory invetory;
   @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true)
   private List<Sales> salesList;
    public Product(){}
    public Product(Long id, UUID uuid, String productName, String barcode, String description, Category category,
                   LocalDateTime createdAt,Invetory invetory,
                   List<Sales> salesList) {
        this.id = id;
        this.uuid = uuid;
        this.productName = productName;
        this.barcode = barcode;
        this.description = description;
        this.category = category;
        this.createdAt = createdAt;
        this.invetory=invetory;
        this.salesList=salesList;
    }
    @PrePersist
    protected void Oncreate(){
        if(this.uuid==null){
            this.uuid=UUID.randomUUID();
        }
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Invetory getInvetory() {
        return invetory;
    }

    public void setInvetory(Invetory invetory) {
        this.invetory = invetory;
    }

    public List<Sales> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<Sales> salesList) {
        this.salesList = salesList;
    }
}
