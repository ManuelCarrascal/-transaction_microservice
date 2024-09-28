package emazon.transaction.ports.persistence.mysql.entity;

import emazon.transaction.ports.persistence.mysql.util.SaleEntityConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = SaleEntityConstants.SALES)
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = SaleEntityConstants.SALE_ID)
    private Long saleId;

    @Column(name = SaleEntityConstants.USER_ID, nullable = false)
    private Long userId;

    @Column(name = SaleEntityConstants.TOTAL_AMOUNT, nullable = false)
    private Double total;

    @Column(name = SaleEntityConstants.CREATED_AT, nullable = false)
    private Date createdAt;

    @OneToMany(mappedBy = SaleEntityConstants.MAPPED_BY_SALE, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleDetailsEntity> saleDetails;

    public void calculateTotal() {
        setTotal(this.saleDetails.stream().mapToDouble(SaleDetailsEntity::calculateSubtotal).sum());
    }
}
