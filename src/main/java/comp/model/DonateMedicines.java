package comp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DonateMedicines {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String medicineName;
    private String brand;
    private Integer power;
    private Integer quantity;
    private String category;
    private String email;
    private String mobile;
    private String donarDetail;

    public DonateMedicines() {
    }

    public DonateMedicines(Long id, String medicineName, String brand, Integer power, Integer quantity, String category, String email, String mobile, String donarDetail) {
        this.id = id;
        this.medicineName = medicineName;
        this.brand = brand;
        this.power = power;
        this.quantity = quantity;
        this.category = category;
        this.email = email;
        this.mobile = mobile;
        this.donarDetail = donarDetail;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDonarDetail() {
        return donarDetail;
    }

    public void setDonarDetail(String donarDetail) {
        this.donarDetail = donarDetail;
    }
}
