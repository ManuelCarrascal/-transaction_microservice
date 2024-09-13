package emazon.transaction.domain.spi;

import emazon.transaction.domain.model.Supply;

public interface ISupplyPersistencePort {
    void saveSupply(Supply supply);
}
