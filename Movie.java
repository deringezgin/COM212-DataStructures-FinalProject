/*
Movie class that creates a Movie Node.
A movie Node has the usual variables: title, release date, ID, score and availability
It has a next pointer for the implementation of the Have Watched list of the users. (It's a linked list)
It has left and right pointers for both their IDs and Dates which are used to create separate BSTs.
 */

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private int releaseDate;
    private int ID;
    private int score;
    private boolean availability;
    private Movie nextMovie;
    private Movie leftIDMovie;
    private Movie rightIDMovie;
    private Movie leftDateMovie;
    private Movie rightDateMovie;

    public Movie(String title, int releaseDate, int ID, int score, boolean availability) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.ID = ID;
        this.score = score;
        this.availability = availability;
    }

    // Functions that set and access the instance variables of a Movie object
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Movie getNextMovie() {
        return nextMovie;
    }

    public void setNextMovie(Movie nextMovie) {
        this.nextMovie = nextMovie;
    }

    public Movie getLeftIDMovie() {
        return leftIDMovie;
    }

    public void setLeftIDMovie(Movie leftIDMovie) {
        this.leftIDMovie = leftIDMovie;
    }

    public Movie getRightIDMovie() {
        return rightIDMovie;
    }

    public void setRightIDMovie(Movie rightIDMovie) {
        this.rightIDMovie = rightIDMovie;
    }

    public Movie getLeftDateMovie() {
        return leftDateMovie;
    }

    public void setLeftDateMovie(Movie leftDateMovie) {
        this.leftDateMovie = leftDateMovie;
    }

    public Movie getRightDateMovie() {
        return rightDateMovie;
    }

    public void setRightDateMovie(Movie rightDateMovie) {
        this.rightDateMovie = rightDateMovie;
    }

    @Override
    public String toString() {
        // Function to print the movie
        return "MOVIE DETAILS" +
                "\nTitle of the movie: " + this.title +
                "\nRelease Date: " + this.releaseDate +
                "\nID of the movie: " + this.ID +
                "\nScore of the movie: " + this.score +
                "\nAvailability: " + this.availability;
    }
}