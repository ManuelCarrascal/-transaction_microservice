package emazon.transaction.ports.persistence.mysql.adapter;

import emazon.transaction.domain.spi.IStockConnectionPersistencePort;
import emazon.transaction.infrastructure.configuration.feign.IStockFeignClient;
import emazon.transaction.ports.application.http.dto.ProductQuantityRequest;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockConnectionAdapter implements IStockConnectionPersistencePort {

    private final IStockFeignClient stockFeignClient;


    public boolean existById(Long id) {
            try{
                return stockFeignClient.existById(id);
            }catch (FeignException.NotFound e){
                return false;
            }
    }

    public void updateQuantityProduct(Long productId, Integer productQuantity) {
        ProductQuantityRequest productQuantityRequest = new ProductQuantityRequest(productQuantity);
        stockFeignClient.updateProduct(productId, productQuantityRequest);
    }
}
