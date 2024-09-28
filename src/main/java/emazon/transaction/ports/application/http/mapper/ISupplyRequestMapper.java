package emazon.transaction.ports.application.http.mapper;

import emazon.transaction.domain.model.Supply;
import emazon.transaction.ports.application.http.dto.supply.SupplyRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISupplyRequestMapper {
    Supply supplyRequestToSupply(SupplyRequest supplyRequest);
}
