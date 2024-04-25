package comp.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
	@Column(name = "cart_items")
	private Set<CartItems> cartItems = new HashSet<>();
	

	
	@Column(name = "total_item")
	private double totalItem;
	

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(User user, Set<CartItems> cartItems, double totalPrice, double totalItem,
			Integer totalDiscountedPrice, Integer discount) {
		this.user = user;
		this.cartItems = cartItems;
		this.totalItem = totalItem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItems> cartItems) {
		this.cartItems = cartItems;
	}


	public double getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(double totalItem) {
		this.totalItem = totalItem;
	}


}
