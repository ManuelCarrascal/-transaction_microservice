package emazon.transaction.domain.spi;

import emazon.transaction.domain.model.Supply;

import java.util.Date;

public interface ISupplyPersistencePort {
    void saveSupply(Supply supply);
    Date findNextSupplyDateByProductId(Long productId);

}
