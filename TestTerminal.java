/*
Simple Test Terminal
*/

import managers.*;
import structures.*;

import java.io.Serializable;
import java.util.Scanner;

public class TestTerminal implements Serializable {
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

        int choice = mainMenu();
        while (choice != 4) {
            //////////////////// MAIN MENU \\\\\\\\\\\\\\\\\\\\
            switch (choice) {
                case 1: {
                    //////////////////// CUSTOMER LOGIN \\\\\\\\\\\\\\\\\\\\
                    System.out.println("CUSTOMER LOGIN");
//                    System.out.print("Please enter your email address --> ");
                    System.out.print("Please enter your credit card number --> ");
                    int enteredCreditNumber = getIntegerInput(1000, 9999);
                    Customer currentCustomer = customers.lookUpCustomer(enteredCreditNumber);
                    if (currentCustomer != null) {
                        System.out.println("Customer found!");
                        System.out.println(currentCustomer);
                        int customerChoice = customerMenu();
                        while (customerChoice != 4) {
                            switch (customerChoice) {
                                //////////////////// CUSTOMER MENU \\\\\\\\\\\\\\\\\\\\
                                case 1: {
                                    System.out.println("Accessing the wishlist of the customer...");
                                    Wishlist customerWishlist = currentCustomer.getWishlist();
                                    System.out.println("Accessed the wishlist.");
                                    System.out.println("Pulling up the first movie in the wishlist...");
                                    Movie firstMovie = customerWishlist.getFirstMovie();
                                    if (firstMovie != null) {
                                        System.out.println("Movie found!");
                                        System.out.println(firstMovie);
                                        System.out.print("Would you like to delete the movie? (1/0) --> ");
                                        int deleteChoice = getIntegerInput(0, 1);
                                        if (deleteChoice == 1) {
                                            customerWishlist.deleteFirstMovie();
                                            System.out.println("Movie deleted!");
                                        }
                                    } else {
                                        System.out.println("No movies found!");
                                    }
                                    break;
                                }
                                case 2: {
                                    System.out.println("Printing all of the movies by the release date...");
                                    moviesByDate.printAscendingDate();
                                    System.out.println("Returning to the main menu...");
                                    break;
                                }
                                case 3: {
                                    System.out.println("Accessing a movie by ID");
                                    System.out.print("Please enter the movie ID --> ");
                                    int enteredMovieID = getIntegerInput(10000, 99999);
                                    Movie foundMovie = moviesByID.searchMovieByID(enteredMovieID);
                                    if (foundMovie == null) {
                                        System.out.println("Movie not found!");
                                    } else {
                                        System.out.println("Movie found!");
                                        System.out.println(foundMovie);
                                        System.out.print("Have you watched this movie? Would you like to add this movie to your watched list (1/0) --> ");
                                        int addHaveWatchedChoice = getIntegerInput(0, 1);
                                        if (addHaveWatchedChoice == 1) {
                                            currentCustomer.getWatchedList().insertMovie(foundMovie);
                                        } else {
                                            System.out.print("Would you like to add this movie to your wishlist? (1/0) --> ");
                                            int addWishlistChoice = getIntegerInput(0, 1);
                                            if (addWishlistChoice == 1) {
                                                currentCustomer.getWishlist().addMovie(foundMovie);
                                                System.out.println("Movie added!");
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                            saveLoadManager.saveAllData(customers, wishlist, haveWatched, movieScoresHeap, moviesByID, moviesByDate);
                            customerChoice = customerMenu();
                        }

                    } else {
                        System.out.println("Customer not found");
                        break;
                    }
                    break;
                }
                case 2: {
                    //////////////////// NEW CUSTOMER \\\\\\\\\\\\\\\\\\\\
                    createCustomer(customers);
                    break;
                }
                case 3: {
                    //////////////////// ADMIN SIDE \\\\\\\\\\\\\\\\\\\\
                    if (adminLogin()) {
                        int adminChoice = adminMenu();
                        while (adminChoice != 5) {
                            switch (adminChoice) {
                                case 1: {
                                    System.out.println("Access and Manage Customer Information");
                                    System.out.print("Please enter the credit card number of the customer you want to access --> ");
                                    int creditCardNumber = getIntegerInput(1000, 9999);
                                    Customer adminCustomer = customers.lookUpCustomer(creditCardNumber);
                                    if (adminCustomer != null) {
                                        System.out.println("Customer found!");
                                        System.out.println(adminCustomer);
                                        System.out.print("Would you like to modify the customer? 1 for YES, 0 for NO --> ");
                                        int modifyChoice = getIntegerInput(0, 1);
                                        if (modifyChoice == 0) {
                                            break;
                                        } else {
                                            modifyCustomer(adminCustomer, customers);
                                        }
                                    } else {
                                        System.out.println("Customer not found");
                                    }
                                    break;
                                }
                                case 2: {
                                    System.out.println("Accessing the least rated movie");
                                    Movie leastRated = movieScoresHeap.findMinScore();
                                    if (leastRated != null) {
                                        System.out.println("Movie found!");
                                        System.out.println(leastRated);
                                        System.out.print("Would you like to remove the movie? 1 for YES, 0 for NO --> ");
                                        int removeChoice = getIntegerInput(0, 1);
                                        if (removeChoice == 0) {
                                            break;
                                        } else {
                                            System.out.println("Removing the least rated movie from the system...");
                                            movieManager.deleteMinScoreMovie();
                                            System.out.println("Movie Removed!!!");
                                        }
                                    } else {
                                        System.out.println("Movie not found");
                                    }
                                    break;
                                }
                                case 3: {
                                    System.out.println("Creating a new movie");
                                    createMovie(movieManager);
                                    break;
                                }
                                case 4: {
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
                                }
                            }
                            saveLoadManager.saveAllData(customers, wishlist, haveWatched, movieScoresHeap, moviesByID, moviesByDate);
                            adminChoice = adminMenu();
                        }
                    }
                    break;
                }
            }
            saveLoadManager.saveAllData(customers, wishlist, haveWatched, movieScoresHeap, moviesByID, moviesByDate);
            System.out.println("\n\n\n\n\n\n\n\n\n\nReturning to the main menu...\n");  // Added blank lines here in order to prevent confusing output
            choice = mainMenu();
        }
    }


    public static boolean adminLogin() {
        int tries = 3;
        String username;
        String password;
        System.out.println("ADMIN LOGIN");
        do {
            System.out.print("Please enter the admin username: ");
            username = getStringInput();
            System.out.print("Please enter the admin password: ");
            password = getStringInput();
            if (username.equals("admin") && password.equals("admin")) {
                System.out.println("ADMIN LOGIN SUCCESSFUL");
            } else {
                System.out.println("Invalid username or password");
                System.out.println(--tries + " attempts left");
            }
        } while ((!(username.equals("admin") && password.equals("admin"))) && (tries != 0));
        return tries != 0;
    }


    public static int mainMenu() {
        System.out.println("WELCOME TO THE MOVIE APP");
        System.out.println("Please select one of the options");
        System.out.println("1. Customer Login");
        System.out.println("2. Create new customer account");
        System.out.println("3. Admin Login");
        System.out.println("4. Quit");
        System.out.print("Please select an option --> ");
        return getIntegerInput(1, 4);
    }

    public static int adminMenu() {
        System.out.println("ADMIN MENU");
        System.out.println("Please select one of the options");
        System.out.println("1. Access and Change Customer Information");
        System.out.println("2. See the Least Rated Movie");
        System.out.println("3. Add a new Movie");
        System.out.println("4. Return to the initial seed of the program");
        System.out.println("5. Return to the main menu");
        System.out.print("Please select an option --> ");
        return getIntegerInput(1, 5);
    }

    public static int customerMenu() {
        System.out.println("CUSTOMER MENU");
        System.out.println("1. Access the Wishlist");
        System.out.println("2. Print all of the movies by release date");
        System.out.println("3. Access a movie by ID");
        System.out.println("4. Return to the main menu");
        System.out.print("Please select an option --> ");
        return getIntegerInput(1, 4);
    }

    public static int customerModifyMenu() {
        System.out.println("MODIFY CURRENT CUSTOMER");
        System.out.println("1. Modify the name of the customer");
        System.out.println("2. Modify the credit card number of the customer");
        System.out.println("3. Modify the email address of the customer");
        System.out.println("4. Return to main menu");
        System.out.print("Please select an option --> ");
        return getIntegerInput(1, 4);
    }

    public static void createCustomer(CustomerStorage customers) {
        System.out.println("CREATE NEW CUSTOMER");
        System.out.print("Please enter the name of the customer --> ");
        String name = getStringInput();
        System.out.print("Please enter the email address of the customer --> ");
        String email = getEmailInput();
        System.out.print("Please enter the credit card number of the customer --> ");
        int credit = getIntegerInput(1, 9999);
        Customer newCustomer = new Customer(name, email, credit);
        customers.insertCustomer(newCustomer);
        System.out.println("Customer created!");
    }

    public static void modifyCustomer(Customer adminCustomer, CustomerStorage customers) {
        // Function to modify the customer
        int choice = customerModifyMenu();
        while (choice != 4) {
            switch (choice) {
                case 1:
                    // Modifying the name of the customer
                    System.out.println("Current name of the customer --> " + adminCustomer.getName());
                    System.out.print("Please enter the new name of the customer (Press return to skip) --> ");
                    String name = getStringInput();
                    if (!name.equals("\n")) {
                        adminCustomer.setName(name);
                    }
                    break;
                case 2:
                    // Modifying the credit card number of the customer
                    System.out.print("Current credit card number of the customer --> " + adminCustomer.getCredit());
                    System.out.print("Please enter the new credit card number of the customer (Enter 999 to skip) --> ");
                    customers.deleteCustomer(adminCustomer.getCredit());
                    int credit = getIntegerInput(999, 9999);
                    if (credit != 999) {
                        adminCustomer.setCredit(credit);
                    }
                    customers.insertCustomer(adminCustomer);
                    break;
                case 3:
                    // Modifying the email of the customer
                    System.out.print("Current email of the customer --> " + adminCustomer.getEmail());
                    System.out.print("Please enter the new email of the customer (Enter null@null.com to skip) --> ");
                    String email = getEmailInput();
                    if (!email.equals("null@null.com")) {
                        adminCustomer.setEmail(email);
                    }
                    break;
            }
            choice = customerModifyMenu();
        }
    }

    public static void createMovie(MovieManager movieManager) {
        System.out.println("CREATE NEW MOVIE");
        System.out.print("Please enter the name of the movie --> ");
        String name = getStringInput();
        System.out.print("Please enter the release date of the movie --> ");
        int releaseDate = getIntegerInput(10000000, 99999999);
        System.out.print("Please enter the ID of the movie --> ");
        int id = getIntegerInput(10000, 99999);
        System.out.print("Please enter the rating of the movie --> ");
        int rating = getIntegerInput(0, 100);
        System.out.print("Please set the availability (1 for available, 0 for not available) --> ");
        int availability = getIntegerInput(0, 1);
        boolean isAvailable = availability == 1;
        Movie newMovie = new Movie(name, releaseDate, id, rating, isAvailable);
        movieManager.insert(newMovie);
    }

    public static int getIntegerInput(int lower, int upper) {
        // Function to get integer input until the user enters a valid integer.
        // It also checks if the integer is included in the lower/upper range
        Scanner scanner = new Scanner(System.in);
        int validInteger = 0;
        boolean isValid = false;

        while (!isValid) {
            if (scanner.hasNextInt()) {
                validInteger = scanner.nextInt();
                if (validInteger >= lower && validInteger <= upper) {
                    isValid = true;
                } else {
                    System.out.print("Please enter an integer from " + lower + " to " + upper + " --> ");
                }
            } else {
                System.out.print("Invalid input. Please enter an integer --> ");
                scanner.nextLine();
            }
        }
        return validInteger;
    }

    public static String getStringInput() {
        // Function to get any string input throughout the program
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String getEmailInput() {
        // Function to take string input to use as an email.
        // As an extra this function does input validation to check if this is a valid email address
        Scanner scanner = new Scanner(System.in);
        boolean validEmail = false;
        String email = null;
        while (!validEmail) {
            email = scanner.nextLine();
            if (email.contains("@") && !email.startsWith("@") && !email.endsWith("@") && !email.contains("@.") && (email.endsWith(".com") || email.endsWith(".edu") || email.endsWith(".gov") || email.endsWith(".org") || email.endsWith(".net") || email.endsWith(".info") || email.endsWith(".biz"))) {
                validEmail = true;
            } else {
                System.out.print("Invalid email address. Please enter again --> ");
            }
        }
        return email;
    }
}
