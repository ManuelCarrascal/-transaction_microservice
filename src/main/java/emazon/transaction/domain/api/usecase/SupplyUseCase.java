package emazon.transaction.domain.api.usecase;

import emazon.transaction.domain.api.ISupplyServicePort;
import emazon.transaction.domain.exception.NotFoundException;
import emazon.transaction.domain.model.Supply;
import emazon.transaction.domain.spi.IAuthenticationPersistencePort;
import emazon.transaction.domain.spi.IStockConnectionPersistencePort;
import emazon.transaction.domain.spi.ISupplyPersistencePort;
import emazon.transaction.domain.util.SupplyUseCaseContants;


import java.util.Date;

public class SupplyUseCase implements ISupplyServicePort {
    private final ISupplyPersistencePort supplyPersistencePort;
    private final IAuthenticationPersistencePort authenticationPersistencePort;
    private final IStockConnectionPersistencePort stockConnectionPersistencePort;

    public SupplyUseCase(
            ISupplyPersistencePort supplyPersistencePort,
            IAuthenticationPersistencePort authenticationPersistencePort,
            IStockConnectionPersistencePort stockConnectionPersistencePort
    ) {
        this.supplyPersistencePort = supplyPersistencePort;
        this.authenticationPersistencePort = authenticationPersistencePort;
        this.stockConnectionPersistencePort = stockConnectionPersistencePort;
    }

    @Override
    public void saveSupply(Supply supply, Long productId) {
        supply.setProductId(productId);
        if (!stockConnectionPersistencePort.existById(supply.getProductId())) {
            throw new NotFoundException(SupplyUseCaseContants.PRODUCT_NOT_FOUND);
        }
        if(supply.getNextSupplyDate() == null){
            throw new NotFoundException("Next supply date is required");
        }
        supply.setCreatedAt(new Date());

        Long userId = authenticationPersistencePort.getAuthenticatedUserId();
        supply.setUserId(userId);
        stockConnectionPersistencePort.updateQuantityProduct(supply.getProductId(), supply.getProductQuantity());
        supplyPersistencePort.saveSupply(supply);
    }

    @Override
    public Date getNextSupplyDate(Long productId) {
        return supplyPersistencePort.findNextSupplyDateByProductId(productId);
    }

}
