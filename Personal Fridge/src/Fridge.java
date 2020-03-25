import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Fridge {
	ArrayList<Item> myFridge;
	
	public Fridge() throws FileNotFoundException {
		// Initialize the arrayList with the items in Fridge.txt
		myFridge = new ArrayList<Item>();
		FileReader fridgeReader = new FileReader("Fridge.txt");
		Scanner fridgeScanner = new Scanner(fridgeReader);
		while(fridgeScanner.hasNextLine()) {
			String info = fridgeScanner.nextLine();
			String[] itemInfo = info.split(": ");
			myFridge.add(new Item(itemInfo[0], Integer.parseInt(itemInfo[1])));
		}
	}

	public void addItem(String itemName, int quantity) {
		if (myFridge.contains(itemName)) {
			getItemFromName(itemName).addToQuantity(quantity);
		} else {
			myFridge.add(new Item(itemName, quantity));
		}
		System.out.println("Added " + quantity + " " + itemName + " to your fridge.");
	}

	public void removeItem(String itemName, int quantity) {
		Item item = getItemFromName(itemName);
		if(item == null) {
			System.out.println("You don't have any " + itemName + " in your fridge.");
			return;
		}
		
		if(item.quantity - quantity == 0) {
			myFridge.remove(getItemIndexFromName(itemName));
			System.out.println("Removed " + quantity + " " + itemName + " from your fridge.");
		}
		else if (item.quantity - quantity > 0) {
			getItemFromName(itemName).subtractFromQuantity(quantity);
			System.out.println("Removed " + quantity + " " + itemName + " from your fridge.");
		}
		else {
			System.out.println("You only have " + item.quantity + " " + item.name + " in your fridge.");
		}
		
	}

	public String itemSearch(String itemName) {
		// User enters item to search, returns item and quantity found. If there is no
		// item found, returns "Item not found"
		
		Item item = getItemFromName(itemName);
		if(item.equals(null)) {
			return "Item not found";
		}
		else {
			return "Found: " + item.name + " , quantity: " + item.quantity;
		}
	}

	public String review() {
		if(myFridge.isEmpty()) {
			return "Your fridge is empty.";
		}
		
		String fridgeReview = "";
		for (int itemIndex = 0; itemIndex < myFridge.size(); itemIndex++) {
			fridgeReview += "|" + myFridge.get(itemIndex).name + ": " + myFridge.get(itemIndex).quantity + "|";
		}

		return fridgeReview; // Return a String of all the contents in the fridge and their quantities
	}
	
	private Item getItemFromName(String name) {
		for(int index = 0; index < myFridge.size(); index++) {
			if(myFridge.get(index).name.equals(name)) {
				return myFridge.get(index);
			}
		}
		return null;
	}
	
	private int getItemIndexFromName(String name) {
		for(int index = 0; index < myFridge.size(); index++) {
			if(myFridge.get(index).name.equals(name)) {
				return index;
			}
		}
		return -1;
	}
	
	public void updateFile() throws FileNotFoundException {
		PrintStream fridgeWriter = new PrintStream("Fridge.txt");
		for(int item = 0; item < this.myFridge.size(); item++) {
			Item fridgeItem = this.myFridge.get(item);
			fridgeWriter.println(fridgeItem.name + ": " + fridgeItem.quantity);
		}
	}
}
