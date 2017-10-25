import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MovieBrowseScreen {
	
	// Movie placeholders
	private static String[] nameOfMovies = {"Predator", "RoboCob", "IT", "Interstellar"};
	private static String ratingOfMovies = "R";
	private static String[] directorOfMovies = {"John McTiernan", "Paul Verhoeven", "Andrés Muschietti", "Christopher Nolan"};
	private static String[] actorsOfMovies = {"Kanye West", "Matthew McConnahay", "Taylor Swift"};
	private static String[] moviePosterURL = {"MoviePosters/predator.jpg", "MoviePosters/robocop.jpg", "MoviePosters/it.jpg", "MoviePosters/interstellar.jpg"};
	
	private static Movie movie1;
	private static Movie movie2;
	private static Movie movie3;
	private static Movie movie4;

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieBrowseScreen window = new MovieBrowseScreen();
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
	public MovieBrowseScreen() {
		importMovies();
		initialize();
	}
	
	// Imports movie information from database(eventually)
	public void importMovies() {
		movie1 = new Movie(nameOfMovies[0], ratingOfMovies, directorOfMovies[0], actorsOfMovies, moviePosterURL[0]);
		movie2 = new Movie(nameOfMovies[1], ratingOfMovies, directorOfMovies[1], actorsOfMovies, moviePosterURL[1]);
		movie3 = new Movie(nameOfMovies[2], ratingOfMovies, directorOfMovies[2], actorsOfMovies, moviePosterURL[2]);
		movie4 = new Movie(nameOfMovies[3], ratingOfMovies, directorOfMovies[3], actorsOfMovies, moviePosterURL[3]);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnMovie1 = new JButton(movie1.getName());
		btnMovie1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectedMovieScreen selMovie = new SelectedMovieScreen(movie1);
			}
		});
		btnMovie1.setBounds(107, 102, 100, 146);
		frame.getContentPane().add(btnMovie1);
		
		JButton btnMovie2 = new JButton(movie2.getName());
		btnMovie2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectedMovieScreen selMovie = new SelectedMovieScreen(movie2);
			}
		});
		btnMovie2.setBounds(219, 102, 100, 146);
		frame.getContentPane().add(btnMovie2);
		
		JButton btnMovie3 = new JButton(movie3.getName());
		btnMovie3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectedMovieScreen selMovie = new SelectedMovieScreen(movie3);
			}
		});
		btnMovie3.setBounds(331, 102, 100, 146);
		frame.getContentPane().add(btnMovie3);
		
		JButton btnMovie4 = new JButton(movie4.getName());
		btnMovie4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectedMovieScreen selMovie = new SelectedMovieScreen(movie4);
			}
		});
		btnMovie4.setBounds(443, 102, 100, 146);
		frame.getContentPane().add(btnMovie4);
		
		JButton btnBack = new JButton("<");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(6, 161, 68, 29);
		frame.getContentPane().add(btnBack);
		
		JButton btnForward = new JButton(">");
		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnForward.setBounds(576, 161, 68, 29);
		frame.getContentPane().add(btnForward);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(501, 41, 100, 29);
		frame.getContentPane().add(btnSearch);
		
		textField = new JTextField();
		textField.setBounds(359, 41, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
