package emazon.transaction.domain.spi;

public interface IStockConnectionPersistencePort {
    boolean existById(Long productId);

    void updateQuantityProduct(Long productId, Integer productQuantity);
}
