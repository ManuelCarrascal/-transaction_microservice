package emazon.transaction.ports.application.http.controller;

import emazon.transaction.domain.api.usecase.ISaleServicePort;
import emazon.transaction.ports.application.http.util.RolePermissionConstants;
import emazon.transaction.ports.application.http.util.SaleRestControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/sale")
@CrossOrigin(origins = "${cors.allowed.origins}")
@RequiredArgsConstructor
public class SaleRestController {
    private final ISaleServicePort saleServicePort;
    @Operation(summary = SaleRestControllerConstants.BUY_CART_SUMMARY, description = SaleRestControllerConstants.BUY_CART_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = SaleRestControllerConstants.RESPONSE_CODE_200, description = SaleRestControllerConstants.SUCCESSFUL_PURCHASE_DESCRIPTION),
            @ApiResponse(responseCode = SaleRestControllerConstants.RESPONSE_CODE_403, description = SaleRestControllerConstants.ACCESS_DENIED_DESCRIPTION)
    })
    @PreAuthorize(RolePermissionConstants.HAS_ROLE_CLIENTE)
    @GetMapping
    public ResponseEntity<String> buyCart() {
        saleServicePort.buyCart();
        return ResponseEntity.ok(SaleRestControllerConstants.SUCCESSFUL_PURCHASE_MESSAGE);
    }

}