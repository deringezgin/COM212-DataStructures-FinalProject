package mainStructures;

import java.io.Serializable;

// COM212 Data Structures | Spring 2024 | Final Project
// Derin Gezgin | Dimitrios Seremetis | Johnny Andreasen | Nick Essery
// Wishlist to keep track of the last 20 movies that the user watched.
// We used a queue to implement this functionality

public class Wishlist implements Serializable {
    private static final long serialVersionUID = 12345678910L;
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

        return wishList[firstMovieIndex];
    }

    public void deleteFirstMovie() {
        // Function to delete the first movie in the wishlist
        firstMovieIndex = (firstMovieIndex + 1) % size;
        filmCount -= 1;
    }

    public boolean addMovie(Movie movie) {
        if (filmCount == size) {
            // If the wishlist is full
            return false;
        }
        // Function to add a movie to the wishlist
        wishList[(firstMovieIndex + filmCount) % size] = movie;
        filmCount += 1;
        return true;
    }
}
