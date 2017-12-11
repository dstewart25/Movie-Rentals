import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;

public class EditSelectedMovieScreen extends JPanel {
    private JFrame frame;
    private Movie movie;
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
    private JButton editMovieBtn;

    public EditSelectedMovieScreen(JFrame frame, Movie movie) {
        this.frame = frame; // Setting main frame equal to this frame
        this.movie = movie;
        setLayout(new GridLayout(10,2));
        initialize(); // Initializing the view
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));      
    }

    // Fill the panel
    private void initialize() {
        frame.setTitle("Edit Selected Movie"); // Setting the title of the frame

        // Creating movie title label and text field
        movieNameLbl = new JLabel("Movie Title:");
        add(movieNameLbl);
        movieNameField = new JTextField();
        movieNameField.setColumns(10);
        movieNameField.setText(movie.getTitle());
        add(movieNameField);

        // Creating poster url label and text field
        posterUrlLbl = new JLabel("Poster URL:");
        add(posterUrlLbl);
        posterUrlField = new JTextField();
        posterUrlField.setColumns(10);
        posterUrlField.setText(movie.getPosterURL());
        add(posterUrlField);
        
        // Creating price label and text field
        priceLbl = new JLabel("Price:");
        add(priceLbl);
        priceField = new JTextField();
        priceField.setColumns(10);
        priceField.setText(String.valueOf(movie.getPrice()));
        add(priceField);

        // Creating director label and text field
        directorLbl = new JLabel("Director:");
        add(directorLbl);
        directorField = new JTextField();
        directorField.setColumns(10);
        directorField.setText(movie.getDirector());
        add(directorField);

        // Creating release year label and text field
        releasedYearLbl = new JLabel("Release Year:");
        add(releasedYearLbl);
        releasedYearField = new JTextField();
        releasedYearField.setColumns(10);
        releasedYearField.setText(String.valueOf(movie.getReleaseDate()));
        add(releasedYearField);

        // Creating genre label and text field
        genreLbl = new JLabel("Genre:");
        add(genreLbl);
        genreField = new JTextField();
        genreField.setColumns(10);
        genreField.setText(movie.getGenre());
        add(genreField);

        // Creating rating label and text field
        ratingLbl = new JLabel("Rating:");
        add(ratingLbl);
        ratingField = new JTextField();
        ratingField.setColumns(10);
        ratingField.setText(movie.getRating());
        add(ratingField);
        
         // Creating actors label and text field
        actorsLbl = new JLabel("Actors:");
        add(actorsLbl);
        actorsField = new JTextField();
        actorsField.setColumns(10);
        String[] actors = movie.getActors();
        String actorsStr = "";
        for (int i=0; i<actors.length-2; i++) {
            actorsStr += actors[i] + ", ";
        }
        actorsStr += actors[actors.length-1];
        actorsField.setText(actorsStr);
        add(actorsField);
        
         // Creating description label and text field
        descriptionLbl = new JLabel("Description:");
        add(descriptionLbl);
        descriptionField = new JTextField();
        descriptionField.setColumns(10);
        descriptionField.setText(movie.getDescription());
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

        // Creating edit movie button to change movie details
        editMovieBtn = new JButton("Save Changes to Movie");
        add(editMovieBtn);
        editMovieBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                movie.setTitle(movieNameField.getText());
                movie.setPosterURL(posterUrlField.getText());
                movie.setPrice(priceField.getText());
                movie.setDirector(directorField.getText());
                movie.setReleaseDate(releasedYearField.getText());
                movie.setGenre(genreField.getText());
                movie.setRating(ratingField.getText());
                movie.setActorsString(actorsField.getText());
                movie.setDescription(descriptionField.getText());

                // Removing new movie screen and showing edit movie browse screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new EditMovieBrowseScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });
    }
}
