package mainStructures;

import java.io.Serializable;
import java.util.Objects;

// COM212 Data Structures | Spring 2024 | Final Project
// Derin Gezgin | Dimitris Seremetis | Johnny Andreasen | Nick Essery
// Have watched list for customers that we implemented using a linked-list

public class HaveWatched implements Serializable {
    private static final long serialVersionUID = 12345678910L;
    private Movie head;

    public HaveWatched() {
        head = null;
    }

    public void insertMovie(Movie newMovie) {
        if (head != null)
            newMovie.setNextMovie(head);
        head = newMovie;
    }

    public Movie searchMovieByID(int movieID) {
        Movie temp = head;
        while (temp != null) {
            if (Objects.equals(temp.getID(), movieID)) {
                return temp;
            }
            temp = temp.getNextMovie();
        }
        return null;
    }

    public Movie searchMovieByDate(int movieDate) {
        Movie temp = head;
        while (temp != null) {
            if (Objects.equals(temp.getReleaseDate(), movieDate)) {
                return temp;
            }
            temp = temp.getNextMovie();
        }
        return null;
    }

    public void deleteMovie(Movie movie) {
        Movie currentMovie = head;
        Movie prev;
        if (movie == currentMovie) {
            head = currentMovie.getNextMovie();
            movie.setNextMovie(null);
        } else {
            while (currentMovie != null) {
                prev = currentMovie;
                currentMovie = currentMovie.getNextMovie();
                if (currentMovie == movie) {
                    prev.setNextMovie(currentMovie.getNextMovie());
                    currentMovie.setNextMovie(null);
                }
            }
        }
    }

    public Movie getHead() {
        return head;
    }
}
