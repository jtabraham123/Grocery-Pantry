// --== CS400 File Header Information ==--
// Name: Jack Abraham
// Email: jtabraham@wisc.edu
// Team: HC
// TA: Na Ta
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import java.util.Scanner;

/**
 * This class tests the front end and back end of the pantry implementation
 * 
 * @author Jack Abraham
 *
 */

public class GroceryPantryTester {

	/**
	 * Tests the addPair() function in the front end. 
	 * @param pantry - pantrylist to use
	 * @param scnr - input that a user would enter if using the application to add 5 takis
	 * @return true if the pantry has the item false if not
	 */
	public static boolean testAddItem() {
		PantryList pantry = new PantryList();
		Scanner scnr = new Scanner(System.lineSeparator() + "Takis" + System.lineSeparator() + "5"
				+ System.lineSeparator() + "end");
		FrontEnd.addPair(scnr, pantry);
		if (pantry.contains("takis")) return true;
		return false;
	}
	
	/**
	 * Tests the removeItem() function in the front end. Adds an item, then removes and checks if
	 * item was successfully removed
	 * @param pantry - pantrylist to use
	 * @param scnr - input that a user would enter if using the application to add 5 takis
	 * @param scnr2 - input that a user would enter if using the application to remove 5 takis
	 * @return true if the pantry removed the item false if not
	 */
	public static boolean testRemoveItem() {
		PantryList pantry = new PantryList();
		Scanner scnr = new Scanner(System.lineSeparator() + "Takis" + System.lineSeparator() + "5"
				+ System.lineSeparator() + "end");
		FrontEnd.addPair(scnr, pantry);
		Scanner scnr2 = new Scanner(System.lineSeparator() + "Takis" + System.lineSeparator() + 
				"end");
		FrontEnd.removeItem(scnr2, pantry);
		if (!pantry.contains("takis")) return true;
		return false;
	}
	
	/**
	 * Tests the emptyPantry() function in the front end. Adds 2 items, then clears and checks if
	 * items were successfully removed
	 * @param pantry - pantrylist to use
	 * @param scnr - input that a user would enter if using the application to add 7 milk
	 * @param scnr2 - input that a user would enter if using the application to add 5 takis
	 * @param scnr3 - input that a user would enter if they were emptying the pantry
	 * @return true if the pantry removed all items false if not
	 */
	public static boolean testEmptyPantry() {
		PantryList pantry = new PantryList();
		Scanner scnr = new Scanner(System.lineSeparator() + "Milk" + System.lineSeparator() + "7"
				+ System.lineSeparator() + "end");
		FrontEnd.addPair(scnr, pantry);
		Scanner scnr2 = new Scanner(System.lineSeparator() + "Takis" + System.lineSeparator() + "5"
				+ System.lineSeparator() + "end");
		FrontEnd.addPair(scnr2, pantry);
		Scanner scnr3 = new Scanner(System.lineSeparator() + System.lineSeparator() + "end");
		FrontEnd.emptyPantry(scnr3, pantry);
		if (pantry.size() != 0) return false;
		return true;
	}
	
	/**
	 * Tests the addToGrocery() function in the front end. Adds 1 item, then adds it to grocery list
	 *  and checks if 1 item was successfully removed from the pantry. adds another item (not in 
	 *  pantry) to the groceryList
	 * @param pantry - pantrylist to use
	 * @param scnr - input that a user would enter if using the application to add 7 milk
	 * @param scnr3 - input that a user would enter if using the application to add milk to grocery
	 * list
	 * @param scnr3 - input that a user would enter if they were adding takis to grocery list
	 * @return true if the pantry added the items to grocery list and removed from pantry false if
	 *  not
	 */
	public static boolean testAddToGrocery() {
		PantryList pantry = new PantryList();
		Scanner scnr = new Scanner(System.lineSeparator() + "Milk" + System.lineSeparator() + "7"
				+ System.lineSeparator() + "end");
		FrontEnd.addPair(scnr, pantry);  // milk added
		Scanner scnr3 = new Scanner(System.lineSeparator() + "Milk" + System.lineSeparator() + 
				"end");
		Scanner scnr4 = new Scanner(System.lineSeparator() + "Takis" + System.lineSeparator() + 
				"end");
		FrontEnd.addToGrocery(scnr3, pantry);
		FrontEnd.addToGrocery(scnr4, pantry); // note: takis were not added to the pantry
		String groceryList = "milk takis";
		if (!pantry.getGroceryList().equals(groceryList)) return false;
		if (pantry.contains("milk")) return false; // milk should not be in final pantry (added to
		// grocery list)
		return true;
	}
	
	/**
	 * Prints the results of the four tests
	 */
	public static void main(String[] args) {
		System.out.println(testAddItem());
		System.out.println(testRemoveItem());
		System.out.println(testEmptyPantry());	
		System.out.print(testAddToGrocery());
	}
}
