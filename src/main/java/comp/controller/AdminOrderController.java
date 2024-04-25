package comp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comp.exception.OrderException;
import comp.model.Order;
import comp.response.ApiResponse;
import comp.service.OrderService;

	@RestController
	@RequestMapping("api/admin/order")
	public class AdminOrderController {

		@Autowired
		private OrderService orderService;

		public AdminOrderController(OrderService orderService) {
			super();
			this.orderService = orderService;
		}
		
		@GetMapping("/")
		public ResponseEntity<List<Order>> getAllOrderHandler(){
			List<Order> orders = orderService.getAllOrders();
			return new ResponseEntity<List<Order>>(orders,HttpStatus.ACCEPTED);
		}
		
		@PutMapping("/{orderId}/confirmed")
		public ResponseEntity<Order> ConfirmedOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt)throws OrderException{
			
			Order order = orderService.confirmedOrder(orderId);
			return new ResponseEntity<Order>(order,HttpStatus.OK);
		}
		
		@PutMapping("/{orderId}/Ship")
		public ResponseEntity<Order> shippedOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt)throws OrderException{
			
			Order order = orderService.shippedOrder(orderId);
			return new ResponseEntity<Order>(order,HttpStatus.OK);
		}
		
		@PutMapping("/{orderId}/deliver")
		public ResponseEntity<Order> deliverOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt)throws OrderException{
			
			Order order = orderService.deliveredOrder(orderId);
			return new ResponseEntity<Order>(order,HttpStatus.OK);
		}
		
		@PutMapping("/{orderId}/cancle")
		public ResponseEntity<Order> cancleOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt)throws OrderException{
			
			Order order = orderService.cancledOrder(orderId);
			return new ResponseEntity<Order>(order,HttpStatus.OK);
		}
		
		@PutMapping("/{orderId}/delete")
		public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt)throws OrderException{
			
			orderService.deleteOrder(orderId);
			
			ApiResponse apiResponse = new ApiResponse();
			apiResponse.setMessage("Order Deleted Successfully");
			apiResponse.setStatus(true);
			return new ResponseEntity<>(apiResponse,HttpStatus.OK);
		}
		
		
}
