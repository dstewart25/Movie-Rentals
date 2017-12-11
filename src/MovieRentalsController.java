import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MovieRentalsController extends JFrame {
    public static void main(String[] args) {
        MovieRentalsController frame = new MovieRentalsController();
        frame.setVisible(true);
    }

    public MovieRentalsController() {
        // Setting up the frame
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        JFrame myFrame = this;

        // Importing movies from the text database
        importMovies();

        // Adding login screen to show when program begins
        getContentPane().removeAll();
        getContentPane().add(new MovieBrowseScreen(myFrame), BorderLayout.CENTER);
        pack();
        getContentPane().setVisible(true);
    }

    public void importMovies() {
        BufferedReader reader = null;
        try {
            // Pointing the reader at the movie database
            reader = new BufferedReader(new FileReader("MovieDatabase/MovieDatabase.txt"));

            // Reading the database line by line
            String line;
            int i = 0; // The index of which movie is currently being imported
            while ((line = reader.readLine()) != null) {
                String[] splits = line.split(": ");

                if (splits[0].equals("Title")) { // Getting movie title
                    // Creating a new movie every time we get to a new movie title
                    // Since it is the first piece of information in the database
                    Global.movie[i] = new Movie();

                    Global.movie[i].setTitle(splits[1]);
                } else if (splits[0].equals("Released")) { // Getting release date
                    Global.movie[i].setReleaseDate(splits[1]);
                } else if (splits[0].equals("Director")) { // Getting director
                    Global.movie[i].setDirector(splits[1]);
                } else if (splits[0].equals("Actors")) { // Getting main actors
                    String[] subSplits = splits[1].split(", ");
                    for (int j=0; j<subSplits.length; j++) {
                        Global.movie[i].setActors(subSplits);
                    }
                } else if (splits[0].equals("Rating")) { // Getting rating
                    Global.movie[i].setRating(splits[1]);
                } else if (splits[0].equals("Genre")) { // Getting genres
                    Global.movie[i].setGenre(splits[1]);
                } else if (splits[0].equals("Poster")) {
                    Global.movie[i].setPosterURL("MoviePosters/" + splits[1]);
                } else if (splits[0].equals("Price")) {
                    Global.movie[i].setPrice(splits[1]);
                } else if (splits[0].equals("Description")) { // Getting description
                    Global.movie[i].setDescription(splits[1]);

                    // Since description is the last item imported per movie
                    // we can increase index to next movie
                    i++;
                    Global.currentNumOfMovies++;
                }
            }
            Arrays.sort(Global.movie, Movie::compareTitle);
        } catch (IOException e) {}
        finally {
            try {
                reader.close();
            } catch (NullPointerException e) {
            } catch (IOException e) {}
        }
    }
}
