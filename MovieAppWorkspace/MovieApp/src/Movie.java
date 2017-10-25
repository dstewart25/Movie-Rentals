import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Movie {
	// Movie information delcarations
	private String nameOfMovie;
	private String ratingOfMovie;
	private String directorOfMovie;
	private String[] actorsInMovie;
	private String moviePosterURL;
	private ImageIcon moviePoster;
	
	public String getName() {
		return nameOfMovie;
	}
	
	public String getRating() {
		return ratingOfMovie;
	}
	
	public String getDirector() {
		return directorOfMovie;
	}
	
	public String[] getActors() {
		return actorsInMovie;
	}
	
	public ImageIcon getImage() {
		BufferedImage img = new BufferedImage(130, 170, BufferedImage.TYPE_4BYTE_ABGR);
		try {
			img = ImageIO.read(new File(moviePosterURL));
		} catch (IOException e) {
			System.err.println("Image not found.");
			System.exit(1);
		}
 		
		moviePoster = new ImageIcon(img);
		
		return moviePoster;
	}
	
	public Movie(String name, String rating, String director, String[] actors, String posterURL) {
		nameOfMovie = name;
		ratingOfMovie = rating;
		directorOfMovie = director;
		actorsInMovie = actors;
		moviePosterURL = posterURL;
	}
}
