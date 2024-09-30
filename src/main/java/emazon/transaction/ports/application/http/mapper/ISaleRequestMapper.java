package emazon.transaction.ports.application.http.mapper;

import emazon.transaction.domain.model.Sale;
import emazon.transaction.ports.application.http.dto.report.SaleRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISaleRequestMapper {
    SaleRequest toSaleRequest(Sale sale);
}
