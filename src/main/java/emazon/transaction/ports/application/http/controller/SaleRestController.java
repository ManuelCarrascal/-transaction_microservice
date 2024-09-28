package emazon.transaction.ports.application.http.controller;

import emazon.transaction.domain.api.usecase.ISaleServicePort;
import emazon.transaction.ports.application.http.util.RolePermissionConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleRestController {
    private final ISaleServicePort saleServicePort;

    @PreAuthorize(RolePermissionConstants.HAS_ROLE_CLIENTE)
    @GetMapping
    public ResponseEntity<String> buyCart() {
        saleServicePort.buyCart();
        return ResponseEntity.ok("Cart bought");
    }



}