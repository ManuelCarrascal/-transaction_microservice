package emazon.transaction.ports.application.http.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleRequest {
    private Long saleId;
    private Long userId;
    private Double total;
    private Date createdAt;
    private List<SaleDetailsRequest> saleDetails;
}
