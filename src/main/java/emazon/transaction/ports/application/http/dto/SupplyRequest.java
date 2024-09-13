package emazon.transaction.ports.application.http.dto;

import emazon.transaction.ports.application.http.util.SupplyRequestConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplyRequest {
    @NotNull( message = SupplyRequestConstants.PRODUCT_QUANTITY_REQUIRED)
    private Integer productQuantity;
}
