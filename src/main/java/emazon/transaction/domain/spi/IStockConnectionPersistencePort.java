package emazon.transaction.domain.spi;

public interface IStockConnectionPersistencePort {
    boolean existById(Long productId);
    boolean isStockSufficient(Long productId, Integer quantity);

    void updateQuantityProduct(Long productId, Integer productQuantity);

    void reduceProductQuantity(Long productId, Integer productQuantity);

    Double getProductPriceById(Long productId);
}
