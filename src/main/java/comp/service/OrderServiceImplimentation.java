package comp.service;

	
	import java.time.LocalDateTime;
	import java.util.ArrayList;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Optional;

	import comp.model.*;
	import comp.repository.*;
	import comp.request.OrderRequest;
	import comp.response.OrderResponse;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import comp.exception.OrderException;

@Service
	public class OrderServiceImplimentation implements OrderService{

	private AddressRepository addressRepository;
	private ProductRepository productRepository;
		private OrderRepository orderRepository;


	@Autowired
	public OrderServiceImplimentation(ProductRepository productRepository, OrderRepository orderRepository, AddressRepository addressRepository) {
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
		this.addressRepository = addressRepository;
	}

	@Override
	public OrderResponse createOrder(User user, OrderRequest orderRequest) {
		Product product = productRepository.getReferenceById(orderRequest.getProductId());

		// Eagerly fetch the Address by ID to ensure it's fully loaded
		Address address = addressRepository.findById(orderRequest.getAddressId()).orElseThrow(() -> new RuntimeException("Address not found"));

		// Create the order with fully loaded objects
		Order createdOrder = new Order();
		createdOrder.setUser(user);
		createdOrder.setProduct(product);
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setPower(orderRequest.getPower());
		createdOrder.setQuantity(product.getQuantity()- orderRequest.getQuantity());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.setCreatedAt(LocalDateTime.now());

		orderRepository.save(createdOrder);

		// Build the OrderResponse with fully initialized Address
		OrderResponse response = new OrderResponse();
		response.setOrderDate(createdOrder.getOrderDate());
		response.setDeliveryDate(createdOrder.getDeliveryDate());
		response.setProductTitle(product.getTitle());
		response.setOrderStatus(createdOrder.getOrderStatus());
		response.setAddress(address);
		response.setPower(orderRequest.getPower());
		response.setQuantity(orderRequest.getQuantity());

		return response;
	}


	@Override
		public Order findOrderById(Long orderId) throws OrderException {
			Optional<Order> opt = orderRepository.findById(orderId);
			
			if (opt.isPresent()) {
				return opt.get();
			}
			
			throw new OrderException("Order Does Not Exist with id"+orderId);
		}

		@Override
		public List<Order> usersOrderHistory(Long userId) {
			List<Order> orders = orderRepository.getUsersOrders(userId);
			return orders;
		}

		@Override
		public Order placedOrder(Long orderId) throws OrderException {
			Order order = findOrderById(orderId);
			order.setOrderStatus("PLACED");
			return order;
		}

		@Override
		public Order confirmedOrder(Long orderId) throws OrderException {
			Order order = findOrderById(orderId);
			order.setOrderStatus("CONFIRMED");
			return orderRepository.save(order);
		}

		@Override
		public Order shippedOrder(Long orderId) throws OrderException {
			Order order = findOrderById(orderId);
			order.setOrderStatus("SHIPPED");
			return orderRepository.save(order);
		}

		@Override
		public Order deliveredOrder(Long orderId) throws OrderException {
			Order order = findOrderById(orderId);
			order.setOrderStatus("DELIVERED");
			return orderRepository.save(order);
		}

		@Override
		public Order cancledOrder(Long orderId) throws OrderException {
			Order order = findOrderById(orderId);
			order.setOrderStatus("CANCELLED");
			return orderRepository.save(order);
		}

		@Override
		public List<Order> getAllOrders() {
			
			return orderRepository.findAll();
		}

		@Override
		public void deleteOrder(Long orderId) throws OrderException {
			Order order = findOrderById(orderId);
			orderRepository.deleteById(orderId);
			
		}	

}
