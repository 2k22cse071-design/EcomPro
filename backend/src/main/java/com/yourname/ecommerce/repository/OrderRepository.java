package com.yourname.ecommerce.repository;

import com.yourname.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByUser_IdOrderByOrderDateDesc(Long userId);
}