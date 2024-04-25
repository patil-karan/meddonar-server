package comp.model;

public class Power {
	@SuppressWarnings("unused")
	private Integer powers;
	private Integer quantity;
	
	public Power() {
		
	}

	public Power(Integer powers, Integer quantity) {
		this.powers = powers;
		this.quantity = quantity;
	}


	public Integer getPowers() {
		return powers;
	}


	public void setPowers(Integer powers) {
		this.powers = powers;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
