package comp.service;

	
	import java.time.LocalDateTime;
	import java.util.ArrayList;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import comp.exception.OrderException;
import comp.model.Address;
import comp.model.CartItems;
import comp.model.Order;
import comp.model.OrderItem;
import comp.model.User;
import comp.repository.AddressRepository;
import comp.repository.CartRepository;
import comp.repository.OrderItemRepository;
import comp.repository.OrderRepository;
import comp.repository.UserRepository;
import comp.model.Cart;

	@Service
	public class OrderServiceImplimentation implements OrderService{
		
		private CartRepository cartRepository;
		private CartItemService cartItemService;
		private ProductService productService;
		private OrderRepository orderRepository;
		private AddressRepository addressRepository;
		private UserRepository userRepository;
		private CartService cartService;
		private OrderItemService orderItemService;
		private OrderItemRepository orderItemRepository;
		
		

		
		public OrderServiceImplimentation(CartRepository cartRepository, CartItemService cartItemService,
				ProductService productService, OrderRepository orderRepository, AddressRepository addressRepository,
				UserRepository userRepository,CartService cartService,
				OrderItemService orderItemService, OrderItemRepository orderItemRepository) {
			this.cartRepository = cartRepository;
			this.cartItemService = cartItemService;
			this.productService = productService;
			this.orderRepository = orderRepository;
			this.addressRepository = addressRepository;
			this.cartService =cartService;
			this.userRepository=userRepository;
			this.orderItemService = orderItemService;
			this.orderItemRepository = orderItemRepository;
		}

		@Override
		public Order createOrder(User user, Address shippingAddress) {
			shippingAddress.setUser(user);
			Address address = addressRepository.save(shippingAddress);
			user.getAddresses().add(address);
			userRepository.save(user);
			
			Cart cart = cartService.findUserCart(user.getId());
			List<OrderItem> orderItems = new ArrayList<>();
			
			for (CartItems items: cart.getCartItems()) {
				OrderItem orderItem = new OrderItem();
				
				orderItem.setProduct(items.getProduct());
				orderItem.setQuantity(items.getQuantity());
				orderItem.setSize(items.getPower());
				orderItem.setUserId(items.getUserId());
				
				OrderItem createdOrderItem = orderItemRepository.save(orderItem);
				orderItems.add(createdOrderItem);
				}
			
			Order createdOrder = new Order();
			createdOrder.setUser(user);
			createdOrder.setOrderItems(orderItems);
			createdOrder.setTotalItem(cart.getTotalItem());
			List<Address> shippingAddresses = new ArrayList<>();
			shippingAddresses.add(address);
			createdOrder.setShippingAddresses(shippingAddresses);
			createdOrder.setOrderDate(LocalDateTime.now());
			createdOrder.setOrderStatus("PENDING");
			createdOrder.setCreatedAt(LocalDateTime.now());
			
			Order saveOrder = orderRepository.save(createdOrder);
			
			for(OrderItem item: orderItems) {
				item.setOrder(saveOrder);
				orderItemRepository.save(item);
			}
			return saveOrder;
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
