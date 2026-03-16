package com.yourname.ecommerce.service;

import com.yourname.ecommerce.dto.request.LoginRequest;
import com.yourname.ecommerce.dto.request.UserRegistrationRequest;
import com.yourname.ecommerce.dto.request.ForgotPasswordRequest;
import com.yourname.ecommerce.dto.request.ResetPasswordRequest;
import com.yourname.ecommerce.dto.response.UserResponse;
import java.util.List;

public interface UserService {
    UserResponse registerUser(UserRegistrationRequest request);
    UserResponse loginUser(LoginRequest request);
    UserResponse getUserProfile(Long userId);
    void forgotPassword(ForgotPasswordRequest request);
    void resetPassword(ResetPasswordRequest request);
    void deleteAccount(Long userId);
}