import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CartScreen {

	private JFrame frame;
	
	// Used to view cart information
	private JTable cartTable;
	private JScrollPane scrollPane;
	
	private static Movie[] cartMovies;
	private static DefaultTableModel tableModel;
	
	// Used for money computations
	private double total = 0;
	private double tax = 0;
	private double subtotal = 0;
	private static final double TAX_PERCENT = .06;
	private String strTotal;
	private String strTax;
	private String strSubtotal;
	private JLabel lblTotal;
	private JLabel lblTax;
	private JLabel lblSubtotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CartScreen window = new CartScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CartScreen() {
		initialize();
		frame.setVisible(true);
	}
	
	private void getPricesForMovies() {
		for (int i=0; i<cartMovies.length; i++) {
			subtotal += cartMovies[i].getPrice();
		}
		
		tax = TAX_PERCENT * subtotal;
		total = subtotal + tax;
		
		strSubtotal = String.format("Subtotal: $%.2f", subtotal);
		strTax = String.format("       Tax: $%.2f", tax);
		strTotal = String.format("     Total: $%.2f", total);
	}
	
	private void updatePriceLabels() {
		lblSubtotal.setText(strSubtotal);
		lblTax.setText(strTax);
		lblTotal.setText(strTotal);
		frame.getContentPane().remove(lblSubtotal);
		frame.getContentPane().remove(lblTax);
		frame.getContentPane().remove(lblTotal);
		frame.getContentPane().add(lblSubtotal);
		frame.getContentPane().add(lblTax);
		frame.getContentPane().add(lblTotal);
	}
	
	private void populateTable() {
		// Creating list of movies in cart
		cartMovies = null;
		cartMovies = Global.cart.toMovieArray();
		String[] column = new String[] {"Movie", "Price"};
		String[][] data = new String[cartMovies.length][column.length];
		for (int i=0; i<cartMovies.length; i++) {
			data[i][0] = cartMovies[i].getName();
			data[i][1] = Double.toString(cartMovies[i].getPrice());
		}
		tableModel = new DefaultTableModel(data, column);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		populateTable();
		getPricesForMovies();
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnExit.setBounds(340, 6, 117, 29);
		frame.getContentPane().add(btnExit);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckoutScreen checkoutScreen = new CheckoutScreen();
				frame.dispose();
			}
		});
		btnCheckout.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnCheckout.setBounds(340, 77, 117, 29);
		frame.getContentPane().add(btnCheckout);
		
		lblSubtotal = new JLabel(strSubtotal);
		lblSubtotal.setBounds(340, 220, 154, 16);
		frame.getContentPane().add(lblSubtotal);
		
		lblTax = new JLabel(strTax);
		lblTax.setBounds(341, 238, 152, 16);
		frame.getContentPane().add(lblTax);
		
		lblTotal = new JLabel(strTotal);
		lblTotal.setBounds(340, 256, 154, 16);
		frame.getContentPane().add(lblTotal);
		
		JLabel lblYourCart = new JLabel("Your Cart");
		lblYourCart.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblYourCart.setBounds(6, 10, 198, 25);
		frame.getContentPane().add(lblYourCart);
		
		cartTable = new JTable(tableModel);
		cartTable.setBounds(6, 47, 322, 225);
		scrollPane = new JScrollPane(cartTable);
		scrollPane.setBounds(6, 47, 322, 225);
		frame.getContentPane().add(scrollPane);
		
		JButton btnRemoveFromCart = new JButton("Remove Movie");
		btnRemoveFromCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Global.cart.removeFromCart(cartTable.getSelectedRow());
					tableModel.removeRow(cartTable.getSelectedRow());
					cartTable.clearSelection();
					populateTable();
					getPricesForMovies();
					updatePriceLabels();
					frame.getContentPane().validate();
				} catch (ArrayIndexOutOfBoundsException e1) {}
			}
		});
		btnRemoveFromCart.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnRemoveFromCart.setBounds(340, 47, 117, 29);
		frame.getContentPane().add(btnRemoveFromCart);
	}
}
