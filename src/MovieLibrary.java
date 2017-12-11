public class MovieLibrary {
    
    public static Movie[] myMovies = new Movie[100];
    private static int numMovies = -1;   
      
    // return the static number of movies in MovieLibrary
    public int getNumOfMovies(){
        return numMovies;
    }
    
    // add movie to the static array of movie library movies
    public void addMovie(String nameOfMovie, String moviePosterURL, String priceOfMovie,
                                String director, String releasedYear, String genre, String rating,
                                String actor, String description){
 
        numMovies++;
 
        myMovies[numMovies] = (new Movie(nameOfMovie, moviePosterURL, releasedYear,
                                            director, actor, rating, genre,
                                            description, priceOfMovie));
    }
    
}
