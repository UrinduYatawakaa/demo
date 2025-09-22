package com.example.demo.controller;

import com.example.demo.service.ProductMaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

    private final ProductMaintenanceService productMaintenanceService;

    public MaintenanceController(ProductMaintenanceService productMaintenanceService) {
        this.productMaintenanceService = productMaintenanceService;
    }

    /**
     * Manual trigger to deactivate out-of-stock products.
     */
    @PostMapping("/deactivate-outofstock")
    public ResponseEntity<String> deactivateOutOfStock() {
        productMaintenanceService.deactivateOutOfStockProducts();
        return ResponseEntity.ok("Out-of-stock products deactivated successfully!");
    }
}
