package comp.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
	@Table(name = "order_item")
	public class OrderItem {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		@JsonIgnore
		@ManyToOne
		@JoinColumn(name = "order_id")
		private Order order;
		
		@ManyToOne
		private Product product;
		
		private String size;
		
		private Integer quantity;
	
		private Long userId;
		
		private LocalDateTime diliveryDate;

		public OrderItem() {
			super();
			// TODO Auto-generated constructor stub
		}

		public OrderItem( Order order, Product product, String size, Integer quantity, Integer price,
				Integer discountedPrice, Long userId, LocalDateTime diliveryDate) {
			this.order = order;
			this.product = product;
			this.size = size;
			this.quantity = quantity;
			this.userId = userId;
			this.diliveryDate = diliveryDate;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		
		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public LocalDateTime getDiliveryDate() {
			return diliveryDate;
		}

		public void setDiliveryDate(LocalDateTime diliveryDate) {
			this.diliveryDate = diliveryDate;
		}
	
	}

