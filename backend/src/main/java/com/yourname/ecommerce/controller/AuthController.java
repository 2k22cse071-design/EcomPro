package com.yourname.ecommerce.controller;

import com.yourname.ecommerce.dto.request.*;
import com.yourname.ecommerce.dto.response.UserResponse;
import com.yourname.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final com.yourname.ecommerce.repository.AddressRepository addressRepository;

    public AuthController(UserService userService, com.yourname.ecommerce.repository.AddressRepository addressRepository) {
        this.userService = userService;
        this.addressRepository = addressRepository;
    }

    @GetMapping("/{userId}/addresses")
    public ResponseEntity<java.util.List<com.yourname.ecommerce.entity.Address>> getUserAddresses(@PathVariable Long userId) {
        return ResponseEntity.ok(addressRepository.findByUser_Id(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegistrationRequest request) {
        UserResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request) {
        UserResponse response = userService.loginUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        userService.forgotPassword(request);
        return ResponseEntity.ok("OTP sent to your email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        userService.resetPassword(request);
        return ResponseEntity.ok("Password reset successfully");
    }

    @DeleteMapping("/account/{userId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long userId) {
        userService.deleteAccount(userId);
        return ResponseEntity.ok("Account deleted successfully");
    }
}