package emazon.transaction.infrastructure.configuration.beanconfiguration;

import emazon.transaction.domain.api.ISupplyServicePort;
import emazon.transaction.domain.api.usecase.SupplyUseCase;
import emazon.transaction.domain.spi.IAuthenticationPersistencePort;
import emazon.transaction.domain.spi.IStockConnectionPersistencePort;
import emazon.transaction.domain.spi.ISupplyPersistencePort;
import emazon.transaction.infrastructure.configuration.feign.IStockFeignClient;
import emazon.transaction.infrastructure.configuration.util.JwtService;
import emazon.transaction.ports.persistence.mysql.adapter.AuthenticationAdapter;
import emazon.transaction.ports.persistence.mysql.adapter.StockConnectionAdapter;
import emazon.transaction.ports.persistence.mysql.adapter.SupplyAdapter;
import emazon.transaction.ports.persistence.mysql.mapper.ISupplyEntityMapper;
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
    public IAuthenticationPersistencePort authenticationPersistencePort(JwtService jwtService) {
        return new AuthenticationAdapter(jwtService);
    }
    @Bean
    public IStockConnectionPersistencePort stockConnectionPersistencePort(IStockFeignClient stockFeignClient) {
        return new StockConnectionAdapter(stockFeignClient);
    }

    @Bean
    public ISupplyServicePort supplyServicePort(ISupplyPersistencePort supplyPersistencePort, IAuthenticationPersistencePort authenticationPersistencePort, IStockConnectionPersistencePort stockConnectionPersistencePort) {
        return new SupplyUseCase(supplyPersistencePort, authenticationPersistencePort, stockConnectionPersistencePort);
    }
}