import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectedMovieScreen {

	private JFrame frame;
	private static Movie movie;
	Color txtBGColor = new Color(238, 238, 238);

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
	public SelectedMovieScreen(Movie importedMovie) {
		movie = importedMovie;
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
		btnAddToCart.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnAddToCart.setBounds(327, 67, 117, 29);
		frame.getContentPane().add(btnAddToCart);
		
		JButton btnAddToWishlist = new JButton("Add to Wishlist");
		btnAddToWishlist.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnAddToWishlist.setBounds(327, 96, 117, 29);
		frame.getContentPane().add(btnAddToWishlist);
		
		JButton btnViewCart = new JButton("View Cart");
		btnViewCart.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnViewCart.setBounds(327, 124, 117, 29);
		frame.getContentPane().add(btnViewCart);
	}
}
