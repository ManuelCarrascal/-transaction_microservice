package emazon.transaction.ports.application.http.dto.supply;

import emazon.transaction.ports.application.http.util.SupplyRequestConstants;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SupplyRequest {
    @NotNull( message = SupplyRequestConstants.PRODUCT_QUANTITY_REQUIRED)
    private Integer productQuantity;
    @NotNull(message = "Next supply date is required")
    @Temporal(TemporalType.DATE)
    private Date nextSupplyDate;
}
