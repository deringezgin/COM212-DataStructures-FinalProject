/*
A BST that organizes the movies by the ID.
*/

package mainStructures;

import java.io.Serial;
import java.io.Serializable;

public class MoviesByID implements Serializable {
    @Serial
    private static final long serialVersionUID = 12345678910L;
    private Movie root;

    public MoviesByID() {
        root = null;
    }

    public void insertMovieByID(Movie movie) {
        // Inserting a Movie to the ID BST
        System.out.println("Inserting movie: " + movie.getTitle());
        if (root == null) {
            root = movie;
        } else {
            inserter(root, movie);
        }
    }

    private void inserter(Movie parent, Movie target) {
        // Recursive insert function
        if (target.getID() < parent.getID()) {
            if (parent.getLeftIDMovie() == null) {
                parent.setLeftIDMovie(target);
            } else {
                inserter(parent.getLeftIDMovie(), target);
            }
        } else {
            if (parent.getRightIDMovie() == null) {
                parent.setRightIDMovie(target);
            } else {
                inserter(parent.getRightIDMovie(), target);
            }
        }
    }

    public void deleteMovieID(Movie target) {
        // Deleting a movie from the ID BST
        if (root != null) {
            if (root == target) {
                root = deleteRoot(root);
            } else {
                deleter(root, target);
            }
        }
    }

    private void deleter(Movie parent, Movie target) {
        // Recursive delete function
        if (parent.getLeftIDMovie() != null && target.getID() < parent.getID()) {
            if (target.getID() == parent.getLeftIDMovie().getID()) {
                parent.setLeftIDMovie(deleteRoot(parent.getLeftIDMovie()));
            } else {
                deleter(parent.getLeftIDMovie(), target);
            }
        } else if (parent.getRightIDMovie() != null && target.getID() > parent.getID()) {
            if (target.getID() == parent.getRightIDMovie().getID()) {
                parent.setRightIDMovie(deleteRoot(parent.getRightIDMovie()));
            } else {
                deleter(parent.getRightIDMovie(), target);
            }
        }
    }

    private Movie deleteRoot(Movie target) {
        // Function to delete the root
        if (target.getLeftIDMovie() == null) {
            Movie temp = target;
            target = target.getRightIDMovie();
            temp.setRightIDMovie(null);
            return target;
        } else if (target.getRightIDMovie() == null) {
            Movie temp = target;
            target = target.getLeftIDMovie();
            temp.setLeftIDMovie(null);
            return target;
        } else {
            Movie temp = getSuccessor(target.getRightIDMovie());
            deleteMovieID(temp);
            temp.setRightIDMovie(target.getRightIDMovie());
            temp.setLeftIDMovie(target.getLeftIDMovie());
            target.setLeftIDMovie(null);
            target.setRightIDMovie(null);
            return temp;
        }
    }

    private Movie getSuccessor(Movie movie) {
        // Function to find the successor of a Node
        while (movie.getLeftIDMovie() != null) {
            movie = movie.getLeftIDMovie();
        }
        return movie;
    }

    public Movie searchMovieByID(int movieID) {
        // Searching movies by ID in the ID BST
        return searcher(root, movieID);
    }

    private Movie searcher(Movie movie, int movieID){
        // Recursive search function
        if (movie == null) {
            return null;
        } else if (movieID == movie.getID()) {
            return movie;
        } else if (movieID < movie.getID()) {
            return searcher(movie.getLeftIDMovie(), movieID);
        } else {
            return searcher(movie.getRightIDMovie(), movieID);
        }
    }
}