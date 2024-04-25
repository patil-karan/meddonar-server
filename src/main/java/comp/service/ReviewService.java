package comp.service;

import java.util.List;

import comp.exception.ProductException;
import comp.model.Review;
import comp.model.User;
import comp.request.ReviewRequest;


	public interface ReviewService {

		public Review createReview(ReviewRequest req,User user)throws ProductException; 
		
		public List<Review> getAllReview(Long productId);
	}
