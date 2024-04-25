package comp.service;

import comp.exception.ProductException;
import comp.model.Cart;
import comp.model.User;
import comp.request.AddItemRequest;

public interface CartService {


	public Cart CreateCart(User user);
		
		public String addCartItem(Long userId,AddItemRequest req)throws ProductException;
		
		public Cart findUserCart(Long userId);
		
	}


