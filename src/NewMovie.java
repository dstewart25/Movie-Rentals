import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;

public class NewMovie extends JPanel {
    private Movie newMovie = new Movie();
    private JFrame frame;
    private String path = "MovieDatabase/MovieDatabase.txt";
    private JLabel movieNameLbl;
    private JTextField movieNameField;
    private JLabel posterUrlLbl;
    private JTextField posterUrlField;
    private JLabel priceLbl;
    private JTextField priceField;
    private JLabel directorLbl;
    private JTextField directorField;
    private JLabel releasedYearLbl;
    private JTextField releasedYearField;
    private JLabel genreLbl;
    private JTextField genreField;
    private JLabel ratingLbl;
    private JTextField ratingField;
    private JLabel actorsLbl;
    private JTextField actorsField;
    private JLabel descriptionLbl;
    private JTextField descriptionField;
    private JButton cancelBtn;
    private JButton addMovieBtn;

    public NewMovie(JFrame frame) {
        this.frame = frame; // Setting main frame equal to this frame
        setLayout(new GridLayout(10,2));
        initialize(); // Initializing the view
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    // Fill the panel
    private void initialize() {
        frame.setTitle("New Movie"); // Setting the title of the frame

        // Creating movie title label and text field
        movieNameLbl = new JLabel("Movie Title:");
        add(movieNameLbl);
        movieNameField = new JTextField();
        movieNameField.setColumns(10);
        add(movieNameField);

        // Creating poster url label and text field
        posterUrlLbl = new JLabel("Poster URL:");
        add(posterUrlLbl);
        posterUrlField = new JTextField();
        posterUrlField.setColumns(10);
        add(posterUrlField);
        
        // Creating price label and text field
        priceLbl = new JLabel("Price:");
        add(priceLbl);
        priceField = new JTextField();
        priceField.setColumns(10);
        add(priceField);

        // Creating director label and text field
        directorLbl = new JLabel("Director:");
        add(directorLbl);
        directorField = new JTextField();
        directorField.setColumns(10);
        add(directorField);

        // Creating release year label and text field
        releasedYearLbl = new JLabel("Release Year:");
        add(releasedYearLbl);
        releasedYearField = new JTextField();
        releasedYearField.setColumns(10);
        add(releasedYearField);

        // Creating genre label and text field
        genreLbl = new JLabel("Genre:");
        add(genreLbl);
        genreField = new JTextField();
        genreField.setColumns(10);
        add(genreField);

        // Creating rating label and text field
        ratingLbl = new JLabel("Rating:");
        add(ratingLbl);
        ratingField = new JTextField();
        ratingField.setColumns(10);
        add(ratingField);
        
         // Creating actors label and text field
        actorsLbl = new JLabel("Actors:");
        add(actorsLbl);
        actorsField = new JTextField();
        actorsField.setColumns(10);
        add(actorsField);
        
         // Creating description label and text field
        descriptionLbl = new JLabel("Description:");
        add(descriptionLbl);
        descriptionField = new JTextField();
        descriptionField.setColumns(10);
        add(descriptionField);

        // Creating cancel button to take back to admin tasks
        cancelBtn = new JButton("Cancel");
        add(cancelBtn);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing user login screen and showing main login screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new AdminTasks(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Creating add movie button to write user information to a text file
        addMovieBtn = new JButton("Add New Movie");
        add(addMovieBtn);
        addMovieBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Adding attributes to the new movie
                newMovie = new Movie();
                newMovie.setTitle(movieNameField.getText());
                newMovie.setPosterURL(posterUrlField.getText());
                newMovie.setPrice(priceField.getText());
                newMovie.setDirector(directorField.getText());
                newMovie.setReleaseDate(releasedYearField.getText());
                newMovie.setGenre(genreField.getText());
                newMovie.setRating(ratingField.getText());
                newMovie.setActorsString(actorsField.getText());
                newMovie.setDescription(descriptionField.getText());

                // Telling the admin that a new movie has been creating
                JOptionPane.showMessageDialog(
                        null,
                        newMovie.getTitle() + " has been added to the database.",
                        "Movie Added",
                        JOptionPane.PLAIN_MESSAGE
                );

                // Removing new movie screen and showing edit movie browse screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new AdminTasks(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });
    }
}
