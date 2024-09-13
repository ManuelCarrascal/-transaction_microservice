package emazon.transaction.infrastructure.configuration.feign;

import emazon.transaction.ports.application.http.dto.ProductQuantityRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stock", url = "http://localhost:9091", configuration = FeignConfig.class)
public interface IStockFeignClient {

    @GetMapping("/products/{productId}")
    boolean existById(@PathVariable Long productId);


    @PatchMapping(value = "/products/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateProduct(
            @PathVariable("productId") Long productId,
            @RequestBody ProductQuantityRequest productQuantityRequest
    );

}
