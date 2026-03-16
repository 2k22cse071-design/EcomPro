package com.yourname.ecommerce.service.impl;

import com.yourname.ecommerce.dto.response.ProductDetailResponse;
import com.yourname.ecommerce.entity.Product;
import com.yourname.ecommerce.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private VendorProductRepository vendorProductRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private VendorRatingRepository vendorRatingRepository;
    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(
                productRepository,
                userRepository,
                vendorProductRepository,
                categoryRepository,
                vendorRatingRepository,
                reviewRepository
        );
    }

    @Test
    void testGetProductDetails_Success() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        product.setPrice(100.0);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(vendorProductRepository.findByProductId(productId)).thenReturn(new ArrayList<>());
        when(reviewRepository.findByProductId(productId)).thenReturn(new ArrayList<>());

        // Act
        ProductDetailResponse response = productService.getProductDetails(productId);

        // Assert
        assertNotNull(response);
        assertEquals("Test Product", response.getProduct().getName());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testGetProductDetails_NotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            productService.getProductDetails(productId);
        });
    }
}
