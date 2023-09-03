package com.nagarro.exittest.services;

import java.util.List;

import com.nagarro.exittest.models.Review;

public interface ReviewService {
	public Review addReview(Review review);

	public List<Review> findByProductId(Long productId);

	public List<Review> showProductReview(Long productId);

	public List<Review> findAll();

	public List<Review> findAllReviews();

	public void save(Review review);

}
