package cp213;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author Ahmed Maher
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-09-06
 */
public class Cashier {

    // Attributes
    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
	System.out.println("\nMenu:");
	System.out.println(menu.toString());
	System.out.println("Press 0 when done.");
	System.out.println("Press any other key to see the menu again.\n");
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
	System.out.println("Welcome to WLU Foodorama!");

	// your code here
	printCommands();
	Order orders = new Order();
	int command = -1;
	
	try(Scanner in = new Scanner(System.in)){
	while (command != 0) {
		int quantity = 0;
		System.out.print("Command: ");
		try {
			command = in.nextInt(); 
		} catch (InputMismatchException e) {
			System.out.println("Not a valid number");
			printCommands();
			continue;
		} finally {
			in.nextLine();
		}
		if (command > menu.size() || command <0) {
			printCommands();
		}
		else if (command == 0) {
		}
		else {
			while(quantity == 0) {
				System.out.println("How many do you want?");
				try {
					quantity = in.nextInt();
					if (quantity <= 0) {
						break;
					}
				} catch (InputMismatchException e) {
                    System.out.println("Not a valid number");
				} finally {
					in.nextLine();
				}
			}
			if (quantity > 0) {
				orders.add(menu.getItem(command-1),quantity);
			}
		}
		}

	
	}
	System.out.println("----------------------------------------");
	System.out.println(orders);

	return orders;
    }
}