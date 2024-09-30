package emazon.transaction.domain.spi;

import emazon.transaction.domain.model.Sale;

public interface ISaleReportConnectionPersistencePort {
    void createSaleReport(Sale sale);
}
