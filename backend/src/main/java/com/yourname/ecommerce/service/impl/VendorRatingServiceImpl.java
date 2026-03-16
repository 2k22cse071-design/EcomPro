package com.yourname.ecommerce.service.impl;

import com.yourname.ecommerce.entity.User;
import com.yourname.ecommerce.entity.VendorRating;
import com.yourname.ecommerce.repository.UserRepository;
import com.yourname.ecommerce.repository.VendorRatingRepository;
import com.yourname.ecommerce.service.VendorRatingService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorRatingServiceImpl implements VendorRatingService {

    private final VendorRatingRepository vendorRatingRepository;
    private final UserRepository userRepository;

    public VendorRatingServiceImpl(VendorRatingRepository vendorRatingRepository, UserRepository userRepository) {
        this.vendorRatingRepository = vendorRatingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void submitRating(Long userId, Long vendorId, Integer rating, String review) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User vendor = userRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        VendorRating vr = new VendorRating();
        vr.setUser(user);
        vr.setVendor(vendor);
        vr.setRating(rating);
        vr.setReview(review);
        
        vendorRatingRepository.save(vr);
    }

    @Override
    public List<VendorRating> getVendorRatings(Long vendorId) {
        return vendorRatingRepository.findByVendorId(vendorId);
    }

    @Override
    public Double getAverageRating(Long vendorId) {
        Double avg = vendorRatingRepository.getAverageRatingForVendor(vendorId);
        return avg != null ? avg : 0.0;
    }
}
