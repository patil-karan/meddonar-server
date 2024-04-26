package comp.request;

import comp.model.Category;
import comp.model.Power;
import comp.model.Rating;
import comp.model.Review;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductUpdateRequest {

    private String title;
    private String discription;
    private Integer quantity;
    private String brand;

    private Set<Power> power = new HashSet<>();

    private String imageUrl;

    private String categoryName;

    private LocalDateTime createdAt;

    public ProductUpdateRequest() {
    }

    public ProductUpdateRequest(String title, String discription, Integer quantity, String brand, Set<Power> power, String imageUrl, String categoryName, LocalDateTime createdAt) {
        this.title = title;
        this.discription = discription;
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
}
