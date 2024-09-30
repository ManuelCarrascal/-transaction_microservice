package emazon.transaction.infrastructure.configuration.feign;

import emazon.transaction.infrastructure.configuration.util.FeignConstants;
import emazon.transaction.ports.application.http.dto.cart.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name=FeignConstants.CART_SERVICE_NAME, url = FeignConstants.CART_SERVICE_URL, configuration = FeignConfig.class)
public interface ICartFeignClient
{

    @GetMapping("/api/v1/cart/cart-products")
    List<CartResponse> buyCart();

    @DeleteMapping("/api/v1/cart")
    void deleteCartByUserId();
}
