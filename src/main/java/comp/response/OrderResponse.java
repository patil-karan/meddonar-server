package comp.response;

import comp.model.Address;

import java.time.LocalDateTime;

public class OrderResponse {
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String orderStatus;
    private String productTitle;
    private Address address;
    private Integer power;
    private Integer quantity;

    public OrderResponse() {
    }

    public OrderResponse(LocalDateTime orderDate, LocalDateTime deliveryDate, String orderStatus, String productTitle, Address address, Integer power, Integer quantity) {
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.productTitle = productTitle;
        this.address = address;
        this.power = power;
        this.quantity = quantity;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
