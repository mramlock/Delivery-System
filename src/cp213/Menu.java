package cp213;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 * @author Ahmed Maher
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-09-06
 */
public class Menu {

    // Attributes.
    // define a List of MenuItem objects

    // your code here
	private ArrayList<MenuItem> menus = new ArrayList<MenuItem>();

    /**
     * Creates a new Menu from an existing Collection of MenuItems. MenuItems are
     * copied into the Menu List.
     *
     * @param items an existing Collection of MenuItems.
     */
    public Menu(Collection<MenuItem> items) {

	// your code here
    	menus = new ArrayList<MenuItem>(items);
    	

    }

    /**
     * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
     * corresponds to a MenuItem. You have to read the Scanner line by line and add
     * each MenuItem to the List of items.
     *
     * @param fileScanner A Scanner accessing MenuItem String data.
     */
    public Menu(Scanner fileScanner) {

	// your code here
    	while(fileScanner.hasNextLine()) {
    		String line = fileScanner.nextLine().trim();
    		String[] sections = line.split(" ",2);
    		if (sections.length >= 2) {
    			String priceStr = sections[0];
    			double price = Double.parseDouble(priceStr);
    			String foodName = sections[1].trim();
    			MenuItem food = new MenuItem(foodName,price);
    			menus.add(food);
    		}
    		
    	}
    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {

	// your code here

	return new MenuItem(menus.get(i).getName(),menus.get(i).getPrice());
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {

	// your code here

	return menus.size();
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
    5) poutine      $ 3.75
    6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {

	// your code here
    	String menuItems = "";
    	for (int i = 0; i<menus.size();i++) {
    		menuItems += " " + (i+1) + ") " + menus.get(i).toString() + "\n";
    	}

	return menuItems;
    }
}