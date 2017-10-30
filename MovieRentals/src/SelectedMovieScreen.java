import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SelectedMovieScreen {

	private JFrame frame;
	private static Movie movie;
	Color txtBGColor = new Color(238, 238, 238);
	private MovieBrowseScreen movieBrowseScreen = new MovieBrowseScreen();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectedMovieScreen window = new SelectedMovieScreen(movie);
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
	public SelectedMovieScreen(Movie selectedMovie) {
		movie = selectedMovie;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnBack.setBounds(360, 6, 84, 29);
		frame.getContentPane().add(btnBack);
		
		JLabel lblMoviePlaceholder = new JLabel("Movie Placeholder");
		lblMoviePlaceholder.setIcon(movie.getImage());
		lblMoviePlaceholder.setBounds(6, 6, 130, 170);
		frame.getContentPane().add(lblMoviePlaceholder);
		
		JTextPane txtpnNameOfMovie = new JTextPane();
		txtpnNameOfMovie.setEditable(false);
		txtpnNameOfMovie.setText(movie.getName());
		txtpnNameOfMovie.setBounds(147, 19, 99, 16);
		txtpnNameOfMovie.setBackground(txtBGColor);
		frame.getContentPane().add(txtpnNameOfMovie);
		
		JTextPane txtpnDescriptionOfMovie = new JTextPane();
		txtpnDescriptionOfMovie.setEditable(false);
		txtpnDescriptionOfMovie.setText("Description of Movie");
		txtpnDescriptionOfMovie.setBounds(147, 47, 161, 193);
		frame.getContentPane().add(txtpnDescriptionOfMovie);
		
		JButton btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
					null,
					movie.getName() + " was added to your cart",
					"Movie added to cart",
					JOptionPane.PLAIN_MESSAGE
				);
				Global.cart.addToCart(movie);
			}
		});
		btnAddToCart.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnAddToCart.setBounds(327, 67, 117, 29);
		frame.getContentPane().add(btnAddToCart);
		
		JButton btnAddToWishlist = new JButton("Add to Wishlist");
		btnAddToWishlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
					null,
					movie.getName() + " was added to your wishlist",
					"Movie added to wishlist",
					JOptionPane.PLAIN_MESSAGE
				);
				Global.wishlist.addToWishlist(movie);
			}
		});
		btnAddToWishlist.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnAddToWishlist.setBounds(327, 96, 117, 29);
		frame.getContentPane().add(btnAddToWishlist);
		
		JButton btnViewCart = new JButton("View Cart");
		btnViewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CartScreen cartScreen = new CartScreen();
				frame.dispose();
			}
		});
		btnViewCart.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnViewCart.setBounds(327, 124, 117, 29);
		frame.getContentPane().add(btnViewCart);
		
		JButton btnViewWishlist = new JButton("View Wishlist");
		btnViewWishlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WishlistScreen wishlistScreen = new WishlistScreen();
			}
		});
		btnViewWishlist.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnViewWishlist.setBounds(327, 152, 117, 29);
		frame.getContentPane().add(btnViewWishlist);
	}
}
