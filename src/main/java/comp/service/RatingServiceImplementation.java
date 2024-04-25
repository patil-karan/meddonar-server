package comp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import comp.exception.ProductException;
import comp.model.Product;
import comp.model.Rating;
import comp.model.User;
import comp.repository.RatingRepository;
import comp.request.RatingRequest;

@Service
	public class RatingServiceImplementation implements RatingService {

		private RatingRepository ratingRepository;
		private ProductService productService;
		
		
		
		public RatingServiceImplementation(RatingRepository ratingRepository, ProductService productService) {
			super();
			this.ratingRepository = ratingRepository;
			this.productService = productService;
		}

		@Override
		public Rating createRating(RatingRequest req, User user) throws ProductException {
			Product product = productService.findProductById(req.getProductId());
			
			Rating rating = new Rating();
			rating.setProduct(product);
			rating.setUser(user);
			rating.setRating(req.getRating());
			rating.setCreatedAt(LocalDateTime.now());
			return ratingRepository.save(rating);
		}

		@Override
		public List<Rating> getProductRating(Long productId) {
			
			return ratingRepository.getAllProductRating(productId);
		}

	}

