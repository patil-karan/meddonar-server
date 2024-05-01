package comp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "customer_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "order_id")
	private Long orderId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

//	@OneToMany
//	@JsonIgnore
//	private List<OrderItem> orderItems = new ArrayList<>();

	private LocalDateTime orderDate;
	private LocalDateTime deliveryDate;

	@ManyToOne(cascade = CascadeType.ALL) // Assuming many orders can share the same address
	@JoinColumn(name = "address_id")
	private Address shippingAddress;

	private String orderStatus;
	private LocalDateTime createdAt;

	private Integer power;
	private Integer quantity;



	public Order() {
	}

	public Order(Long id, Long orderId, User user, Product product, LocalDateTime orderDate, LocalDateTime deliveryDate, Address shippingAddress, String orderStatus, LocalDateTime createdAt, Integer power, Integer quantity) {
		this.id = id;
		this.orderId = orderId;
		this.user = user;
		this.product = product;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.shippingAddress = shippingAddress;
		this.orderStatus = orderStatus;
		this.createdAt = createdAt;
		this.power = power;
		this.quantity = quantity;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
