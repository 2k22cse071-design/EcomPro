package com.yourname.ecommerce.controller;

import com.yourname.ecommerce.dto.response.VendorProductResponse;
import com.yourname.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    private final ProductService productService;

    public VendorController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{vendorId}/products")
    public ResponseEntity<List<VendorProductResponse>> getVendorProducts(@PathVariable Long vendorId) {
        return ResponseEntity.ok(productService.getVendorInventory(vendorId));
    }
}