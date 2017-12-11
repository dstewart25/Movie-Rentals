import java.util.ArrayList;

public class Cart {
    private ArrayList<Movie> cartMovies = new ArrayList<Movie>();

    public void addToCart(Movie addedMovie) {
        cartMovies.add(addedMovie);
    }

    public void removeFromCart(int index) {
        cartMovies.remove(index);
    }

    public void clearCart() {
        cartMovies.clear();
    }

    public int numOfMovies() {
        return cartMovies.size();
    }

    // Puts all movies in cart into a Movie array to get the information from the movie object
    public Movie[] toMovieArray() {
        Movie[] movies = cartMovies.toArray(new Movie[cartMovies.size()]);
        return movies;
    }
}