package comp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comp.exception.CartItemException;
import comp.exception.UserException;
import comp.model.Cart;
import comp.model.CartItems;
import comp.model.Product;
import comp.model.User;
import comp.repository.CartItemRepository;

@Service
	public class CartItemServiceImplementation implements CartItemService{

		@Autowired
		private CartItemRepository cartItemRepository;
		@Autowired
		private UserService userService;
		
		
		
		public CartItemServiceImplementation(CartItemRepository cartItemRepository, UserService userService) {
			super();
			this.cartItemRepository = cartItemRepository;
			this.userService = userService;
		}

		@Override
		public CartItems creaCartItems(CartItems cartItems) {
			cartItems.setQuantity(1);
			
			
			CartItems createdCartItem = cartItemRepository.save(cartItems);
			
			return createdCartItem;
		}

		@Override
		public CartItems updateCartItems(Long userId, Long id, CartItems cartItems)throws CartItemException, UserException {
			CartItems item = findCartItemById(id);
			User user = userService.findById(item.getId());
			
			if (user.getId().equals(userId)) {
				item.setQuantity(cartItems.getQuantity());
				
			}
			return cartItemRepository.save(item);
		}

		@Override
		public CartItems isCartItemExist(Cart cart, Product product, String size, Long userId) {
			CartItems cartItem = cartItemRepository.isCartItemExist(cart, product, size, userId);
			return cartItem;
		}

		@Override
		public void removeCartItem(Long userId, Long cartItemId)throws CartItemException,UserException {
			CartItems cartItem = findCartItemById(cartItemId);
			
			User user = userService.findById(cartItem.getUserId());
			
			User reqUser = userService.findById(userId);
			
			if (user.getId().equals(reqUser.getId())) {
				cartItemRepository.deleteById(cartItemId);
			}else {
				throw new UserException("You Cant Remove another users item");
			}
		}

		@Override
		public CartItems findCartItemById(Long cartItemId) throws CartItemException {
			Optional<CartItems> optional = cartItemRepository.findById(cartItemId);
			
			if (optional.isPresent()) {
				return optional.get();
			}
			
			throw new CartItemException("Cart Item Not Found with id : "+cartItemId);
		}

		

	

}
