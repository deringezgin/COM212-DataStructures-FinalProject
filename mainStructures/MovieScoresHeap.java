/*
A movie scores heap that keeps track of the lowest scored movie
*/

package mainStructures;

import java.io.Serializable;

public class MovieScoresHeap implements Serializable {
    private static final long serialVersionUID = 12345678910L;
    private Movie[] moviesByScore;
    private final int size = 255;  // We initialized size here for better modification and readability
    private int movieCount;

    public MovieScoresHeap() {
        moviesByScore = new Movie[size];
        movieCount = 0;
    }

    public Movie findMinScore() {
        // Function to find the minScored Movie in the Score heap
        return moviesByScore[0];
    }

    public void deleteMinScore() {
        // Function to delete the lowest scored movie
        if (movieCount == 0) {
            return;
        } if (movieCount == 1) {
            moviesByScore[0] = null;
        }
        movieCount -= 1;
        swap(0, movieCount);
        int temp = 0;
        while (temp < movieCount) {
            int left = getLeft(temp);
            int right = getRight(temp);
            int smallest = temp;

            if ((left < movieCount) && (moviesByScore[left].getScore() < moviesByScore[smallest].getScore())) {
                smallest = left;
            }

            if ((right < movieCount) && (moviesByScore[right].getScore() < moviesByScore[smallest].getScore())) {
                smallest = right;
            }

            if (smallest != temp) {
                swap(temp, smallest);
                temp = smallest;
            } else {
                break;
            }
        }
    }

    public boolean insertMovie(Movie movie) {
        // Function to insert a movie to the movie
        if (movieCount == size) {  // In case that the movie heap is full. We can't insert further movies
            System.out.println("Movie Scores Heap is full. No other movies can be added");
            return false;
        }
        moviesByScore[movieCount] = movie;
        int child = movieCount;
        int parent = getParent(movieCount);
        while ((moviesByScore[child].getScore() < moviesByScore[parent].getScore())) {  // While the child Movie is less than the parent Movie and parent is greater than 0
            swap(child, parent);  // Swap child and the parent
            child = parent;
            parent = getParent(parent);
        }
        movieCount += 1;
        return true;
    }

    private int getParent(int child) {
        return ((child - 1) / 2);
    }

    private int getLeft(int parent) {
        return (2 * parent) + 1;
    }

    private int getRight(int parent) {
        return (2 * parent) + 2;
    }

    private void swap(int Movie1, int Movie2) {
        // Function to swap two Movies in indexes Movie1 and Movie2
        Movie temp = moviesByScore[Movie1];
        moviesByScore[Movie1] = moviesByScore[Movie2];
        moviesByScore[Movie2] = temp;
    }
}
