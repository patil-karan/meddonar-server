package comp.response;

public class AddressResponse {
    Long id ;

    public AddressResponse(Long id) {
        this.id = id;
    }

    public AddressResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
