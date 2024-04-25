package comp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import comp.model.OrderItem;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}

