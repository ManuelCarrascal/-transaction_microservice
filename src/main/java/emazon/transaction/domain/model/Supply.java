package emazon.transaction.domain.model;

import java.util.Date;

public class Supply {
    private Long supplyId;
    private Long productId;
    private Integer productQuantity;
    private Date createdAt;
    private Long userId;
    private Date nextSupplyDate;

    public Supply() {
    }

    public Supply(Long supplyId, Long productId, Integer productQuantity, Date createdAt , Long userId,Date nextSupplyDate) {
        this.supplyId = supplyId;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.createdAt = createdAt;
        this.userId = userId;
        this.nextSupplyDate = nextSupplyDate;
    }

    public Long getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Long supplyId) {
        this.supplyId = supplyId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getNextSupplyDate() {
        return nextSupplyDate;
    }

    public void setNextSupplyDate(Date nextSupplyDate) {
        this.nextSupplyDate = nextSupplyDate;
    }
}
