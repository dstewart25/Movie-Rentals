import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MovieBrowseScreen extends JPanel {
    private JFrame frame;

    // Movie placeholders
    private static String[] nameOfMovies = {"Predator", "RoboCop", "IT", "Interstellar"};
    private static String[] moviePosterURL = {"MoviePosters/predator.jpg", "MoviePosters/robocop.jpg", "MoviePosters/it.jpg", "MoviePosters/interstellar.jpg"};
    private static double[] priceOfMovies = {9.99, 10.25, 12.90, 3.99};

    private static Movie movie1;
    private static Movie movie2;
    private static Movie movie3;
    private static Movie movie4;

    private static String searchTxt;

    public MovieBrowseScreen(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        importMovies();
        initialize();
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    // Fill the panel
    public void initialize() {
        frame.setTitle("Browse"); // Setting title of the frame

        // Constraints to use for formatting the panel
        GridBagConstraints c = new GridBagConstraints();

        // Creating sub-panels to hold components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new GridBagLayout());

        // Creating logout button
        JButton logoutBtn = new JButton("Log Out");
        c.weightx = 5;
        topPanel.add(logoutBtn, c);
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing movie browse screen and showing main login screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new LoginScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Creating view cart button
        JButton viewCartBtn = new JButton("View Cart");
        c.gridx = 1;
        topPanel.add(viewCartBtn, c);
        viewCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing selected movie screen and showing cart screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new CartScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Creating view wishlist button
        JButton viewWishlistBtn = new JButton("View Wishlist");
        c.gridx = 2;
        topPanel.add(viewWishlistBtn, c);
        viewWishlistBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing selected movie screen and showing wishlist screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new WishlistScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Creating search field and button
        JTextField searchField = new JTextField();
        searchField.setColumns(10);
        searchTxt = searchField.getText();
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // If 'ENTER' key is pressed then search
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                }
            }
        });
        c.insets.left = 30;
        c.gridx = 3;
        topPanel.add(searchField, c);
        JButton searchBtn = new JButton("Search");
        c.insets.left = 0;
        c.gridx = 4;
        topPanel.add(searchBtn, c);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Search in movie database when button is clicked
            }
        });

        // Creating back button for scrolling through movies
        JButton backBtn = new JButton("<");
        backBtn.setPreferredSize(new Dimension(68,29));
        c.weightx = 40;
        c.gridx = 0;
        moviePanel.add(backBtn, c);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        // Creating movie buttons for browsing movies
        JButton movie1Btn = new JButton(movie1.getName());
        movie1Btn.setPreferredSize(new Dimension(100,146));
        c.weightx = 5;
        c.gridx = 1;
        moviePanel.add(movie1Btn, c);
        movie1Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Removing movie browse screen and showing the selected movie screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new SelectedMovieScreen(frame, movie1));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });
        JButton movie2Btn = new JButton(movie2.getName());
        movie2Btn.setPreferredSize(new Dimension(100,146));
        c.gridx = 2;
        moviePanel.add(movie2Btn, c);
        movie2Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Removing movie browse screen and showing the selected movie screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new SelectedMovieScreen(frame, movie2));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });
        JButton movie3Btn = new JButton(movie3.getName());
        movie3Btn.setPreferredSize(new Dimension(100,146));
        c.gridx = 3;
        moviePanel.add(movie3Btn, c);
        movie3Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Removing movie browse screen and showing the selected movie screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new SelectedMovieScreen(frame, movie3));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });
        JButton movie4Btn = new JButton(movie4.getName());
        movie4Btn.setPreferredSize(new Dimension(100,146));
        c.gridx = 4;
        moviePanel.add(movie4Btn, c);
        movie4Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Removing movie browse screen and showing the selected movie screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new SelectedMovieScreen(frame, movie4));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Creating forward button to scroll through movies
        JButton forwardBtn = new JButton(">");
        forwardBtn.setPreferredSize(new Dimension(68,29));
        c.weightx = 40;
        c.gridx = 5;
        moviePanel.add(forwardBtn, c);
        forwardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        // Adding sub-panels to the main panel
        add(topPanel, BorderLayout.NORTH);
        add(moviePanel, BorderLayout.CENTER);
    }

    // Adding selected movie to cart
    public void addMovieToCart(Movie movie) {
        Global.cart.addToCart(movie);
    }

    // Adding selected movie to wishlist
    public void addMovieToWishlist(Movie movie) {
        Global.wishlist.addToWishlist(movie);
    }

    // Imports movie information from database(eventually)
    public void importMovies() {
        movie1 = new Movie(nameOfMovies[0], moviePosterURL[0], priceOfMovies[0]);
        movie2 = new Movie(nameOfMovies[1], moviePosterURL[1], priceOfMovies[1]);
        movie3 = new Movie(nameOfMovies[2], moviePosterURL[2], priceOfMovies[2]);
        movie4 = new Movie(nameOfMovies[3], moviePosterURL[3], priceOfMovies[3]);
    }
}
