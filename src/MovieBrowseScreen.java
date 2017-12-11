import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class MovieBrowseScreen extends JPanel {
    private JFrame frame;

    private static int index1 = 0; // The index to find which movie was selected
    private static int index2 = 1;
    private static int index3 = 2;
    private static int index4 = 3;

    // Array to hold different sort options
    String[] sortOptions = {"Title", "Release Date", "Genre", "Director", "Rating"};
    private static int sortIndex;

    private static String searchTxt; // Holds text from the search field

    public MovieBrowseScreen(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
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

        // Sub-panel for inside buttons
        JPanel titlePanel1 = new JPanel();
        titlePanel1.setLayout(new GridLayout(3,1));
        titlePanel1.setBackground(Color.WHITE);
        JPanel titlePanel2 = new JPanel();
        titlePanel2.setLayout(new GridLayout(3,1));
        titlePanel2.setBackground(Color.WHITE);
        JPanel titlePanel3 = new JPanel();
        titlePanel3.setLayout(new GridLayout(3,1));
        titlePanel3.setBackground(Color.WHITE);
        JPanel titlePanel4 = new JPanel();
        titlePanel4.setLayout(new GridLayout(3,1));
        titlePanel4.setBackground(Color.WHITE);

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
                // Removing movie browse screen and showing cart screen
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
                // Removing movie browse screen and showing wishlist screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new WishlistScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        JButton historyBtn = new JButton("Rental History");
        c.gridx = 3;
        topPanel.add(historyBtn, c);
        historyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing movie browse screen and showing rental history screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new RentalHistoryScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Creating sort combo box and label
        JLabel sortLabel = new JLabel("Sort By:");
        c.gridy = 1;
        c.gridx = 0;
        topPanel.add(sortLabel, c);
        JComboBox sortComboBox = new JComboBox(sortOptions);
        sortComboBox.setSelectedIndex(sortIndex);
        c.gridx = 1;
        topPanel.add(sortComboBox, c);
        sortComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sortComboBox.getSelectedItem() == "Title") {
                    sortByTitle();
                    sortIndex = 0;
                    // Refreshing movie browse screen to show new sort
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new MovieBrowseScreen(frame));
                    frame.pack();
                    frame.getContentPane().setVisible(true);
                } else if (sortComboBox.getSelectedItem() == "Genre") {
                    sortByGenre();
                    sortIndex = 2;
                    // Refreshing movie browse screen to show new sort
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new MovieBrowseScreen(frame));
                    frame.pack();
                    frame.getContentPane().setVisible(true);
                } else if (sortComboBox.getSelectedItem() == "Release Date") {
                    sortByReleaseDate();
                    sortIndex = 1;
                    // Refreshing movie browse screen to show new sort
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new MovieBrowseScreen(frame));
                    frame.pack();
                    frame.getContentPane().setVisible(true);
                } else if (sortComboBox.getSelectedItem() == "Director") {
                    sortByDirector();
                    sortIndex = 3;
                    // Refreshing movie browse screen to show new sort
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new MovieBrowseScreen(frame));
                    frame.pack();
                    frame.getContentPane().setVisible(true);
                } else if (sortComboBox.getSelectedItem() == "Rating") {
                    sortByRating();
                    sortIndex = 4;
                    // Refreshing movie browse screen to show new sort
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new MovieBrowseScreen(frame));
                    frame.pack();
                    frame.getContentPane().setVisible(true);
                }
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
        c.gridx = 4;
        c.gridy = 0;
        topPanel.add(searchField, c);
        JButton searchBtn = new JButton("Search");
        c.insets.left = 0;
        c.gridx = 5;
        topPanel.add(searchBtn, c);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Search in movie database when button is clicked
            }
        });

        // Creating back button for scrolling through movies
        JButton backBtn = new JButton("<");
        if (index1 == 0) {
            backBtn.setEnabled(false);
        }
        backBtn.setPreferredSize(new Dimension(68,29));
        c.weightx = 40;
        c.gridx = 0;
        moviePanel.add(backBtn, c);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backButtonPressed();
                // Refreshing movie browse screen to show new movies
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new MovieBrowseScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        JButton movie1Btn = new JButton();
        movie1Btn.setLayout(new BorderLayout());
        if (movieTitleTooLong(Global.movie[index1].getTitle())) {
            String[] movieTitle = {Global.movie[index1].getTitle().substring(0,18),
                    Global.movie[index1].getTitle().substring(18, Global.movie[index1].getTitle().length())};
            titlePanel1.add(new JLabel(movieTitle[0]));
            if (movieTitleTooLong(movieTitle[1])) {
                String[] subMovieTitle = {movieTitle[1].substring(0,18),
                        movieTitle[1].substring(18, movieTitle[1].length())};
                titlePanel1.add(new JLabel(subMovieTitle[0]));
                titlePanel1.add(new JLabel(subMovieTitle[1]));
            } else {
                titlePanel1.add(new JLabel(movieTitle[1]));
                titlePanel1.add(new JLabel());
            }
            movie1Btn.add(BorderLayout.NORTH, titlePanel1);
        } else {
            titlePanel1.add(new JLabel(Global.movie[index1].getTitle()));
            titlePanel1.add(new JLabel());
            movie1Btn.add(BorderLayout.NORTH, titlePanel1);
        }
        movie1Btn.add(BorderLayout.CENTER, new JLabel(Global.movie[index1].getButtonPoster()));
        movie1Btn.add(BorderLayout.SOUTH, new JLabel(Global.movie[index1].getGenre()));
        movie1Btn.setPreferredSize(new Dimension(150,250));
        c.weightx = 5;
        c.gridx = 1;
        moviePanel.add(movie1Btn, c);
        movie1Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Removing movie browse screen and showing the selected movie screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new SelectedMovieScreen(frame, Global.movie[index1]));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });
        JButton movie2Btn = new JButton();
        movie2Btn.setLayout(new BorderLayout());
        if (movieTitleTooLong(Global.movie[index2].getTitle())) {
            String[] movieTitle = {Global.movie[index2].getTitle().substring(0,18),
                    Global.movie[index2].getTitle().substring(18, Global.movie[index2].getTitle().length())};
            titlePanel2.add(new JLabel(movieTitle[0]));
            if (movieTitleTooLong(movieTitle[1])) {
                String[] subMovieTitle = {movieTitle[1].substring(0,18),
                        movieTitle[1].substring(18, movieTitle[1].length())};
                titlePanel2.add(new JLabel(subMovieTitle[0]));
                titlePanel2.add(new JLabel(subMovieTitle[1]));
            } else {
                titlePanel2.add(new JLabel(movieTitle[1]));
                titlePanel2.add(new JLabel());
            }
            movie2Btn.add(BorderLayout.NORTH, titlePanel2);
        } else {
            titlePanel2.add(new JLabel(Global.movie[index2].getTitle()));
            titlePanel2.add(new JLabel());
            movie2Btn.add(BorderLayout.NORTH, titlePanel2);
        }
        movie2Btn.add(BorderLayout.CENTER, new JLabel(Global.movie[index2].getButtonPoster()));
        movie2Btn.add(BorderLayout.SOUTH, new JLabel(Global.movie[index2].getGenre()));
        movie2Btn.setPreferredSize(new Dimension(150,250));
        c.gridx = 2;
        moviePanel.add(movie2Btn, c);
        movie2Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Removing movie browse screen and showing the selected movie screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new SelectedMovieScreen(frame, Global.movie[index2]));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });
        JButton movie3Btn = new JButton();
        movie3Btn.setLayout(new BorderLayout());
        if (movieTitleTooLong(Global.movie[index3].getTitle())) {
            String[] movieTitle = {Global.movie[index3].getTitle().substring(0,18),
                    Global.movie[index3].getTitle().substring(18, Global.movie[index3].getTitle().length())};
            titlePanel3.add(new JLabel(movieTitle[0]));
            if (movieTitleTooLong(movieTitle[1])) {
                String[] subMovieTitle = {movieTitle[1].substring(0,18),
                        movieTitle[1].substring(18, movieTitle[1].length())};
                titlePanel3.add(new JLabel(subMovieTitle[0]));
                titlePanel3.add(new JLabel(subMovieTitle[1]));
            } else {
                titlePanel3.add(new JLabel(movieTitle[1]));
                titlePanel3.add(new JLabel());
            }
            movie3Btn.add(BorderLayout.NORTH, titlePanel3);
        } else {
            titlePanel3.add(new JLabel(Global.movie[index3].getTitle()));
            titlePanel3.add(new JLabel());
            movie3Btn.add(BorderLayout.NORTH, titlePanel3);
        }
        movie3Btn.add(BorderLayout.CENTER, new JLabel(Global.movie[index3].getButtonPoster()));
        movie3Btn.add(BorderLayout.SOUTH, new JLabel(Global.movie[index3].getGenre()));
        movie3Btn.setPreferredSize(new Dimension(150,250));
        c.gridx = 3;
        moviePanel.add(movie3Btn, c);
        movie3Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Removing movie browse screen and showing the selected movie screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new SelectedMovieScreen(frame, Global.movie[index3]));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });
        JButton movie4Btn = new JButton();
        movie4Btn.setLayout(new BorderLayout());
        if (movieTitleTooLong(Global.movie[index4].getTitle())) {
            String[] movieTitle = {Global.movie[index4].getTitle().substring(0,18),
                    Global.movie[index4].getTitle().substring(18, Global.movie[index4].getTitle().length())};
            titlePanel4.add(new JLabel(movieTitle[0]));
            if (movieTitleTooLong(movieTitle[1])) {
                String[] subMovieTitle = {movieTitle[1].substring(0,18),
                        movieTitle[1].substring(18, movieTitle[1].length())};
                titlePanel4.add(new JLabel(subMovieTitle[0]));
                titlePanel4.add(new JLabel(subMovieTitle[1]));
            } else {
                titlePanel4.add(new JLabel(movieTitle[1]));
                titlePanel4.add(new JLabel());
            }
            movie4Btn.add(BorderLayout.NORTH, titlePanel4);
        } else {
            titlePanel4.add(new JLabel(Global.movie[index4].getTitle()));
            titlePanel4.add(new JLabel());
            movie4Btn.add(BorderLayout.NORTH, titlePanel4);
        }
        movie4Btn.add(BorderLayout.CENTER, new JLabel(Global.movie[index4].getButtonPoster()));
        movie4Btn.add(BorderLayout.SOUTH, new JLabel(Global.movie[index4].getGenre()));
        movie4Btn.setPreferredSize(new Dimension(150,250));
        c.gridx = 4;
        moviePanel.add(movie4Btn, c);
        movie4Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Removing movie browse screen and showing the selected movie screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new SelectedMovieScreen(frame, Global.movie[index4]));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Creating forward button to scroll through movies
        JButton forwardBtn = new JButton(">");
        if (index4 == 74) {
            forwardBtn.setEnabled(false);
        }
        forwardBtn.setPreferredSize(new Dimension(68,29));
        c.weightx = 40;
        c.gridx = 5;
        moviePanel.add(forwardBtn, c);
        forwardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forwardButtonPressed();
                // Refreshing movie browse screen to show new movies
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new MovieBrowseScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Adding sub-panels to the main panel
        add(topPanel, BorderLayout.NORTH);
        add(moviePanel, BorderLayout.CENTER);
    }

    private void sortMoviesByDate() {

    }

    private boolean movieTitleTooLong(String movieTitle) {
        if (movieTitle.length() > 20) {
            return true;
        } else {
            return false;
        }
    }

    private void backButtonPressed() {
        index1--;
        index2--;
        index3--;
        index4--;
    }

    private void forwardButtonPressed() {
        index1++;
        index2++;
        index3++;
        index4++;
    }

    public void sortByReleaseDate() {
        Arrays.sort(Global.movie, Movie::compareReleaseDates);
    }

    public void sortByTitle() {
        Arrays.sort(Global.movie, Movie::compareTitle);
    }

    public void sortByGenre() {
        Arrays.sort(Global.movie, Movie::compareGenre);
    }

    public void sortByDirector() {
        Arrays.sort(Global.movie, Movie::compareDirector);
    }

    public void sortByRating() {
        Arrays.sort(Global.movie, Movie::compareRating);
    }
}
