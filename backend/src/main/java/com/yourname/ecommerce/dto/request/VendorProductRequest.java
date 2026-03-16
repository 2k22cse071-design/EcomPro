package com.yourname.ecommerce.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VendorProductRequest {
    @NotNull
    private Long productId;

    @NotNull
    @Min(0)
    private Double sellingPrice;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotNull
    @Min(0)
    private Double discount;
}
