package mainStructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

// COM212 Data Structures | Spring 2024 | Final Project
// Derin Gezgin | Dimitrios Seremetis | Johnny Andreasen | Nick Essery
// Import manager to read the .csv seed files

public class ImportManager implements Serializable {
    private static final long serialVersionUID = 12345678910L;

    private MoviesByID moviesByID;
    private MoviesByDate moviesByDate;
    private MovieScoresHeap movieScoresHeap;
    private CustomerStorage customers;

    public ImportManager(MoviesByID moviesByID, MoviesByDate moviesByDate, MovieScoresHeap movieScoresHeap, CustomerStorage customers) {
        this.moviesByID = moviesByID;
        this.moviesByDate = moviesByDate;
        this.movieScoresHeap = movieScoresHeap;
        this.customers = customers;
    }

    public void importMoviesFromCSV() {
        try {
            Scanner input = new Scanner(new File("seed/movieSeedCSV.csv"));
            input.useDelimiter(",|\\R"); // Delimiter pattern for commas or line breaks

            while (input.hasNext()) {
                String title = input.next().trim();
                int releaseDate = Integer.parseInt(input.next().trim());
                int id = moviesByDate.getCount() + 1;
                int score = Integer.parseInt(input.next().trim());
                boolean availability = Integer.parseInt(input.next().trim()) == 1;
                Movie movie = new Movie(title, releaseDate, id, score, availability);
                moviesByDate.insertMovieByDate(movie);
                moviesByID.insertMovieByID(movie);
                movieScoresHeap.insertMovie(movie);
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("seed/movieSeedCSV.csv file is not found");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing data: " + e.getMessage());
        }
    }

    public void importCustomersFromCSV() {
        try {
            Scanner input = new Scanner(new File("seed/customerSeedCSV.csv"));
            input.useDelimiter(",|\\R"); // Delimiter pattern for commas or line breaks

            while (input.hasNext()) {
                String name = input.next().trim();
                System.out.println(name);
                String email = input.next().trim();
                int credit = Integer.parseInt(input.next().trim());
                Customer newCustomer = new Customer(name, email, credit);
                customers.insertCustomer(newCustomer);
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("seed/movieSeedCSV.csv file is not found");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing data: " + e.getMessage());
        }
    }

    public void importAllData() {
        importMoviesFromCSV();
        importCustomersFromCSV();
    }
}
