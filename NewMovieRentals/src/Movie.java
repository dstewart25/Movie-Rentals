import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;

public class Movie {
    // Movie information declarations
    private String title;
    private String posterURL;
    private String releaseDate;
    private String director;
    private String[] actors;
    private String rating;
    private String[] genre;
    private String description;
    private ImageIcon selectedMoviePoster;
    private ImageIcon buttonPoster;
    private double price;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public String[] getActors() {
        return actors;
    }

    public String getRating() {
        return rating;
    }

    public String[] getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getPosterURL() {
        return posterURL;
    }

    // Importing poster image for the movie
    private ImageIcon getImage() {
        BufferedImage img = new BufferedImage(674, 1000, BufferedImage.TYPE_4BYTE_ABGR);
        try {
            img = ImageIO.read(new File(posterURL));
        } catch (IOException e) {
            System.err.println("Image not found.");
            System.exit(1);
        }

        ImageIcon image = new ImageIcon(img);
        return image;
    }

    // Resizing movie poster for the selected movie screen
    public ImageIcon getSelectedMoviePoster() {
        selectedMoviePoster = getImage();

        Image image = selectedMoviePoster.getImage(); // transform it
        Image resizedImage = image.getScaledInstance(168, 250,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        selectedMoviePoster = new ImageIcon(resizedImage);  // transform it back

        return selectedMoviePoster;
    }

    // Resizing movie poster to show on buttons for the movie browse screen
    public ImageIcon getButtonPoster() {
        buttonPoster = getImage();

        Image image = buttonPoster.getImage(); // transform it
        Image resizedImage = image.getScaledInstance(135, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        buttonPoster = new ImageIcon(resizedImage);  // transform it back

        return buttonPoster;
    }
}