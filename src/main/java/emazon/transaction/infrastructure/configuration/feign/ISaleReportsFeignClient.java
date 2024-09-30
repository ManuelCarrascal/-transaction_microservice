package emazon.transaction.infrastructure.configuration.feign;

import emazon.transaction.infrastructure.configuration.util.FeignConstants;
import emazon.transaction.ports.application.http.dto.report.SaleRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name= FeignConstants.REPORT_SERVICE_NAME, url = FeignConstants.REPORT_SERVICE_URL, configuration = FeignConfig.class)
public interface ISaleReportsFeignClient {

    @PostMapping("/reports/sale")
    void createSaleReport(@RequestBody SaleRequest saleRequest);
}
