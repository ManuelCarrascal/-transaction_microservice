package emazon.transaction.ports.persistence.mysql.mapper;

import emazon.transaction.domain.model.Supply;
import emazon.transaction.ports.application.http.dto.supply.SupplyRequest;
import emazon.transaction.ports.persistence.mysql.entity.SupplyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISupplyEntityMapper {
    SupplyEntity toEntity(Supply supply);

    Supply supplyRequestToSupply(SupplyRequest supplyRequest);
}
