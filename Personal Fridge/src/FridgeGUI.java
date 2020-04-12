
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;

public class FridgeGUI {
	
	JFrame frame;
	FridgePanel panel;
	FridgeButtonPanel buttonPanel;
	FridgeRequestPanel requestPanel;
	FridgeRequestPanel nameTextFieldPanel;
	FridgeRequestPanel titlePanel;
	JList itemsList;
	Fridge fridge;
	
	JButton addButton;
	JButton removeButton;
	JButton searchButton; // TODO For final product have a picture of a magnifying glass on this button
	
	JTextField nameTextField;
	JLabel nameLabel;
	JLabel titleLabel;
	
	public static void main(String[] args) throws FileNotFoundException {
		new FridgeGUI().go();
	}
	
	public void go() throws FileNotFoundException {
		fridge = new Fridge();
		frame = new JFrame();
		panel = new FridgePanel();
		buttonPanel = new FridgeButtonPanel();
		requestPanel = new FridgeRequestPanel();
		nameTextFieldPanel = new FridgeRequestPanel();
		titlePanel = new FridgeRequestPanel();
		
		titleLabel = new JLabel();
		nameLabel = new JLabel();
		nameTextField = new JTextField();
		
		itemsList = new JList(arrayListToArray(fridge.myFridge));
		JScrollPane scroller = new JScrollPane(itemsList);
		
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panel.add(scroller);
		
		itemsList.setVisibleRowCount(10);
		
		addButton = new JButton("Add Item");
		removeButton = new JButton("Remove Item");
		searchButton = new JButton("Search");
		
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(searchButton);
		
		addButton.addActionListener(new AddButtonListener());
		removeButton.addActionListener(new RemoveButtonListener());
		searchButton.addActionListener(new SearchButtonListener());
		
		nameTextField.addActionListener(new TextFieldListener());
		nameTextFieldPanel.setLayout(new BoxLayout(nameTextFieldPanel, BoxLayout.Y_AXIS));		
		
		titlePanel.add(titleLabel);
		requestPanel.add(nameTextFieldPanel);
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
		frame.getContentPane().add(BorderLayout.EAST, requestPanel);
		frame.getContentPane().add(BorderLayout.NORTH, titlePanel);
		
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
	
	// Converts the ArrayList myFridge to a string array to be displayed in the JList
	public String[] arrayListToArray(ArrayList<Item> list) {
		String[] array = new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			Item item = list.get(i);
			array[i] = item.quantity + " " + item.name;
		}
		return array;
	}
	
	public void addToPanels() {
		nameTextFieldPanel.add(nameLabel);
		nameTextFieldPanel.add(nameTextField);
		
		nameTextField.setColumns(10);
	}
	
	class AddButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			titleLabel.setText("Add Item");
			nameLabel.setText("Name: ");
			
			addToPanels();

		}
	}
	
	class RemoveButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			titleLabel.setText("Remove Item");
			nameLabel.setText("Name: ");
			
			addToPanels();
		}
	}
	
	class SearchButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			titleLabel.setText("Find");
			addToPanels();
		}
	}
	
	class TextFieldListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String food = "";
			int quantity = -1;
			if(nameLabel.getText().equals("Name: ")) {
				food = nameTextField.getText();
//				System.out.println(food);
				nameTextField.setText("");
				nameLabel.setText("Quantity: ");
			}
			else if(nameLabel.getText().equals("Quantity: ") || nameLabel.getText().contains("Please")) {
				try {
					quantity = Integer.parseInt(nameTextField.getText());
					nameLabel.setText("");
				} catch(Exception e) {
					nameLabel.setText("Please enter a numerical answer");
				}
				processCommand(food, quantity);
			}
			else {
				nameLabel.setText("");
				
			}
			
		}
	}
	
	public void processCommand(String foodName, int foodQuantity) {
		if(titleLabel.getText().contains("Add")) {
			fridge.addItem(foodName, foodQuantity, "none");
			// TODO this does not actually update the item list displayed
			itemsList = new JList(arrayListToArray(fridge.myFridge));
			System.out.println("added");
		}
	}

}
