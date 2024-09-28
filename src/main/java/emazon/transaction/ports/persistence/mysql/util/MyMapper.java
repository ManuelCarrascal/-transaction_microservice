package emazon.transaction.ports.persistence.mysql.util;

import emazon.transaction.domain.model.Sale;
import emazon.transaction.ports.persistence.mysql.entity.SaleDetailsEntity;
import emazon.transaction.ports.persistence.mysql.entity.SaleEntity;

public class MyMapper {
    private MyMapper(){}
    public static SaleEntity toSaleEntity(Sale sale){
        SaleEntity saleEntity = new SaleEntity();
        saleEntity.setUserId(sale.getUserId());
        saleEntity.setTotal(sale.getTotal());
        saleEntity.setCreatedAt(sale.getCreatedAt());
        saleEntity.setSaleDetails(sale.getSaleDetails().stream().map(details -> {
            SaleDetailsEntity saleDetailsEntity = new SaleDetailsEntity();
            saleDetailsEntity.setProductId(details.getProductId());
            saleDetailsEntity.setQuantity(details.getQuantity());
            saleDetailsEntity.setPrice(details.getPrice());
            saleDetailsEntity.setSale(saleEntity);
            return saleDetailsEntity;
        }).toList());
        return saleEntity;
    }
}
