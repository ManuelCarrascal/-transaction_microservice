package emazon.transaction.ports.application.http.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplyRequest {
    private Integer productQuantity;
}
