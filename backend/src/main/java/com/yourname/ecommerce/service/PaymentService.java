package com.yourname.ecommerce.service;

import com.yourname.ecommerce.dto.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPaymentIntent(Double amount, String currency);
}