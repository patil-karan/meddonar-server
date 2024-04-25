package comp.service;

import java.util.List;

import comp.exception.OrderException;
import comp.model.Address;
import comp.model.Order;
import comp.model.User;


public interface OrderService {

	public Order createOrder(User user,Address shippingAddress);
	
	public Order findOrderById(Long OrderId)throws OrderException;
	
	public List<Order> usersOrderHistory(Long userId);
	
	public Order placedOrder (Long orderId) throws OrderException;
	
	public Order confirmedOrder (Long orderId) throws OrderException;
	
	public Order shippedOrder (Long orderId) throws OrderException;
	
	public Order deliveredOrder (Long orderId) throws OrderException;
	
	public Order cancledOrder (Long orderId) throws OrderException;
	
	public List<Order> getAllOrders();
	
	public void deleteOrder(Long orderId) throws OrderException;
}
