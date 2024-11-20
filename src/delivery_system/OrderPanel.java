package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.awt.print.PrinterJob;

import javax.print.PrintException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI for the Order class.
 *
 * @author Ahmed Maher
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-09-06
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    // Attributes
    private Menu menu = null; // Menu attached to panel.
    private final Order order = new Order(); // Order to be printed by panel.
    // GUI Widgets
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("0");
    private final JLabel taxLabel = new JLabel("0");
    private final JLabel totalLabel = new JLabel("0");

    private JLabel nameLabels[] = null;
    private JLabel priceLabels[] = null;
    // TextFields for menu item quantities.
    private JTextField quantityFields[] = null;

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	this.nameLabels = new JLabel[this.menu.size()];
	this.priceLabels = new JLabel[this.menu.size()];
	this.quantityFields = new JTextField[this.menu.size()];
	this.layoutView();
	this.registerListeners();
    }

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {

	    // your code here
		PrinterJob print = PrinterJob.getPrinterJob();
		print.setPrintable(order);
		if(print.printDialog()) {
			try {
				print.print();
			} catch(PrinterException err) {
				System.out.println(err.getMessage());
			}
		}


	}
    }

    /**
     * Implements a FocusListener on a JTextField. Accepts only positive integers,
     * all other values are reset to 0. Adds a new MenuItem to the Order with the
     * new quantity, updates an existing MenuItem in the Order with the new
     * quantity, or removes the MenuItem from the Order if the resulting quantity is
     * 0.
     */
    private class QuantityListener implements FocusListener {
    	private MenuItem item = null;

    	QuantityListener(final MenuItem item) {
	    this.item = item;
	}

	// your code here
        public void focusGained(final FocusEvent e) {
        }

        public void focusLost(final FocusEvent e) {
        	int quantity = 0;
        	JTextField quantityField = (JTextField) e.getSource();
        	try {
        		quantity = Integer.parseInt(quantityField.getText());
        		if (quantity < 0) {
        			quantity = 0;
        		}
        	} catch (NumberFormatException ex) {
        		quantity = 0;
        	}
        	order.update(item, quantity);
        	subtotalLabel.setText(String.format("%.2f", order.getSubTotal().floatValue()));
        	taxLabel.setText(String.format("%.2f", order.getTaxes().floatValue()));
            totalLabel.setText(String.format("%.2f", order.getTotal().floatValue()));
            String quan = String.valueOf(quantity);
            quantityField.setText(quan);
        }

    }

    /**
     * Layout the panel.
     */
    private void layoutView() {
	// your code here
        GridLayout layout = new GridLayout(menu.size()+5,3);
        setLayout(layout);
        JLabel items = new JLabel("Item");
        JLabel prices = new JLabel("Price");
        JLabel quantities = new JLabel("Quantity");
        
        
        this.add(items);
        this.add(prices);
        this.add(quantities);
        for(int i = 0; i<menu.size();i++ ) {
        	MenuItem item = menu.getItem(i);
        	priceLabels[i] = new JLabel(item.getPrice().toString());
        	quantityFields[i] = new JTextField("0",5);
        	nameLabels[i] = new JLabel(item.getName());
        	 this.add(nameLabels[i]);
             this.add(priceLabels[i]);
             this.add(quantityFields[i]);
        	
        }
        JLabel subtotal = new JLabel("Subtotal;");
        JLabel taxes = new JLabel("Tax:");
        JLabel total = new JLabel("Total:");
        this.add(subtotal);
        this.add(new JLabel(""));
        this.add(subtotalLabel);
        this.add(taxes);
        this.add(new JLabel(""));
        this.add(taxLabel);
        this.add(total);
        this.add(new JLabel(""));
        this.add(totalLabel);
        this.add(new JLabel(""));
        this.add(printButton);
        this.setVisible(true);
    }

    /**
     * Register the widget listeners.
     */
    private void registerListeners() {

	// your code here
	 this.printButton.addActionListener(new PrintListener());
     for (int i = 0; i < menu.size(); i++) {
         MenuItem items = menu.getItem(i);
         quantityFields[i].addFocusListener(new QuantityListener(items));
     }
    }

}