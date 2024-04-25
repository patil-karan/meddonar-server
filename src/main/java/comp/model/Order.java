package comp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_order") 
public class Order {
	


		
		 @Id
		    @GeneratedValue(strategy = GenerationType.AUTO)
		    private Long id;

		    @Column(name = "order_id")
		    private Long orderId;

		    @ManyToOne
		    private User user;

		    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
		    private List<OrderItem> orderItems = new ArrayList();

		    private LocalDateTime orderDate;
		    private LocalDateTime deliveryDate;

		    @OneToMany
		    private List<Address> shippingAddresses;

		    private String orderStatus;
		    private Double totalItem;
		    private LocalDateTime createdAt;

		public Order() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Order(Long id, Long orderId, User user, List<OrderItem> orderItems, LocalDateTime orderDate,
				LocalDateTime deliveryDate, List<Address> shippingAddresses, String orderStatus, Double totalItem,
				LocalDateTime createdAt) {
			super();
			this.id = id;
			this.orderId = orderId;
			this.user = user;
			this.orderItems = orderItems;
			this.orderDate = orderDate;
			this.deliveryDate = deliveryDate;
			this.shippingAddresses = shippingAddresses;
			this.orderStatus = orderStatus;
			this.totalItem = totalItem;
			this.createdAt = createdAt;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public List<OrderItem> getOrderItems() {
			return orderItems;
		}

		public void setOrderItems(List<OrderItem> orderItems) {
			this.orderItems = orderItems;
		}

		public LocalDateTime getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(LocalDateTime orderDate) {
			this.orderDate = orderDate;
		}

		public LocalDateTime getDeliveryDate() {
			return deliveryDate;
		}

		public void setDeliveryDate(LocalDateTime deliveryDate) {
			this.deliveryDate = deliveryDate;
		}

		public List<Address> getShippingAddresses() {
			return shippingAddresses;
		}

		public void setShippingAddresses(List<Address> shippingAddresses) {
			this.shippingAddresses = shippingAddresses;
		}

		public String getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}

		public Double getTotalItem() {
			return totalItem;
		}

		public void setTotalItem(Double totalItem) {
			this.totalItem = totalItem;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		
	
}
