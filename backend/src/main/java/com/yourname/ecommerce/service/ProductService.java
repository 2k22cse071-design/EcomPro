package com.yourname.ecommerce.service;

import com.yourname.ecommerce.dto.request.ProductRequest;
import com.yourname.ecommerce.dto.request.VendorProductRequest;
import com.yourname.ecommerce.dto.response.*;
import java.util.List;

public interface ProductService {
    // Marketplace (for Users)
    List<ProductResponse> getMarketplaceProducts(String category, String search);
    ProductDetailResponse getProductDetails(Long productId);
    
    // Vendor specific
    void addVendorProduct(Long vendorId, ProductRequest request); // Direct addition
    List<VendorProductResponse> getVendorInventory(Long vendorId);
    void updateVendorProduct(Long vendorProductId, VendorProductRequest request);
    void deleteVendorProduct(Long vendorProductId);
    
    // Comparison
    List<ProductDetailResponse> compareProducts(List<Long> productIds);
    
    // Categories
    List<CategoryResponse> getAllCategories();
}