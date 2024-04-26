package comp.controller;

import java.util.List;

import comp.repository.OrderRepository;
import comp.request.OrderRequest;
import comp.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


		
		@PostMapping("/create")
		public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest, @RequestHeader("Authorization") String jwt)throws UserException{
			User user = userService.findUserProfileByJwt(jwt);
			
			OrderResponse response = orderService.createOrder(user,orderRequest);
			if(response!=null)
				return new ResponseEntity<OrderResponse>(response,HttpStatus.CREATED);
			else
				return new ResponseEntity<OrderResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@GetMapping("/user/order")
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
