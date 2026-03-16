package com.yourname.ecommerce.dto.response;

import lombok.Data;

@Data
public class VendorProductResponse {
    private Long id; // VendorProduct mapping ID
    private Long productId;
    private String productName;
    private Double sellingPrice;
    private Double originalPrice;
    private Integer stock;
    private Double discount;
}
