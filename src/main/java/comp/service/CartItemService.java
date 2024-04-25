package comp.service;

import comp.exception.CartItemException;
import comp.exception.UserException;
import comp.model.Cart;
import comp.model.CartItems;
import comp.model.Product;

public interface CartItemService {
	

		public CartItems creaCartItems(CartItems cartItems);
		
		public CartItems updateCartItems(Long userId, Long id,CartItems cartItems)throws CartItemException,UserException;
		
		public CartItems isCartItemExist(Cart cart, Product product,String size, Long userId);
		
		public void removeCartItem(Long userId, Long cartItemId)throws CartItemException,UserException;
		
		public CartItems findCartItemById(Long cartItemId) throws CartItemException;


}
