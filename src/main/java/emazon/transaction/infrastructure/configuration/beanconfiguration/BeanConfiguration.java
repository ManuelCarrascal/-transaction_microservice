package emazon.transaction.infrastructure.configuration.beanconfiguration;

import emazon.transaction.domain.api.ISupplyServicePort;
import emazon.transaction.domain.api.usecase.ISaleServicePort;
import emazon.transaction.domain.api.usecase.SaleUseCase;
import emazon.transaction.domain.api.usecase.SupplyUseCase;
import emazon.transaction.domain.spi.*;
import emazon.transaction.infrastructure.configuration.feign.ICartFeignClient;
import emazon.transaction.infrastructure.configuration.feign.ISaleReportsFeignClient;
import emazon.transaction.infrastructure.configuration.feign.IStockFeignClient;
import emazon.transaction.ports.application.http.mapper.ICartResponseMapper;
import emazon.transaction.ports.application.http.mapper.ISaleRequestMapper;
import emazon.transaction.ports.persistence.mysql.adapter.*;
import emazon.transaction.ports.persistence.mysql.mapper.ISupplyEntityMapper;
import emazon.transaction.ports.persistence.mysql.repository.ISaleRepository;
import emazon.transaction.ports.persistence.mysql.repository.ISupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ISupplyRepository supplyRepository;
    private final ISupplyEntityMapper supplyEntityMapper;


    @Bean
    public ISupplyPersistencePort supplyPersistencePort() {
        return new SupplyAdapter(supplyRepository, supplyEntityMapper);
    }

    @Bean
    public IAuthenticationPersistencePort authenticationPersistencePort() {
        return new AuthenticationAdapter();
    }

    @Bean
    public IStockConnectionPersistencePort stockConnectionPersistencePort(IStockFeignClient stockFeignClient) {
        return new StockConnectionAdapter(stockFeignClient);
    }

    @Bean
    public ISupplyServicePort supplyServicePort(
            ISupplyPersistencePort supplyPersistencePort,
            IAuthenticationPersistencePort authenticationPersistencePort,
            IStockConnectionPersistencePort stockConnectionPersistencePort
    ) {
        return new SupplyUseCase(supplyPersistencePort, authenticationPersistencePort, stockConnectionPersistencePort);
    }

    @Bean
    public ICartConnectionPersistencePort cartConnectionPersistencePort(ICartFeignClient cartFeignClient, ICartResponseMapper cartResponseMapper) {
        return new CartConnectionAdapter(cartFeignClient, cartResponseMapper);
    }

    @Bean
    ISalePersistencePort salePersistencePort(ISaleRepository saleRepository) {
        return new SaleAdapter(saleRepository);
    }

    @Bean
    public ISaleReportConnectionPersistencePort saleReportConnectionPersistencePort(ISaleReportsFeignClient saleReportsFeignClient, ISaleRequestMapper saleRequestMapper) {
        return new SaleReportConnectionConnectionAdapter(saleReportsFeignClient, saleRequestMapper);
    }

    @Bean
    public ISaleServicePort saleServicePort(
            ICartConnectionPersistencePort cartConnectionPersistencePort,
            IAuthenticationPersistencePort authenticationPersistencePort,
            ISalePersistencePort salePersistencePort,
            IStockConnectionPersistencePort stockConnectionPersistencePort,
            ISupplyPersistencePort supplyPersistencePort,
            ISaleReportConnectionPersistencePort saleReportConnectionPersistencePort
    ) {
        return new SaleUseCase(
                cartConnectionPersistencePort,
                authenticationPersistencePort,
                salePersistencePort,
                stockConnectionPersistencePort,
                supplyPersistencePort,saleReportConnectionPersistencePort
        );
    }
}