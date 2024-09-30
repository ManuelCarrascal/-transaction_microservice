package emazon.transaction.domain.model;

import java.util.Date;
import java.util.List;

public class Sale {

private Long saleId;
private Long userId;
private Double total;
private Date createdAt;
private List<SaleDetails> saleDetails;

    public Sale() {
    }

    public Sale(Long saleId, Long userId, Double total, Date createdAt, List<SaleDetails> saleDetails) {
        this.saleId = saleId;
        this.userId = userId;
        this.total = total;
        this.createdAt = createdAt;
        this.saleDetails = saleDetails;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<SaleDetails> getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(List<SaleDetails> saleDetails) {
        this.saleDetails = saleDetails;
    }
}
