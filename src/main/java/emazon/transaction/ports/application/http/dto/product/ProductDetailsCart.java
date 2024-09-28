package emazon.transaction.ports.application.http.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import emazon.transaction.ports.application.http.dto.BrandProductResponse;
import emazon.transaction.ports.application.http.dto.CategoryProductResponse;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDetailsCart {
    private Long productId;
    private String productName;
    private String productDescription;
    private Integer productQuantity;
    private Double productPrice;
    private Integer cartQuantity;
    private BrandProductResponse brand;
    private List<CategoryProductResponse> categories;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nextSupplyDate;
    private Double subtotal;
    private Date purchaseDate;
}
