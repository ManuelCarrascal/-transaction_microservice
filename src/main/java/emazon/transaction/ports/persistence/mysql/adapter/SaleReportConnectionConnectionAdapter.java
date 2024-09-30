package emazon.transaction.ports.persistence.mysql.adapter;

import emazon.transaction.domain.model.Sale;
import emazon.transaction.domain.spi.ISaleReportConnectionPersistencePort;
import emazon.transaction.infrastructure.configuration.feign.ISaleReportsFeignClient;
import emazon.transaction.ports.application.http.dto.report.SaleRequest;
import emazon.transaction.ports.application.http.mapper.ISaleRequestMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaleReportConnectionConnectionAdapter implements ISaleReportConnectionPersistencePort {
    private final ISaleReportsFeignClient saleReportsFeignClient;
    private final ISaleRequestMapper saleRequestMapper;


    @Override
    public void createSaleReport(Sale sale) {
        SaleRequest saleRequest= saleRequestMapper.toSaleRequest(sale);
        saleReportsFeignClient.createSaleReport(saleRequest);
    }
}
