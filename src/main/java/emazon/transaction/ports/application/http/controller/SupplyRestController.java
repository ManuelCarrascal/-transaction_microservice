package emazon.transaction.ports.application.http.controller;

import emazon.transaction.domain.api.ISupplyServicePort;
import emazon.transaction.domain.model.Supply;
import emazon.transaction.ports.application.http.dto.NextSupplyResponse;
import emazon.transaction.ports.application.http.dto.supply.SupplyRequest;
import emazon.transaction.ports.application.http.dto.supply.SupplyResponse;
import emazon.transaction.ports.application.http.mapper.ISupplyResponseMapper;
import emazon.transaction.ports.application.http.util.RolePermissionConstants;
import emazon.transaction.ports.application.http.util.SupplyRestControllerConstants;
import emazon.transaction.ports.persistence.mysql.mapper.ISupplyEntityMapper;
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

   @PreAuthorize(RolePermissionConstants.HAS_ROLE_AUX_BODEGA)
   @PostMapping("/add/{productId}")
   public ResponseEntity<SupplyResponse> addProductToSupply(@Valid @PathVariable Long productId, @RequestBody SupplyRequest supplyRequest) {
       Supply supply = supplyEntityMapper.supplyRequestToSupply(supplyRequest);

       supplyServicePort.saveSupply(supply, productId);
       SupplyResponse supplyResponse = supplyResponseMapper.supplyToSupplyResponse(supply);

       return ResponseEntity.status(HttpStatus.CREATED).body(supplyResponse);
   }

   @GetMapping("/get/next-supply-date/{productId}")
   public ResponseEntity<NextSupplyResponse> getNextSupplyDate(@PathVariable Long productId) {
       Date nextSupplyDate = supplyServicePort.getNextSupplyDate(productId);
       SimpleDateFormat sdf = new SimpleDateFormat(SupplyRestControllerConstants.DATE_FORMAT);
       NextSupplyResponse nextSupplyResponse = new NextSupplyResponse();
       nextSupplyResponse.setNextSupplyDate(sdf.format(nextSupplyDate));

       return ResponseEntity.status(HttpStatus.OK).body(nextSupplyResponse);
   }
}
