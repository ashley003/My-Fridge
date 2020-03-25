
public class Item {
	public int quantity;
	public String name;
	
	public Item(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void subtractFromQuantity(int quantity) {
		this.quantity -= quantity;
	}
	
	public void addToQuantity(int quantity) {
		this.quantity += quantity;
	}
}
