package emazon.transaction.domain.api;

import emazon.transaction.domain.model.Supply;

import java.util.Date;

public interface ISupplyServicePort {
    void saveSupply(Supply supply, Long productId);

    Date getNextSupplyDate(Long productId);
}
