package com.yourname.ecommerce.service;

import com.yourname.ecommerce.dto.response.UserResponse;
import java.util.List;

public interface AdminService {
    List<UserResponse> getPendingVendors();
    List<UserResponse> getAllVendors();
    UserResponse addVendor(com.yourname.ecommerce.dto.request.UserRegistrationRequest request);
    void approveVendor(Long vendorId);
    void deleteUser(Long userId);
}