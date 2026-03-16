package com.yourname.ecommerce.controller;

import com.yourname.ecommerce.dto.request.PaymentRequest;
import com.yourname.ecommerce.dto.response.PaymentResponse;
import com.yourname.ecommerce.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-intent")
    public ResponseEntity<PaymentResponse> createPaymentIntent(@RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.createPaymentIntent(request.getAmount(), request.getCurrency());
        return ResponseEntity.ok(response);
    }
}