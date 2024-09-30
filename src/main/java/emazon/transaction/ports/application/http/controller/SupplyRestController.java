package emazon.transaction.ports.application.http.controller;

import emazon.transaction.domain.api.ISupplyServicePort;
import emazon.transaction.domain.model.Supply;
import emazon.transaction.ports.application.http.dto.supply.NextSupplyResponse;
import emazon.transaction.ports.application.http.dto.supply.SupplyRequest;
import emazon.transaction.ports.application.http.dto.supply.SupplyResponse;
import emazon.transaction.ports.application.http.mapper.ISupplyResponseMapper;
import emazon.transaction.ports.application.http.util.RolePermissionConstants;
import emazon.transaction.ports.application.http.util.SupplyRestControllerConstants;
import emazon.transaction.ports.persistence.mysql.mapper.ISupplyEntityMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/supply")
@RequiredArgsConstructor
public class SupplyRestController {

    private final ISupplyEntityMapper supplyEntityMapper;
    private final ISupplyServicePort supplyServicePort;
    private final ISupplyResponseMapper supplyResponseMapper;
    @Operation(summary = SupplyRestControllerConstants.ADD_PRODUCT_TO_SUPPLY_SUMMARY, description = SupplyRestControllerConstants.ADD_PRODUCT_TO_SUPPLY_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = SupplyRestControllerConstants.RESPONSE_CODE_201, description = SupplyRestControllerConstants.SUCCESSFUL_ADDITION_MESSAGE, content = @Content(schema = @Schema(implementation = SupplyResponse.class))),
            @ApiResponse(responseCode = SupplyRestControllerConstants.RESPONSE_CODE_400, description = SupplyRestControllerConstants.INVALID_INPUT_MESSAGE, content = @Content),
            @ApiResponse(responseCode = SupplyRestControllerConstants.RESPONSE_CODE_403, description = SupplyRestControllerConstants.ACCESS_DENIED_MESSAGE, content = @Content)
    })

   @PreAuthorize(RolePermissionConstants.HAS_ROLE_AUX_BODEGA)
   @PostMapping("/add/{productId}")
   public ResponseEntity<SupplyResponse> addProductToSupply(@Valid @PathVariable Long productId, @RequestBody SupplyRequest supplyRequest) {
       Supply supply = supplyEntityMapper.supplyRequestToSupply(supplyRequest);

       supplyServicePort.saveSupply(supply, productId);
       SupplyResponse supplyResponse = supplyResponseMapper.supplyToSupplyResponse(supply);

       return ResponseEntity.status(HttpStatus.CREATED).body(supplyResponse);
   }

    @Operation(summary = SupplyRestControllerConstants.GET_NEXT_SUPPLY_DATE_SUMMARY, description = SupplyRestControllerConstants.GET_NEXT_SUPPLY_DATE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = SupplyRestControllerConstants.RESPONSE_CODE_200, description = SupplyRestControllerConstants.SUCCESSFUL_RETRIEVAL_MESSAGE, content = @Content(schema = @Schema(implementation = NextSupplyResponse.class))),
            @ApiResponse(responseCode = SupplyRestControllerConstants.RESPONSE_CODE_404, description =SupplyRestControllerConstants.INVALID_INPUT_MESSAGE, content = @Content),
            @ApiResponse(responseCode = SupplyRestControllerConstants.RESPONSE_CODE_403, description = SupplyRestControllerConstants.ACCESS_DENIED_MESSAGE, content = @Content)
    })
    @PreAuthorize(RolePermissionConstants.HAS_ROLE_AUX_BODEGA_OR_CLIENTE)
   @GetMapping("/get/next-supply-date/{productId}")
   public ResponseEntity<NextSupplyResponse> getNextSupplyDate(@PathVariable Long productId) {
       Date nextSupplyDate = supplyServicePort.getNextSupplyDate(productId);
       SimpleDateFormat sdf = new SimpleDateFormat(SupplyRestControllerConstants.DATE_FORMAT);
       NextSupplyResponse nextSupplyResponse = new NextSupplyResponse();
       nextSupplyResponse.setNextSupplyDate(sdf.format(nextSupplyDate));

       return ResponseEntity.status(HttpStatus.OK).body(nextSupplyResponse);
   }
}
