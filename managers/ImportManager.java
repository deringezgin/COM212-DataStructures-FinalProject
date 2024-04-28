package managers;

import structures.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ImportManager {
    private MovieManager movieManager;


    public static void main(String[] args) {
        SaveLoadManager saveLoadManager = new SaveLoadManager();
        MovieScoresHeap movieScoresHeap = saveLoadManager.loadMovieScoresHeap();
        MoviesByID moviesByID = saveLoadManager.loadMoviesByID();
        MoviesByDate moviesByDate = saveLoadManager.loadMoviesByDate();
        MovieManager movieManager = new MovieManager(moviesByDate, moviesByID, movieScoresHeap);
        importMoviesFromCSV(movieManager);
        importCustomersFromCSV();
    }

    public static void importMoviesFromCSV(MovieManager movieManager) {
        try {
            Scanner input = new Scanner(new File("seed/movieSeedCSV.csv"));
            input.useDelimiter(",|\\R"); // Delimiter pattern for commas or line breaks

            while (input.hasNext()) {
                String title = input.next().trim();
                int releaseDate = Integer.parseInt(input.next().trim());
                int id = Integer.parseInt(input.next().trim());
                int score = Integer.parseInt(input.next().trim());
                boolean availability = Integer.parseInt(input.next().trim()) == 1;
                Movie movie = new Movie(title, releaseDate, id, score, availability);
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("seed/movieSeedCSV.csv file is not found");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing data: " + e.getMessage());
        }
    }

    public static void importCustomersFromCSV() {
        try {
            Scanner input = new Scanner(new File("seed/customerSeedCSV.csv"));
            input.useDelimiter(",|\\R"); // Delimiter pattern for commas or line breaks

            while (input.hasNext()) {
                String name = input.next().trim();
                String email = input.next().trim();
                int credit = Integer.parseInt(input.next().trim());
                Customer customer = new Customer(name, email, credit);
                System.out.println(customer);
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("seed/movieSeedCSV.csv file is not found");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing data: " + e.getMessage());
        }
    }

}
