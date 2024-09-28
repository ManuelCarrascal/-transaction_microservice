package emazon.transaction.domain.spi;


import emazon.transaction.domain.model.Sale;


public interface ISalePersistencePort {

    void saveSale(Sale sale);
}
