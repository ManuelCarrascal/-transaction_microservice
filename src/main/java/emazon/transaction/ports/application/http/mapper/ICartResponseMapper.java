package emazon.transaction.ports.application.http.mapper;

import emazon.transaction.domain.model.Cart;
import emazon.transaction.ports.application.http.dto.CartResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICartResponseMapper {


    List<Cart> cartResponsesToCarts(List<CartResponse> cartResponses);
}
