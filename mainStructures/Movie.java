/*
Movie class that creates a Movie Node.
A movie Node has the usual variables: title, release date, ID, score and availability
It has a next pointer for the implementation of the Have Watched list of the users. (It's a linked list)
It has left and right pointers for both their IDs and Dates which are used to create separate BSTs.
 */

package mainStructures;

import java.io.Serial;
import java.io.Serializable;

public class Movie implements Serializable {
    @Serial
    private static final long serialVersionUID = 12345678910L;
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

    public String convertToDate() {
        int year = this.releaseDate / 10000;
        int monthDay = this.releaseDate % 10000;
        int month = monthDay / 100;
        int day = monthDay % 100;

        String monthString;
        switch (month) {
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
            default:
                monthString = "Invalid month";
                break;
        }
        return monthString + " " + day + ", " + year;
    }

//    @Overrides
//    public String toString() {
//        // Function to print the movie
//        return "\nMOVIE DETAILS" +
//                "\nTitle of the movie: " + this.title +
//                "\nRelease Date: " + convertToDate() +
//                "\nID of the movie: " + this.ID +
//                "\nScore of the movie: " + this.score +
//                "\nAvailability: " + this.availability +
//                "\nAddress: " + this;
//    }
}