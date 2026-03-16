package com.yourname.ecommerce.service;

import com.yourname.ecommerce.dto.request.OrderRequest;
import com.yourname.ecommerce.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request, Long userId);
    void awardCreditPoints(Long userId, Double totalAmount, boolean pointsUsed);
    java.util.List<OrderResponse> getUserOrders(Long userId);
    void deleteAllOrders();
    void updateOrderItemStatus(Long itemId, String status);
    void updateOrderStatus(Long orderId, String status);
    void cancelOrder(Long orderId, Long userId);
    java.util.List<OrderResponse> getVendorOrders(Long vendorId);
    java.util.List<OrderResponse> getAllOrders();
}