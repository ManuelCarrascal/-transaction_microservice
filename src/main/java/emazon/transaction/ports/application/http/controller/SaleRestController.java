package emazon.transaction.ports.application.http.controller;

import emazon.transaction.ports.application.http.util.RolePermissionConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleRestController {

    @PreAuthorize(RolePermissionConstants.HAS_ROLE_CLIENTE)
    @PostMapping("/{productId}")
    public ResponseEntity<String> saveSale(@PathVariable Long productId) {
        return ResponseEntity.ok(String.format("Sale of product %d saved", productId));
    }
}