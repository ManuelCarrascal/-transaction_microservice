package emazon.transaction.domain.api;

import emazon.transaction.domain.model.Supply;

public interface ISupplyServicePort {
    void saveSupply(Supply supply, Long productId);

}
