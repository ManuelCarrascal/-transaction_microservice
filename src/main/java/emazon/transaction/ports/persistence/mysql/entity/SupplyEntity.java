package emazon.transaction.ports.persistence.mysql.entity;

import emazon.transaction.ports.persistence.mysql.util.SupplyEntityConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = SupplyEntityConstants.TABLE_NAME)
public class SupplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = SupplyEntityConstants.COLUMN_SUPPLY_ID)
    private Long supplyId;

    @Column(name = SupplyEntityConstants.COLUMN_PRODUCT_ID)
    private Long productId;

    @Column(name = SupplyEntityConstants.COLUMN_QUANTITY)
    private Integer productQuantity;

    @Column(name = SupplyEntityConstants.COLUMN_USER_ID)
    private Long userId;

    @Column(name = SupplyEntityConstants.COLUMN_CREATED_AT)
    private Date createdAt;
}
