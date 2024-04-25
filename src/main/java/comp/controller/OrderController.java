package comp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comp.exception.OrderException;
import comp.exception.UserException;
import comp.model.Address;
import comp.model.Order;
import comp.model.User;
import comp.service.OrderService;
import comp.service.UserService;

@RequestMapping("/api/orders")
	@RestController
	public class OrderController {

		@Autowired
		private OrderService orderService;
		
		@Autowired
		private UserService userService;
		
		@PostMapping("/")
		public ResponseEntity<Order> createOrder(@RequestHeader Address shippingAddress,@RequestHeader("Authorization") String jwt)throws UserException{
			User user = userService.findUserProfileByJwt(jwt);
			
			Order order = orderService.createOrder(user, shippingAddress);
			
			return new ResponseEntity<Order>(order,HttpStatus.CREATED);
			
		}
		
		@GetMapping("/user")
		public ResponseEntity<List<Order>> usersOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException{
			User user = userService.findUserProfileByJwt(jwt);
			
			List<Order> orders = orderService.usersOrderHistory(user.getId());
			
			return new ResponseEntity<List<Order>>(orders,HttpStatus.CREATED);
		}
		
		@GetMapping("/{Id}")
		public ResponseEntity<Order> findOrderById(@PathVariable("Id")Long orderId,@RequestHeader("Authorization") String jwt)throws UserException,OrderException{
			
			User user = userService.findUserProfileByJwt(jwt);
			
			Order order = orderService.findOrderById(orderId);
			
			return new ResponseEntity<Order>(order,HttpStatus.CREATED);
		}
}
