import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class UseFridge {
	public static void main(String[] args) throws FileNotFoundException {

		Fridge fridge = new Fridge();
		Scanner console = new Scanner(System.in);
		
		// List all items in the fridge (or say the fridge is empty)
		System.out.println("Items in your fridge:");
		System.out.println(fridge.review());
		System.out.println();
		
		boolean usingFridge = true;
		while (usingFridge) {
			System.out.println("Would you like to add (to), remove (from), review your fridge, find (an item), or quit?");
			String userRequest = console.nextLine();

			// Add an item to the fridge
			if (userRequest.contains("add")) {
				System.out.println("Enter the name of the food to add:");
				String food = console.nextLine();
				System.out.println("Enter the quantity:");
				int foodQuantity = console.nextInt();
				
				console.nextLine();

				fridge.addItem(food, foodQuantity);
				
			// Remove an item from the fridge
			} else if (userRequest.contains("remove")) {
				System.out.println("Enter the name of the food to remove:");
				String foodToRemove = console.nextLine();
				System.out.println("How many of this food do you want to remove?");
				int quantityRemoved = console.nextInt();
				console.nextLine();
 
				fridge.removeItem(foodToRemove, quantityRemoved);
				
			// Review the items in the fridge
			} else if (userRequest.contains("review")) {
				System.out.println(fridge.review());
				
			} else if (userRequest.contains("find")) {
				System.out.println("Enter the name of the food you want to find in your fridge:");
				String foodToFind = console.nextLine();
				System.out.println(fridge.itemSearch(foodToFind));
			// Quit the program and write to the Fridge.txt file
			} else if (userRequest.equals("quit")) {
				usingFridge = false;
				fridge.updateFile();
			}
		}
	}
}
