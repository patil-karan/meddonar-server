package comp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comp.exception.CartItemException;
import comp.exception.ProductException;
import comp.exception.UserException;
import comp.model.Cart;
import comp.model.User;
import comp.request.AddItemRequest;
import comp.response.ApiResponse;
import comp.service.CartItemService;
import comp.service.CartService;
import comp.service.UserService;

@RequestMapping("/api/cart")
	@RestController
	public class CartController {
		
		@Autowired
		private CartService cartService;
		@Autowired
		private UserService userService;
		@Autowired
		private CartItemService cartItemService;

		
		@GetMapping("/")
		public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException{
			User user = userService.findUserProfileByJwt(jwt);
			Cart cart = cartService.findUserCart(user.getId());
			return new ResponseEntity<Cart>(cart,HttpStatus.OK);
		} 
		
		@PutMapping("/add")
		public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest request,@RequestHeader("Authorization") String jwt)throws UserException , ProductException{
			User user = userService.findUserProfileByJwt(jwt);
			
			cartService.addCartItem(user.getId(),request);
			
			ApiResponse response = new ApiResponse();
			response.setMessage("Items added Successfully");
			response.setStatus(true);
			
			return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
		}
		
		@DeleteMapping("/cart_items/{cartItemId}")
		public ResponseEntity<ApiResponse> removeCartItem(@PathVariable Long cartItemId,@RequestHeader("Authorization") String jwt)throws UserException , ProductException,CartItemException{
			User user = userService.findUserProfileByJwt(jwt);
			cartItemService.removeCartItem(user.getId(), cartItemId);
			
			ApiResponse response = new ApiResponse();
			response.setMessage("Items Removed Successfully");
			response.setStatus(true);
			
			return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
		}
		
}
