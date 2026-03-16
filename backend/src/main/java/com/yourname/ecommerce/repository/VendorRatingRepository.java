package com.yourname.ecommerce.repository;

import com.yourname.ecommerce.entity.VendorRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface VendorRatingRepository extends JpaRepository<VendorRating, Long> {
    List<VendorRating> findByVendorId(Long vendorId);
    
    @Query("SELECT AVG(vr.rating) FROM VendorRating vr WHERE vr.vendor.id = :vendorId")
    Double getAverageRatingForVendor(Long vendorId);
}
