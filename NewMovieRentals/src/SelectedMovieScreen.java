import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectedMovieScreen extends JPanel {
    private JFrame frame;
    private static Movie movie;

    private JTextArea descriptionPane;

    public SelectedMovieScreen(JFrame frame, Movie selectedMovie) {
        this.frame = frame;
        movie = selectedMovie;
        setLayout(new BorderLayout());
        initialize();
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    // Fill the panel
    public void initialize() {
        // Setting title of frame to be the name of the selected movie
        frame.setTitle(movie.getTitle());

        // Creating sub-panels to hold components
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        JPanel topRightPanel = new JPanel();
        topRightPanel.setLayout(new BoxLayout(topRightPanel, BoxLayout.Y_AXIS));
        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setLayout(new GridLayout(4,1));
        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new BorderLayout());

        // Creating movie poster image
        JLabel moviePoster = new JLabel();
        moviePoster.setIcon(movie.getSelectedMoviePoster());
        moviePoster.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
        moviePanel.add(moviePoster, BorderLayout.NORTH);
        add(moviePanel, BorderLayout.WEST);

        // Creating description text of the movie
        descriptionPane = new JTextArea();
        descriptionPane.setEditable(false);
        descriptionPane.setLineWrap(true);
        descriptionPane.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionPane,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        descriptionScrollPane.setPreferredSize(new Dimension(250, 350));
        descriptionPane.setBackground(this.getBackground());
        setDescriptionText();
        descriptionScrollPane.setBackground(this.getBackground());
        add(descriptionScrollPane, BorderLayout.CENTER);

        // Creating back button
        JButton backBtn = new JButton("Back");
        backBtn.setAlignmentX(RIGHT_ALIGNMENT);
        topRightPanel.add(backBtn);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing selected movie screen and showing movie browse screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new MovieBrowseScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Creating add to cart button
        JButton addToCartBtn = new JButton("Add to Cart");
        addToCartBtn.setAlignmentX(CENTER_ALIGNMENT);
        addToCartBtn.setPreferredSize(new Dimension(130,29));
        bottomRightPanel.add(addToCartBtn);
        addToCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        null,
                        movie.getTitle() + " was added to your cart",
                        "Movie added to cart",
                        JOptionPane.PLAIN_MESSAGE
                );
                Global.cart.addToCart(movie);
            }
        });

        // Creating add to wishlist button
        JButton addToWishlistBtn = new JButton("Add to Wishlist");
        addToWishlistBtn.setAlignmentX(CENTER_ALIGNMENT);
        addToWishlistBtn.setPreferredSize(new Dimension(130,29));
        bottomRightPanel.add(addToWishlistBtn);
        addToWishlistBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        null,
                        movie.getTitle() + " was added to your wishlist",
                        "Movie added to wishlist",
                        JOptionPane.PLAIN_MESSAGE
                );
                Global.wishlist.addToWishlist(movie);
            }
        });

        // Creating view cart button
        JButton viewCartBtn = new JButton("View Cart");
        viewCartBtn.setAlignmentX(CENTER_ALIGNMENT);
        viewCartBtn.setPreferredSize(new Dimension(130,29));
        bottomRightPanel.add(viewCartBtn);
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
        viewWishlistBtn.setAlignmentX(CENTER_ALIGNMENT);
        viewWishlistBtn.setPreferredSize(new Dimension(130,29));
        bottomRightPanel.add(viewWishlistBtn);
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

        // Adding right sub-panels to right panel
        rightPanel.add(topRightPanel, BorderLayout.NORTH);
        rightPanel.add(bottomRightPanel, BorderLayout.SOUTH);

        // Adding sub-panels to the main panel
        add(rightPanel, BorderLayout.EAST);
    }

    private void setDescriptionText() {
        descriptionPane.setText(movie.getTitle() + " (" + movie.getReleaseDate() + ")\n\n");
        descriptionPane.append("Rating: " + movie.getRating() + "\n\n");
        descriptionPane.append(movie.getDescription() + "\n\n");
        descriptionPane.append("Director: " + movie.getDirector() + "\n");
    }
}
