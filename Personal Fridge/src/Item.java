import java.util.ArrayList;

public class Item {
	public int quantity;
	public String name;
	public String category;
	
	public Item(String name, int quantity, String category) {
		this.name = name;
		this.quantity = quantity;
		this.category = category;
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
