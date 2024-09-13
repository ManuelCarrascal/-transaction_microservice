package emazon.transaction.ports.persistence.mysql.adapter;

import emazon.transaction.domain.model.Supply;
import emazon.transaction.domain.spi.ISupplyPersistencePort;
import emazon.transaction.ports.persistence.mysql.mapper.ISupplyEntityMapper;
import emazon.transaction.ports.persistence.mysql.repository.ISupplyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupplyAdapter implements ISupplyPersistencePort {
    private final ISupplyRepository supplyRepository;
    private final ISupplyEntityMapper supplyEntityMapper;


    @Override
    public void saveSupply(Supply supply) {
        supplyRepository.save(supplyEntityMapper.toEntity(supply));
    }
}
