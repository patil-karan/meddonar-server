package comp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;


@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "discription")
	private String discription;

	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "brand")
	private String brand;

	@Embedded
	@ElementCollection
	@CollectionTable(name = "product_power",joinColumns = @JoinColumn(name = "product_id"))
	private Set<Power> powers = new HashSet<>();
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Rating> rating = new ArrayList<>();
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Review> review = new ArrayList<>();
	
	@Column(name = "num_rating")
	private Integer numRating;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	private List<Order> order = new ArrayList<>();

	public Product() {
	}

	public Product( String title, String discription, Integer quantity, String brand, Set<Power> powers, String imageUrl, List<Rating> rating, List<Review> review, Integer numRating, Category category, LocalDateTime createdAt, List<Order> order) {
		this.title = title;
		this.discription = discription;
		this.quantity = quantity;
		this.brand = brand;
		this.powers = powers;
		this.imageUrl = imageUrl;
		this.rating = rating;
		this.review = review;
		this.numRating = numRating;
		this.category = category;
		this.createdAt = createdAt;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Set<Power> getPowers() {
		return powers;
	}

	public void setPowers(Set<Power> powers) {
		this.powers = powers;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Rating> getRating() {
		return rating;
	}

	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	public Integer getNumRating() {
		return numRating;
	}

	public void setNumRating(Integer numRating) {
		this.numRating = numRating;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
}
