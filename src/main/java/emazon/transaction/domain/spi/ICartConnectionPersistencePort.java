package emazon.transaction.domain.spi;


import emazon.transaction.domain.model.Cart;

import java.util.List;

public interface ICartConnectionPersistencePort {

    List<Cart> buyCart(Long userId);

}
