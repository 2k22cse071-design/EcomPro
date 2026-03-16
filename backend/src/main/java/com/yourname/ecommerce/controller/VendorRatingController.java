package com.yourname.ecommerce.controller;

import com.yourname.ecommerce.entity.VendorRating;
import com.yourname.ecommerce.service.VendorRatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class VendorRatingController {

    private final VendorRatingService vendorRatingService;

    public VendorRatingController(VendorRatingService vendorRatingService) {
        this.vendorRatingService = vendorRatingService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitRating(
            @RequestParam Long userId,
            @RequestParam Long vendorId,
            @RequestParam Integer rating,
            @RequestParam(required = false) String review) {
        vendorRatingService.submitRating(userId, vendorId, rating, review);
        return ResponseEntity.ok("Rating submitted successfully");
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<VendorRating>> getVendorRatings(@PathVariable Long vendorId) {
        return ResponseEntity.ok(vendorRatingService.getVendorRatings(vendorId));
    }

    @GetMapping("/vendor/{vendorId}/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long vendorId) {
        return ResponseEntity.ok(vendorRatingService.getAverageRating(vendorId));
    }
}
