import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class WishlistScreen {

	private JFrame frame;
	
	// Used to view wishlist information
	private JTable wishlistTable;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	
	private static Movie[] wishlistMovies;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WishlistScreen window = new WishlistScreen();
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
	public WishlistScreen() {
		initialize();
		frame.setVisible(true);
	}
	
	public void populateTable() {
		wishlistMovies = Global.wishlist.toMovieArray();
		String[] column = new String[] {"Movie"};
		String[][] data = new String[wishlistMovies.length][column.length];
		for (int i=0; i<wishlistMovies.length; i++) {
			data[i][0] = wishlistMovies[i].getName();
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
		
		// Creating list of movies in cart
		populateTable();
		
		JButton btnAddToCart = new JButton("Add To Cart");
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Global.wishlist.addToCart(wishlistTable.getSelectedRow());
					tableModel.removeRow(wishlistTable.getSelectedRow());
					wishlistTable.clearSelection();
					wishlistMovies = Global.wishlist.toMovieArray();
					populateTable();
				} catch (ArrayIndexOutOfBoundsException e1) {}
			}
		});
		btnAddToCart.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnAddToCart.setBounds(340, 45, 117, 29);
		frame.getContentPane().add(btnAddToCart);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnExit.setBounds(340, 6, 117, 29);
		frame.getContentPane().add(btnExit);
		
		JLabel lblYourCart = new JLabel("Your Wishlist");
		lblYourCart.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblYourCart.setBounds(6, 10, 198, 25);
		frame.getContentPane().add(lblYourCart);
		
		wishlistTable = new JTable(tableModel);
		wishlistTable.setBounds(6, 47, 322, 225);
		scrollPane = new JScrollPane(wishlistTable);
		scrollPane.setBounds(6, 47, 322, 225);
		frame.getContentPane().add(scrollPane);
		
		JButton btnRemoveMovie = new JButton("Remove Movie");
		btnRemoveMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Global.wishlist.removeFromWishlist(wishlistTable.getSelectedRow());
					tableModel.removeRow(wishlistTable.getSelectedRow());
					wishlistTable.clearSelection();
					wishlistMovies = Global.wishlist.toMovieArray();
					populateTable();
				} catch (ArrayIndexOutOfBoundsException e1) {}
			}
		});
		btnRemoveMovie.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnRemoveMovie.setBounds(340, 105, 117, 29);
		frame.getContentPane().add(btnRemoveMovie);
		
		JButton btnViewCart = new JButton("View Cart");
		btnViewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CartScreen cartScreen = new CartScreen();
				frame.dispose();
			}
		});
		btnViewCart.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnViewCart.setBounds(340, 75, 117, 29);
		frame.getContentPane().add(btnViewCart);
	}

}
