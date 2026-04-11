package Point.Of.Sale.POS.DTO;

public class SalesItemResponseDTO {
    private Long id;
    private Long saleId;
    private ProductInvetorDTO productInvetorDTO;
    private Integer quantity;
    private  Double buyingPrice;
    private Double sellingPrice;
    private Double discountAmount;
    private Double subTotal;
    public SalesItemResponseDTO(){}
    public SalesItemResponseDTO(Long id, Long saleId, Long productId, Integer quantity,
                                Double buyingPrice, Double sellingPrice, Double discountAmount,
                                Double subTotal,
                                ProductInvetorDTO productInvetorDTO) {
        this.id = id;
        this.saleId = saleId;
         this.productInvetorDTO=productInvetorDTO;
        this.quantity = quantity;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.discountAmount = discountAmount;
        this.subTotal = subTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public ProductInvetorDTO getProductInvetorDTO() {
        return productInvetorDTO;
    }

    public void setProductInvetorDTO(ProductInvetorDTO productInvetorDTO) {
        this.productInvetorDTO = productInvetorDTO;
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
