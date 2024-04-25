package comp.service;

import org.springframework.stereotype.Service;

import comp.model.OrderItem;
import comp.repository.OrderItemRepository;

@Service
public class OrderItemServiceImplementation implements OrderItemService{
	
		
		private OrderItemRepository orderItemRepository;

		@Override
		public OrderItem createOrderItem(OrderItem orderItem) {
			return orderItemRepository.save(orderItem);
		}

	}

