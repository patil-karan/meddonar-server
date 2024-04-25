package comp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comp.exception.ProductException;
import comp.model.Cart;
import comp.model.CartItems;
import comp.model.Product;
import comp.model.User;
import comp.repository.CartRepository;
import comp.request.AddItemRequest;


@Service
	public class CartServiceImplementation implements CartService{

		@Autowired
		private CartRepository cartRepository;
		@Autowired
		private CartItemService cartItemService;
		@Autowired
		private ProductService productService;
		
		
		
		public CartServiceImplementation(CartRepository cartRepository, CartItemService cartItemService,ProductService productService) {
			this.cartRepository = cartRepository;
			this.cartItemService = cartItemService;
			this.productService = productService;
		}

		@Override
		public Cart CreateCart(User user) {
			Cart cart = new Cart();
			cart.setUser(user);
			return cartRepository.save(cart);
		}

		@Override
		public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
			Cart cart = cartRepository.findByUserId(userId);
			Product product = productService.findProductById(req.getProductId());
			
			CartItems isPresent = cartItemService.isCartItemExist(cart, product, req.getPower(), userId);
			
			if (isPresent==null) {
				CartItems cartItem = new CartItems();
				cartItem.setProduct(product);
				cartItem.setCart(cart);
				cartItem.setQuantity(req.getQuantity());
				cartItem.setUserId(userId);
				
				cartItem.setPower(req.getPower());
				
				CartItems createdCartItems = cartItemService.creaCartItems(cartItem);
				cart.getCartItems().add(createdCartItems);
			}
			return "Item Add To Cart";
		}

		
		
		@Override
		public Cart findUserCart(Long userId) {
			Cart cart = cartRepository.findByUserId(userId);
			

			Integer totalItems=0;
			
			for(CartItems cartItem: cart.getCartItems()) {
				totalItems += cartItem.getQuantity();
			}
			
			cart.setTotalItem(totalItems);
			return cartRepository.save(cart);
		}


		
	}

