import java.util.ArrayList;

public class Wishlist {
	private ArrayList<Movie> wishlistMovies = new ArrayList<Movie>();
	
	public void addToWishlist(Movie addedMovie) {
		wishlistMovies.add(addedMovie);
	}
	
	public void removeFromWishlist(int index) {
		wishlistMovies.remove(index);
	}
	
	public void addToCart(int index) {
		Global.cart.addToCart(wishlistMovies.get(index));
		wishlistMovies.remove(index);
	}
	
	public int numOfMovies() {
		return wishlistMovies.size();
	}
	
	// Puts all movies in cart into a Movie array to get the information from the movie object
	public Movie[] toMovieArray() {
		Movie[] movies = wishlistMovies.toArray(new Movie[wishlistMovies.size()]);
		return movies;
	}
}
