package emazon.transaction.domain.api.usecase;

import emazon.transaction.domain.exception.InsufficientStockException;
import emazon.transaction.domain.exception.PurchaseException;
import emazon.transaction.domain.model.Cart;
import emazon.transaction.domain.model.Sale;
import emazon.transaction.domain.model.SaleDetails;
import emazon.transaction.domain.spi.*;
import emazon.transaction.domain.util.SaleUseCaseConstants;

import java.util.List;

public class SaleUseCase implements ISaleServicePort {
    private final ICartConnectionPersistencePort cartConnectionPersistencePort;
    private final IAuthenticationPersistencePort authenticationPersistencePort;
    private final ISalePersistencePort salePersistencePort;
    private final IStockConnectionPersistencePort stockConnectionPersistencePort;
    private final ISupplyPersistencePort supplyPersistencePort;
    private final ISaleReportConnectionPersistencePort saleReportConnectionPersistencePort;



    public SaleUseCase(ICartConnectionPersistencePort cartConnectionPersistencePort, IAuthenticationPersistencePort authenticationPersistencePort, ISalePersistencePort salePersistencePort, IStockConnectionPersistencePort stockConnectionPersistencePort, ISupplyPersistencePort supplyPersistencePort, ISaleReportConnectionPersistencePort saleReportConnectionPersistencePort) {
        this.cartConnectionPersistencePort = cartConnectionPersistencePort;
        this.authenticationPersistencePort = authenticationPersistencePort;
        this.salePersistencePort = salePersistencePort;
        this.stockConnectionPersistencePort = stockConnectionPersistencePort;
        this.supplyPersistencePort = supplyPersistencePort;
        this.saleReportConnectionPersistencePort = saleReportConnectionPersistencePort;
    }

    @Override
    public void buyCart() {
        Long userId = authenticationPersistencePort.getAuthenticatedUserId();
        List<Cart> productsInCart = cartConnectionPersistencePort.buyCart(userId);
        try{
            productsInCart.forEach(cart -> validateStockAvailability(cart.getProductId(), cart.getQuantity()));
            Sale sale = salePersistencePort.saveSale(createSales(productsInCart, userId));
            productsInCart.forEach(cart -> reduceStockQuantity(cart.getProductId(), cart.getQuantity()));
            saleReportConnectionPersistencePort.createSaleReport(sale);
            cartConnectionPersistencePort.deleteCartByUserId(userId);
        }
        catch (Exception e){
          throw  new PurchaseException(SaleUseCaseConstants.PURCHASE_ERROR,e);
        }

    }

    private Sale createSales(List<Cart> productsInCart, Long userId) {
        Sale sale = new Sale();
        sale.setUserId(userId);
        List<SaleDetails> saleDetails = productsInCart.stream().map(cart -> {
            SaleDetails saleDetail = new SaleDetails();
            saleDetail.setSale(sale);
            saleDetail.setProductId(cart.getProductId());
            saleDetail.setQuantity(cart.getQuantity());
            saleDetail.setPrice(stockConnectionPersistencePort.getProductPriceById(cart.getProductId()));

            return saleDetail;
        }).toList();

        sale.setSaleDetails(saleDetails);
        return sale;
    }

    private void validateStockAvailability(Long productId, int totalQuantity) {
        if (!stockConnectionPersistencePort.isStockSufficient(productId, totalQuantity)) {
            String nextSupplyDate = supplyPersistencePort.findNextSupplyDateByProductId(productId).toString();

            throw new InsufficientStockException(SaleUseCaseConstants.INSUFFICIENT_STOCK, nextSupplyDate);
        }
    }

    private void reduceStockQuantity(Long productId, int totalQuantity) {
        stockConnectionPersistencePort.reduceProductQuantity(productId, totalQuantity);
    }
}
