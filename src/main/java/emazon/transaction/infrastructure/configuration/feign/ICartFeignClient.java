package emazon.transaction.infrastructure.configuration.feign;

import emazon.transaction.ports.application.http.dto.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="cart", url = "http://localhost:9093", configuration = FeignConfig.class)
public interface ICartFeignClient
{

    @GetMapping("/api/v1/cart/cart-products")
    List<CartResponse> buyCart();

}
