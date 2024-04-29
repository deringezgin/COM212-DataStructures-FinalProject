package managers;

import structures.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ImportManager {
    private MovieManager movieManager;
    private CustomerStorage customers;

    public ImportManager(MovieManager movieManager, CustomerStorage customers) {
        this.movieManager = movieManager;
        this.customers = customers;
    }
    public void importMoviesFromCSV() {
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
                movieManager.insert(movie);
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
