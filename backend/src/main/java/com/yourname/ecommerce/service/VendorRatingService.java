package com.yourname.ecommerce.service;

import com.yourname.ecommerce.entity.VendorRating;
import java.util.List;

public interface VendorRatingService {
    void submitRating(Long userId, Long vendorId, Integer rating, String review);
    List<VendorRating> getVendorRatings(Long vendorId);
    Double getAverageRating(Long vendorId);
}
