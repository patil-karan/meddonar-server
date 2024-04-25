package comp.request;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import comp.model.Power;

public class ProductRequest {
	
		
//		private String title;
//		
//		private String discription;
//		
//		private Integer quantity;
//		
//		private String brand;
//		
//		private Set<Power> power = new HashSet<>();
//		
//		private String imageUrl;
//		
//		private String categoryName;
	
	
    private String title;
    private String description;
    private Integer quantity;
    private String brand;
    private Set<Power> power = new HashSet<>();
    private String imageUrl;
    private String categoryName;
    private LocalDateTime createdAt;
	public ProductRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductRequest(String title, String description, Integer quantity, String brand, Set<Power> power,
			String imageUrl, String categoryName, LocalDateTime createdAt) {
		super();
		this.title = title;
		this.description = description;
		this.quantity = quantity;
		this.brand = brand;
		this.power = power;
		this.imageUrl = imageUrl;
		this.categoryName = categoryName;
		this.createdAt = createdAt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Set<Power> getPower() {
		return power;
	}
	public void setPower(Set<Power> power) {
		this.power = power;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "ProductRequest [title=" + title + ", description=" + description + ", quantity=" + quantity + ", brand="
				+ brand + ", power=" + power + ", imageUrl=" + imageUrl + ", categoryName=" + categoryName
				+ ", createdAt=" + createdAt + "]";
	}
    
    
	}

