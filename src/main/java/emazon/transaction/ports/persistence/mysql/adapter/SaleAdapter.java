package emazon.transaction.ports.persistence.mysql.adapter;

import emazon.transaction.domain.model.Sale;
import emazon.transaction.domain.spi.ISalePersistencePort;
import emazon.transaction.ports.persistence.mysql.entity.SaleEntity;
import emazon.transaction.ports.persistence.mysql.repository.ISaleRepository;
import emazon.transaction.ports.persistence.mysql.util.MyMapper;

import java.time.Instant;
import java.util.Date;


public class SaleAdapter implements ISalePersistencePort {
    private final ISaleRepository saleRepository;


    public SaleAdapter(ISaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public void saveSale(Sale sale) {
        SaleEntity saleEntity =MyMapper.toSaleEntity(sale);
        saleEntity.calculateTotal();
        saleEntity.setCreatedAt(Date.from(Instant.now()));
        saleRepository.save(saleEntity);
    }
}
