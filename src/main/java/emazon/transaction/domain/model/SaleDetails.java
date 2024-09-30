package emazon.transaction.domain.model;

public class SaleDetails {
    private Long saleDetailsId;
    private Sale sale;
    private Long productId;
    private Integer quantity;
    private Double price;
    private Double subtotal;

    public SaleDetails() {
    }

    public SaleDetails(Long saleDetailsId, Sale sale, Long productId, Integer quantity, Double price, Double subtotal) {
        this.saleDetailsId = saleDetailsId;
        this.sale = sale;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    public Long getSaleDetailsId() {
        return saleDetailsId;
    }

    public void setSaleDetailsId(Long saleDetailsId) {
        this.saleDetailsId = saleDetailsId;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
