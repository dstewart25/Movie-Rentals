import java.util.ArrayList;

public class History {
    private ArrayList<Movie> historyMovies = new ArrayList<Movie>();

    public void addToHistory(Movie addedMovie) {
        historyMovies.add(addedMovie);
    }

    public void removeFromHistory(int index) {
        historyMovies.remove(index);
    }

    public int numOfMovies() {
        return historyMovies.size();
    }

    // Puts all movies in cart into a Movie array to get the information from the movie object
    public Movie[] toMovieArray() {
        Movie[] movies = historyMovies.toArray(new Movie[historyMovies.size()]);
        return movies;
    }
}