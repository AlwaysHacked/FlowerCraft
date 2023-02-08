package model;

public class Food {
	private int quantity;
	
	public Food(int quantity) {
		this.quantity = quantity; 
	}
	
	public void grow(int q) {
		this.quantity += q;
	}
//	getters
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int q) {
		this.quantity = q;
	}
}
