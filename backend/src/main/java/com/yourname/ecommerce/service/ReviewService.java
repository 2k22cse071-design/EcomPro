package com.yourname.ecommerce.service;

import com.yourname.ecommerce.dto.request.ReviewRequest;
import com.yourname.ecommerce.dto.response.ReviewResponse;
import java.util.List;

public interface ReviewService {
    ReviewResponse addReview(ReviewRequest request, Long userId);
    List<ReviewResponse> getProductReviews(Long productId);
}
