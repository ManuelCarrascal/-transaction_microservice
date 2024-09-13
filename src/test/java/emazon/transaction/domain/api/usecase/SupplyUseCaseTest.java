package emazon.transaction.domain.api.usecase;

import emazon.transaction.domain.exception.NotFoundException;
import emazon.transaction.domain.model.Supply;
import emazon.transaction.domain.spi.IAuthenticationPersistencePort;
import emazon.transaction.domain.spi.IStockConnectionPersistencePort;
import emazon.transaction.domain.spi.ISupplyPersistencePort;
import emazon.transaction.domain.util.SupplyUseCaseContants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplyUseCaseTest {

    @Mock
    private ISupplyPersistencePort supplyPersistencePort;

    @Mock
    private IAuthenticationPersistencePort authenticationPersistencePort;

    @Mock
    private IStockConnectionPersistencePort stockConnectionPersistencePort;

    @InjectMocks
    private SupplyUseCase supplyUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveSupply_success() {
        Supply supply = new Supply();
        supply.setProductQuantity(10);
        Long productId = 1L;
        Long userId = 100L;

        when(stockConnectionPersistencePort.existById(productId)).thenReturn(true);
        when(authenticationPersistencePort.getAuthenticatedUserId()).thenReturn(userId);

        supplyUseCase.saveSupply(supply, productId);

        verify(stockConnectionPersistencePort, times(1)).updateQuantityProduct(productId, supply.getProductQuantity());
        verify(supplyPersistencePort, times(1)).saveSupply(any(Supply.class));
        assertEquals(userId, supply.getUserId());
        assertNotNull(supply.getCreatedAt());
    }

    @Test
    void saveSupply_productNotFound_throwsNotFoundException() {
        Supply supply = new Supply();
        Long productId = 1L;

        when(stockConnectionPersistencePort.existById(productId)).thenReturn(false);

        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> supplyUseCase.saveSupply(supply, productId),
                SupplyUseCaseContants.PRODUCT_NOT_FOUND
        );

        assertEquals(SupplyUseCaseContants.PRODUCT_NOT_FOUND, thrown.getMessage());
        verify(supplyPersistencePort, never()).saveSupply(any(Supply.class));
    }

    @Test
    void saveSupply_updatesQuantityAndSavesSupply() {
        Supply supply = new Supply();
        supply.setProductQuantity(5);
        Long productId = 1L;

        when(stockConnectionPersistencePort.existById(productId)).thenReturn(true);
        when(authenticationPersistencePort.getAuthenticatedUserId()).thenReturn(100L);

        supplyUseCase.saveSupply(supply, productId);

        verify(stockConnectionPersistencePort, times(1)).updateQuantityProduct(productId, supply.getProductQuantity());
        verify(supplyPersistencePort, times(1)).saveSupply(any(Supply.class));
    }
}