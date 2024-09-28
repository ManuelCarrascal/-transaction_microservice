package emazon.transaction.ports.persistence.mysql.entity;

import emazon.transaction.ports.persistence.mysql.util.SaleDetailsEntityConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = SaleDetailsEntityConstants.SALE_DETAILS)
public class SaleDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = SaleDetailsEntityConstants.SALE_DETAILS_ID)
    private Long saleDetailsId;

    @ManyToOne
    @JoinColumn(name = SaleDetailsEntityConstants.SALE_ID, nullable = false)
    private SaleEntity sale;

    @Column(name = SaleDetailsEntityConstants.PRODUCT_ID, nullable = false)
    private Long productId;

    @Column(name = SaleDetailsEntityConstants.QUANTITY, nullable = false)
    private Integer quantity;

    @Column(name = SaleDetailsEntityConstants.PRICE, nullable = false)
    private Double price;

    @Column(name = SaleDetailsEntityConstants.SUBTOTAL, nullable = false)
    private Double subtotal;

    public Double calculateSubtotal() {
        setSubtotal(this.price * this.quantity);
        return this.price * this.quantity;
    }
}


