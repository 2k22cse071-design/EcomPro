package com.yourname.ecommerce.repository;

import com.yourname.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByCategory(String category);
    
    List<Product> findByNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrCategoryContainingIgnoreCase(String name, String brand, String category);
    
    Optional<Product> findByName(String name);
    
    List<Product> findAllByOrderByEcoScoreDesc();
}