/*
A BST that organizes the movies by the release date.
*/

package structures;

import java.io.Serializable;

public class MoviesByDate implements Serializable {
    private Movie root;

    public MoviesByDate() {
        root = null;
    }

    public void insertMovieByDate(Movie movie) {
        // Inserting a Movie to the Date BST
        if (root == null) {
            root = movie;
        } else {
            inserter(root, movie);
        }
    }

    private void inserter(Movie parent, Movie target) {
        // Recursive insert function
        if (target.getReleaseDate() < parent.getReleaseDate()) {
            if (parent.getLeftDateMovie() == null) {
                parent.setLeftDateMovie(target);
            } else {
                inserter(parent.getLeftDateMovie(), target);
            }
        } else {
            if (parent.getRightDateMovie() == null) {
                parent.setRightDateMovie(target);
            } else {
                inserter(parent.getRightDateMovie(), target);
            }
        }
    }

    public void deleteMovieDate(Movie target) {
        // Deleting a movie from the Date BST
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
        if (parent.getLeftDateMovie() != null && target.getReleaseDate() < parent.getReleaseDate()) {
            if (target.getReleaseDate() == parent.getLeftDateMovie().getReleaseDate()) {
                parent.setLeftDateMovie(deleteRoot(parent.getLeftDateMovie()));
            } else {
                deleter(parent.getLeftDateMovie(), target);
            }
        } else if (parent.getRightDateMovie() != null && target.getReleaseDate() > parent.getReleaseDate()) {
            if (target.getReleaseDate() == parent.getRightDateMovie().getReleaseDate()) {
                parent.setRightIDMovie(deleteRoot(parent.getRightDateMovie()));
            } else {
                deleter(parent.getRightDateMovie(), target);
            }
        }
    }

    private Movie deleteRoot(Movie target) {
        // Function to delete root
        if (target.getLeftDateMovie() == null) {
            Movie temp = target;
            target = target.getRightDateMovie();
            temp.setRightIDMovie(null);
            return target;
        } else if (target.getRightDateMovie() == null) {
            Movie temp = target;
            target = target.getLeftDateMovie();
            temp.setLeftDateMovie(null);
            return target;
        } else {
            Movie temp = getSuccessor(target.getRightDateMovie());
            deleteMovieDate(temp);
            temp.setRightIDMovie(target.getRightDateMovie());
            temp.setLeftDateMovie(target.getLeftDateMovie());
            target.setLeftDateMovie(null);
            target.setRightIDMovie(null);
            return temp;
        }
    }

    private Movie getSuccessor(Movie movie) {
        // Function to find the successor of a Node
        while (movie.getLeftDateMovie() != null) {
            movie = movie.getLeftDateMovie();
        }
        return movie;
    }

    public Movie searchMovieByDate(int movieDate) {
        // Searching movies by Date in the Date BST
        return searcher(root, movieDate);
    }

    private Movie searcher(Movie movie, int movieDate) {
        // Recursive search function
        if (movie == null) {
            return null;
        } else if (movieDate == movie.getReleaseDate()) {
            return movie;
        } else if (movieDate < movie.getReleaseDate()) {
            return searcher(movie.getLeftDateMovie(), movieDate);
        } else {
            return searcher(movie.getRightDateMovie(), movieDate);
        }
    }

    public void printAscendingDate() {
        // Printing the movies with the ascending date order
        ascend(root);
        System.out.println();
    }

    private void ascend(Movie x) {
        if (x != null) {
            ascend(x.getLeftDateMovie());
            System.out.println(x);
            ascend(x.getRightDateMovie());
        }
    }

    public void printDescendingDate() {
        // Printing movies with the descending date order
        descend(root);
        System.out.println();
    }

    private void descend(Movie x) {
        if (x != null) {
            ascend(x.getRightDateMovie());
            System.out.print(x.getReleaseDate() + " ");
            ascend(x.getLeftDateMovie());
        }
    }

    public void printTree() {
        // Printing the movies by date tree
        printTree2(root);
        System.out.println();
    }

    private void printTree2(Movie tree) {
        if (tree != null) {
            System.out.print(tree.getReleaseDate() + " ");
            if (tree.getLeftDateMovie() != null)
                System.out.print("Left: " + tree.getLeftDateMovie().getReleaseDate() + " ");
            else System.out.print("Left: null ");
            if (tree.getRightDateMovie() != null)
                System.out.println("Right: " + tree.getRightDateMovie().getReleaseDate() + " ");
            else System.out.println("Right: null ");
            printTree2(tree.getLeftDateMovie());
            printTree2(tree.getRightDateMovie());
        }
    }

    public Movie getRoot() {
        return root;
    }
}