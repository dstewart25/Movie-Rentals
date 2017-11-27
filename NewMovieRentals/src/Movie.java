import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;

public class Movie {
    // Movie information declarations
    private String nameOfMovie;
    private String moviePosterURL;
    private ImageIcon moviePoster;
    private double priceOfMovie;

    public String getName() {
        return nameOfMovie;
    }

    // importing poster image for the movie
    public ImageIcon getImage() {
        BufferedImage img = new BufferedImage(130, 170, BufferedImage.TYPE_4BYTE_ABGR);
        try {
            img = ImageIO.read(new File(moviePosterURL));
        } catch (IOException e) {
            System.err.println("Image not found.");
            System.exit(1);
        }

        moviePoster = new ImageIcon(img);

        Image image = moviePoster.getImage(); // transform it
        Image resizedImage = image.getScaledInstance(130, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        moviePoster = new ImageIcon(resizedImage);  // transform it back

        return moviePoster;
    }

    public double getPrice() {
        return priceOfMovie;
    }

    public Movie(String name, String posterURL, double price) {
        nameOfMovie = name;
        moviePosterURL = posterURL;
        priceOfMovie = price;
    }
}