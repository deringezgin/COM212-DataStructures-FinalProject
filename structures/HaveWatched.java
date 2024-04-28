/*
Have watched list for customers that we implemented using a linked-list
 */

package structures;

import java.io.Serializable;
import java.util.Objects;

public class HaveWatched implements Serializable {
    private Movie head;

    public HaveWatched() {
        head = null;
    }

    public void insertMovie(Movie newMovie) {
        if (head != null)
            newMovie.setNextMovie(head);
        head = newMovie;
    }

    public Movie searchMovie(String movieTitle) {
        Movie temp = head;
        while (temp != null) {
            if (Objects.equals(temp.getTitle(), movieTitle)) {
                return temp;
            }
            temp = temp.getNextMovie();
        }
        return null;
    }
}
