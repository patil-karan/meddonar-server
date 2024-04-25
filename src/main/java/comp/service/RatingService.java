package comp.service;

import java.util.List;

import comp.exception.ProductException;
import comp.model.Rating;
import comp.model.User;
import comp.request.RatingRequest;


public interface RatingService {
		public Rating createRating(RatingRequest req, User user)throws ProductException;
		
		public List<Rating> getProductRating(Long productId);
	}
