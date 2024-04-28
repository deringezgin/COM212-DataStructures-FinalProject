/*
Movie manager class that we created.
In our program, we keep track of the movies in 3 different ways:
1. A binary search tree by date
2. A binary search tree by ID
3. A priority queue (heap) that keeps track of the lowest scored movie
Because of this multiple storage model, inserting and deleting movies can be tricky and hard to follow.
This class has 2 methods: insert and deleteMinScoreMovie.
These methods will perform these operations in all the data structures that keep track of the movies.
 */

import java.io.Serializable;

public class MovieManager implements Serializable {
    private MoviesByDate moviesByDate;
    private MoviesByID moviesByID;
    private MovieScoresHeap movieScoresHeap;

    public MovieManager(MoviesByDate moviesByDate, MoviesByID moviesByID, MovieScoresHeap movieScoresHeap) {
        this.moviesByDate = moviesByDate;
        this.moviesByID = moviesByID;
        this.movieScoresHeap = movieScoresHeap;
    }

    public void insert(Movie movieToInsert) {
        // Simple method to insert a movie to the Date BST, ID BST and Score Heap
        boolean isNotFull = movieScoresHeap.insertMovie(movieToInsert);
        if (!isNotFull) {
            return;
        }
        moviesByDate.insertMovieByDate(movieToInsert);
        moviesByID.insertMovieByID(movieToInsert);
    }

    public void deleteMinScoreMovie() {
        // Simple method to delete the moin scored movie from the Data BST, ID BST and Score Heap
        Movie minRated = movieScoresHeap.findMinScore();
        movieScoresHeap.deleteMinScore();
        moviesByDate.deleteMovieDate(minRated);
        moviesByID.deleteMovieID(minRated);
    }
}
