/*
Simple Code to return to the seed of the program.
*/

import managers.ImportManager;
import managers.MovieManager;
import managers.SaveLoadManager;
import structures.*;

public class returnToSeed {
    public static void main(String[] args) {
        // Reading the objects from the files and creating a movie-manager object
        SaveLoadManager saveLoadManager = new SaveLoadManager();
        CustomerStorage customers = saveLoadManager.loadCustomers();
        Wishlist wishlist = saveLoadManager.loadWishlist();
        HaveWatched haveWatched = saveLoadManager.loadHaveWatched();
        MovieScoresHeap movieScoresHeap = saveLoadManager.loadMovieScoresHeap();
        MoviesByID moviesByID = saveLoadManager.loadMoviesByID();
        MoviesByDate moviesByDate = saveLoadManager.loadMoviesByDate();
        MovieManager movieManager = new MovieManager(moviesByDate, moviesByID, movieScoresHeap);

        System.out.println("Returning to the seed of the program...");
        saveLoadManager.clearAllData();
        customers = saveLoadManager.loadCustomers();
        wishlist = saveLoadManager.loadWishlist();
        movieScoresHeap = saveLoadManager.loadMovieScoresHeap();
        moviesByID = saveLoadManager.loadMoviesByID();
        moviesByDate = saveLoadManager.loadMoviesByDate();
        movieManager = new MovieManager(moviesByDate, moviesByID, movieScoresHeap);
        ImportManager importManager = new ImportManager(movieManager, customers);
        importManager.importAllData();
        saveLoadManager.saveAllData(customers, wishlist, haveWatched, movieScoresHeap, moviesByID, moviesByDate);
    }
}
