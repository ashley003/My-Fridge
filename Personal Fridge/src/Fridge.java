import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Fridge {
	ArrayList<Item> myFridge;
	
	public Fridge() throws FileNotFoundException {
		// Initialize the fridge ArrayList with the items in Fridge.txt
		myFridge = new ArrayList<Item>();
		FileReader fridgeReader = new FileReader("Fridge.txt");
		Scanner fridgeScanner = new Scanner(fridgeReader);
		while(fridgeScanner.hasNextLine()) {
			String info = fridgeScanner.nextLine();
			String[] itemInfo = info.split(": ");
			myFridge.add(new Item(itemInfo[1], Integer.parseInt(itemInfo[2]), itemInfo[0]));
		}
	}

	public void addItem(String itemName, int quantity, String category) {
		// See if the item is already in the fridge
		if (myFridge.contains(itemName)) {
			getItemFromName(itemName).addToQuantity(quantity);
		} else {
			myFridge.add(new Item(itemName, quantity, category));
		}
		System.out.println("Added " + quantity + " " + itemName + " to your fridge.");
	}

	public void removeItem(String itemName, int quantity) {
		Item item = getItemFromName(itemName);
		// If the item doesn't exist in the fridge, it can't be removed
		if(item == null) {
			System.out.println("You don't have any " + itemName + " in your fridge.");
			System.out.println(similarItemsFromNameToString(itemName));
			return;
		}
		// Let the user know if they just used up their item
		if(item.quantity - quantity == 0) {
			myFridge.remove(getItemIndexFromName(itemName));
			System.out.println("Removed " + quantity + " " + itemName + " from your fridge.");
			System.out.println("You don't have any more " + itemName + " in your fridge.");
		}
		else if (item.quantity - quantity > 0) {
			getItemFromName(itemName).subtractFromQuantity(quantity);
			System.out.println("Removed " + quantity + " " + itemName + " from your fridge.");
		}
		// Let the user know if less of the item exists than the requested amount to remove
		else {
			System.out.println("You only have " + item.quantity + " " + item.name + " in your fridge.");
		}
		
	}
	
	// User enters item to search, returns item and quantity found
	public String itemSearch(String itemName) {
		
		Item item = getItemFromName(itemName);
		if(item == null) {
			// See if there are items with similar names to what the user entered
			String similarItems = similarItemsFromNameToString(itemName);
			if(similarItems.isEmpty()) {
				return "There are no " + itemName + " in your fridge.";
			}
			else {
				return "There are no " + itemName + " in your fridge. \n" + similarItems;
			}
		}
		else {
			return "Found " + item.quantity + " " + item.name + " in your fridge.";
		}
	}
	
	// Searches fridge for items in a given category
	public ArrayList<Item> categorySearch(String requestedCategory) {
		ArrayList<Item> returnedList = new ArrayList<Item>();
		for(Item item : myFridge) {
			if(item.category.equals(requestedCategory)) {
				returnedList.add(item);
			}
		}
		return returnedList;
	}
	
	// toString() method of for the categorySearch() method. Returns a printable String
	public String categorySearchToString(String category) {
		ArrayList<Item> foodsFound = categorySearch(category);
		String returnedStr = "";
		if(foodsFound.isEmpty()) {
			return "Nothing found in your fridge under the \"" + category + "\" category.";
		}
		for(Item item : foodsFound) {
			returnedStr += item.quantity + " " + item.name + ", ";
		}
		// Return the string without the last comma and space after the last item
		return returnedStr.substring(0, returnedStr.length() - 2);
	}

	// Lists all items and their quantites in the fridge
	public String review() {
		if(myFridge.isEmpty()) {
			return "Your fridge is empty.";
		}
		
		String fridgeReview = "";
		for (int itemIndex = 0; itemIndex < myFridge.size(); itemIndex++) {
			Item currentItem = myFridge.get(itemIndex); 
			fridgeReview += "|" + currentItem.name + ": " + currentItem.quantity + "|";
		}

		return fridgeReview; // Return a String of all the contents in the fridge and their quantities
	}
	
	// Searches for items with names that contain the given name 
	// (in case, for example, the user makes a typo or is inconsistent with plurality)
	public ArrayList<Item> getSimilarItemsFromName(String origName) {
		ArrayList<Item> possibleItems = new ArrayList<Item>();
		for(Item item : myFridge) {
			if (item.name.contains(origName) || origName.contains(item.name)) {
				possibleItems.add(item);
			}
		}
		return possibleItems;
	}
	
	// toString() method of for the getSimilarItemsFromName() method. Returns a printable String
	public String similarItemsFromNameToString(String name) {
		ArrayList<Item> items = getSimilarItemsFromName(name);
		String returnedStr = "Some items in your fridge you may be looking for: ";
		// Return an empty string if there are no similar items
		if(items.isEmpty()) {
			return "";
		}
		else {
			for(Item item : items) {
				returnedStr += item.name + ", ";
			}
			// Return the string without the last comma and space after the last item
			return returnedStr.substring(0, returnedStr.length() - 2);
		}
	}
	
	private Item getItemFromName(String name) {
		for(Item item : myFridge) {
			if(item.name.equals(name)) {
				return item;
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
	
	// Writes item information from the fridge ArrayList to the file Fridge.txt
	public void updateFile() throws FileNotFoundException {
		PrintStream fridgeWriter = new PrintStream("Fridge.txt");
		for(Item item : myFridge) {
			fridgeWriter.println(item.category + ": " + item.name + ": " + item.quantity);
		}
	}
}
