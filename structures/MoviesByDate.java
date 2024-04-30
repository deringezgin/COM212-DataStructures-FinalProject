/*
A BST that organizes the movies by the release date.
*/

package structures;

import java.io.Serializable;

public class MoviesByDate implements Serializable {
    private Movie root;
    private int count = 10000;

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
        count += 1;
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
        // Function to delete the Node (target) in the BST
        if (root != null) {  // If it's not an empty tree
            if (root == target) {  // If the Node is the root
                root = deleteRoot(root);  // Directly call the deleteRoot function
            } else {
                deleter(root, target);  // If it's not the root, pass to the deleter
            }
        }
    }

    public void deleter(Movie parent, Movie target) {
        // Recursive delete function to reach the Node we'd like to delete and call the deleteRoot method
        if (parent.getLeftDateMovie() != null && target.getReleaseDate() < parent.getReleaseDate()) {  // If left is not empty and target Node is less than the current parent
            if (target.getReleaseDate() == parent.getLeftDateMovie().getReleaseDate()) {  // If the left Node is our target
                parent.setLeftDateMovie(deleteRoot(parent.getLeftDateMovie()));  // Call the deleteRoot on the left child of the parent and set the new root to the left child of the parent
            } else {
                deleter(parent.getLeftDateMovie(), target);  // If we don't have a match, proceed further in the BST
            }
        } else if (parent.getRightDateMovie() != null && target.getReleaseDate() > parent.getReleaseDate()) {  // If right is not empty and target Node is more than the current parent
            if (target.getReleaseDate() == parent.getRightDateMovie().getReleaseDate()) { // Applying the same procedures but for the right side
                parent.setRightDateMovie(deleteRoot(parent.getRightDateMovie()));
            } else {
                deleter(parent.getRightDateMovie(), target);
            }
        }
    }

    private Movie deleteRoot(Movie x) {
        // Function to delete the Node in a BST that is the root of the tree
        if (x.getLeftDateMovie() == null) {
            // If the Node we want to delete is a Node with only one right child (This case also catches Nodes with no child)
            Movie temp = x;  // Saving the Node in a temp variable
            x = x.getRightDateMovie();  // Setting it into its right child
            temp.setRightDateMovie(null);  // Disconnecting the Node we'd like to delete
            return x;
        } else if (x.getRightDateMovie() == null) {
            // If the Node we want to delete is a Node with only one left child, we apply the same procedure as before but with the left side
            Movie temp = x;
            x = x.getLeftDateMovie();
            temp.setLeftDateMovie(null);
            return x;
        } else {
            // If the Node we want to delete is a Node with a child on both sides
            Movie temp = getSuccessor(x.getRightDateMovie());  // Finding the successor
            deleteMovieDate(temp);  // Removing the successor from the tree
            temp.setRightDateMovie(x.getRightDateMovie());  // Inserting the successor into the place of the Node we'd like to delete
            temp.setLeftDateMovie(x.getLeftDateMovie());
            x.setLeftDateMovie(null);  // Disconnecting the Node we'd like to delete
            x.setRightDateMovie(null);
            return temp;
        }
    }

    private Movie getSuccessor(Movie x) {
        // Function to find the successor of a Node
        while (x.getLeftDateMovie() != null) {  // While the left side is not null
            x = x.getLeftDateMovie();
        }
        return x;
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

    public int getCount() {
        return count;
    }
}