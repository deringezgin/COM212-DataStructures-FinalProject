/*
Wishlist to keep track of the last 20 movies that the user watched.
We used a queue to implement this functionality
*/
package structures;

import java.io.Serializable;

public class Wishlist implements Serializable {
    private Movie[] wishList;
    private int filmCount;
    private int firstMovieIndex;
    private final int size = 20;

    public Wishlist() {
        wishList = new Movie[size];
        filmCount = 0;
        firstMovieIndex = 0;
    }

    public Movie getFirstMovie() {
        // Function to get the first movie in the wishlist. If the movie is not available, deletes it and returns the next movie
        if (filmCount == 0) {
            return null;
        }

        Movie movie = wishList[firstMovieIndex];
        if (!movie.getAvailability()) {
            System.out.println("The first movie is not available in the system anymore.");
            System.out.println("Accessing the next movie...");
            deleteFirstMovie();
            return getFirstMovie();
        }
        return movie;
    }

    public void deleteFirstMovie() {
        // Function to delete the first movie in the wishlist
        firstMovieIndex = (firstMovieIndex + 1) % size;
        filmCount -= 1;
    }

    public void addMovie(Movie movie) {
        // Function to add a movie to the wishlist
//        if(filmCount == size) {
//        }
        wishList[(firstMovieIndex + filmCount) % size] = movie;
        filmCount += 1;
    }
}
