import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Movie {
	// Movie information delcarations
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
