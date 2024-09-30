package emazon.transaction.ports.persistence.mysql.adapter;

import emazon.transaction.domain.spi.IStockConnectionPersistencePort;
import emazon.transaction.infrastructure.configuration.feign.IStockFeignClient;
import emazon.transaction.ports.application.http.dto.product.ProductQuantityRequest;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockConnectionAdapter implements IStockConnectionPersistencePort {

    private final IStockFeignClient stockFeignClient;


    public boolean existById(Long id) {
        try {
            return stockFeignClient.existById(id);
        } catch (FeignException.NotFound e) {
            return false;
        }
    }

    @Override
    public boolean isStockSufficient(Long productId, Integer quantity) {
        try {
            return stockFeignClient.isStockSufficient(productId, quantity);
        } catch (FeignException.NotFound e) {
            return false;
        }
    }

    public void updateQuantityProduct(Long productId, Integer productQuantity) {
        ProductQuantityRequest productQuantityRequest = new ProductQuantityRequest(productQuantity);
        stockFeignClient.updateProduct(productId, productQuantityRequest);
    }

    @Override
    public void reduceProductQuantity(Long productId, Integer productQuantity) {
        ProductQuantityRequest productQuantityRequest = new ProductQuantityRequest(productQuantity);
        stockFeignClient.reduceProductQuantity(productId, productQuantityRequest);
    }

    @Override
    public Double getProductPriceById(Long productId) {
        return stockFeignClient.getProductPriceById(productId);
    }
}
