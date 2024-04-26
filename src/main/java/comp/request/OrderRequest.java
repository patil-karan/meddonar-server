package comp.request;

import comp.model.Address;

public class OrderRequest {
    private Long  addressId;
    private  Long productId;
    private Integer power;
    private Integer quantity;

    public OrderRequest() {
    }

    public OrderRequest(Long productId, Long addressId, Integer power, Integer quantity) {
        this.productId = productId;
        this.addressId = addressId;
        this.power = power;
        this.quantity = quantity;
    }



    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
