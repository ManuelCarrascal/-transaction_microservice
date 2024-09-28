package emazon.transaction.ports.application.http.dto.supply;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SupplyResponse {
    private Long productId;
    private Integer productQuantity;
    private Date nextSupplyDate;
}
