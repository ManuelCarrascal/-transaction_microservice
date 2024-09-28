package emazon.transaction.ports.persistence.mysql.adapter;

import emazon.transaction.domain.model.Cart;
import emazon.transaction.domain.spi.ICartConnectionPersistencePort;
import emazon.transaction.infrastructure.configuration.feign.ICartFeignClient;
import emazon.transaction.ports.application.http.dto.CartResponse;
import emazon.transaction.ports.application.http.mapper.ICartResponseMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class CartConnectionAdapter implements ICartConnectionPersistencePort {
    private final ICartFeignClient cartFeignClient;
    private final ICartResponseMapper cartResponseMapper;


    @Override
    public List<Cart> buyCart(Long userId) {
        List<CartResponse> cartResponses = cartFeignClient.buyCart();
        return cartResponseMapper.cartResponsesToCarts(cartResponses);
    }
}
