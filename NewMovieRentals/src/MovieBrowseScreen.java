import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MovieBrowseScreen extends JPanel {
    private JFrame frame;

    private Movie[] rentals = new Movie[100]; // Holds all of the movies from the database
    private static int index = 0; // The index to find which movie was selected

    private static String searchTxt; // Holds text from the search field

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
        c.insets.left = 100;
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
        JButton movie1Btn = new JButton(rentals[0].getTitle());
        movie1Btn.setPreferredSize(new Dimension(135,200));
        c.weightx = 5;
        c.gridx = 1;
        moviePanel.add(movie1Btn, c);
        movie1Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Removing movie browse screen and showing the selected movie screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new SelectedMovieScreen(frame, rentals[0]));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });
        JButton movie2Btn = new JButton(rentals[1].getTitle());
        movie2Btn.setPreferredSize(new Dimension(135,200));
        c.gridx = 2;
        moviePanel.add(movie2Btn, c);
        movie2Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Removing movie browse screen and showing the selected movie screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new SelectedMovieScreen(frame, rentals[1]));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });
        JButton movie3Btn = new JButton(rentals[2].getTitle());
        movie3Btn.setPreferredSize(new Dimension(135,200));
        c.gridx = 3;
        moviePanel.add(movie3Btn, c);
        movie3Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Removing movie browse screen and showing the selected movie screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new SelectedMovieScreen(frame, rentals[2]));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });
        JButton movie4Btn = new JButton(rentals[3].getTitle());
        movie4Btn.setPreferredSize(new Dimension(135,200));
        c.gridx = 4;
        moviePanel.add(movie4Btn, c);
        movie4Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Removing movie browse screen and showing the selected movie screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new SelectedMovieScreen(frame, rentals[3]));
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

    // Imports movie information from database
    private void importMovies() {
        BufferedReader reader = null;
        try {
            // Pointing the reader at the movie database
            reader = new BufferedReader(new FileReader("MovieDatabase/MovieDatabase.txt"));

            // Reading the database line by line
            String line;
            int i = 0; // The index of which movie is currently being imported
            while ((line = reader.readLine()) != null) {
                String[] splits = line.split(": ");

                if (splits[0].equals("Movie")) { // Getting movie title
                    rentals[i] = new Movie();
                    rentals[i].setTitle(splits[1]);
                } else if (splits[0].equals("Date Released")) { // Getting release date
                    rentals[i].setReleaseDate(splits[1]);
                } else if (splits[0].equals("Director")) { // Getting director
                    rentals[i].setDirector(splits[1]);
                } else if (splits[0].equals("Actors")) { // Getting main actors
                    String[] subSplits = splits[1].split(", ");
                    for (int j=0; j<subSplits.length; j++) {
                        rentals[i].setActors(subSplits);
                    }
                } else if (splits[0].equals("Rating")) { // Getting rating
                    rentals[i].setRating(splits[1]);
                } else if (splits[0].equals("Genre")) { // Getting genres
                    String[] subSplits = splits[1].split(", ");
                    for (int j=0; j<subSplits.length; j++) {
                        rentals[i].setGenre(subSplits);
                    }
                } else if (splits[0].equals("Poster URL")) {
                    rentals[i].setPosterURL(splits[1]);
                } else if (splits[0].equals("Price")) {
                    rentals[i].setPrice(splits[1]);
                } else if (splits[0].equals("Description")) { // Getting description
                    rentals[i].setDescription(splits[1]);

                    // Since description is the last item imported per movie
                    // we can increase index to next movie
                    i++;
                }
            }
        } catch (IOException e) {}
        finally {
            try {
                reader.close();
            } catch (NullPointerException e) {
            } catch (IOException e) {}
        }
    }
}
