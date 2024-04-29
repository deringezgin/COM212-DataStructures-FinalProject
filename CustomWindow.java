import managers.MovieManager;
import managers.SaveLoadManager;
import structures.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class CustomWindow {
    private static final Font titleFont = new Font("Verdana", Font.BOLD, 40);
    private static final Font textFont = new Font("Verdana", Font.PLAIN, 20);

    public static void main(String[] args) {
        // Loading Data
        SaveLoadManager saveLoadManager = new SaveLoadManager();
        CustomerStorage customers = saveLoadManager.loadCustomers();
        Wishlist wishlist = saveLoadManager.loadWishlist();
        HaveWatched haveWatched = saveLoadManager.loadHaveWatched();
        MovieScoresHeap movieScoresHeap = saveLoadManager.loadMovieScoresHeap();
        MoviesByID moviesByID = saveLoadManager.loadMoviesByID();
        MoviesByDate moviesByDate = saveLoadManager.loadMoviesByDate();
        MovieManager movieManager = new MovieManager(moviesByDate, moviesByID, movieScoresHeap);

        // Creating the window
        JFrame frame = new JFrame("Custom Window");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to store the application
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));

        welcomeMenu(panel, customers, saveLoadManager, movieManager);  // Welcome menu

        // Showing the window
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void welcomeMenu(JPanel panel, CustomerStorage customers, SaveLoadManager saveLoadManager, MovieManager movieManager) {
        // Main menu of our program that has 3 options: Customer login - Admin Login and New Customer Creation
        // Title of the welcome menu
        JLabel titleLabel = new JLabel("Parker Films", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Creating buttons, setting fonts and sizes
        JButton customerLoginButton = new JButton("Customer");
        JButton adminLoginButton = new JButton("Admin");
        JButton newCustomerButton = new JButton("New Customer");
        customerLoginButton.setFont(textFont);
        adminLoginButton.setFont(textFont);
        newCustomerButton.setFont(textFont);
        Dimension buttonSize = new Dimension(170, 100);
        customerLoginButton.setPreferredSize(buttonSize);
        adminLoginButton.setPreferredSize(buttonSize);
        newCustomerButton.setPreferredSize(buttonSize);

        // Spacing between the elements
        panel.add(Box.createVerticalStrut(20), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(panel.getBackground());  // Match the background color
        buttonPanel.add(customerLoginButton);
        buttonPanel.add(Box.createHorizontalStrut(30));  // Add space between buttons
        buttonPanel.add(adminLoginButton);
        buttonPanel.add(Box.createHorizontalStrut(30));  // Add space between buttons
        buttonPanel.add(newCustomerButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);  // Add the button panel to the bottom of the panel

        customerLoginButton.addActionListener(e -> {
            // If the customer login button is clicked, create a login menu in the customer version
            loginMenu(panel, "Customer", customers, saveLoadManager, movieManager);
        });

        adminLoginButton.addActionListener(e -> {
            // If the admin login button is clicked, create a login menu in the admin version
            loginMenu(panel, "Admin", customers, saveLoadManager, movieManager);
        });

        newCustomerButton.addActionListener(e -> {
            // If the new customer button is clicked, go to the sign-up page
            newCustomerMenu(panel, customers, saveLoadManager, movieManager);
        });

        // Updating the screen
        panel.revalidate();
        panel.repaint();
    }

    private static void newCustomerMenu(JPanel panel, CustomerStorage customers, SaveLoadManager saveLoadManager, MovieManager movieManager) {
        // Function to create a new customer in our program
        panel.removeAll();  // Clear the panel

        // Main title of the page
        JLabel loginTitleLabel = new JLabel("New Customer Creation", JLabel.CENTER);
        loginTitleLabel.setFont(titleFont);
        panel.add(loginTitleLabel, BorderLayout.NORTH);


        JPanel loginPanel = new JPanel(new GridLayout(5, 1, 10, 5));
        loginPanel.setBackground(panel.getBackground());  // Getting the background color of the main panel

        // Creating labels and text-fields for our program
        JLabel nameLabel = new JLabel("Name Surname", JLabel.CENTER);
        JLabel emailLabel = new JLabel("Email Address", JLabel.CENTER);
        JLabel creditLabel = new JLabel("Credit Card Number", JLabel.CENTER);
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField creditField = new JTextField();
        JLabel passwordLabel = new JLabel("Password", JLabel.CENTER);
        JPasswordField passwordField = new JPasswordField();
        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Go Back");

        nameLabel.setFont(textFont);
        nameField.setFont(textFont);
        emailLabel.setFont(textFont);
        emailField.setFont(textFont);
        creditLabel.setFont(textFont);
        creditField.setFont(textFont);
        passwordLabel.setFont(textFont);
        passwordField.setFont(textFont);
        submitButton.setFont(textFont);
        backButton.setFont(textFont);
        Dimension buttonSize = new Dimension(200, 50);  // Setting button size
        submitButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(buttonSize);

        panel.add(Box.createVerticalStrut(20), BorderLayout.CENTER);  // Setting spacing

        JPanel buttonPanel = new JPanel(new FlowLayout());  // Button panel for storing the buttons
        buttonPanel.setBackground(panel.getBackground());
        buttonPanel.add(submitButton);
        buttonPanel.add(Box.createHorizontalStrut(60));
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Adding the fileds on the panel and adding the panel to the screen
        loginPanel.add(nameLabel);
        loginPanel.add(nameField);
        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(creditLabel);
        loginPanel.add(creditField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(backButton);
        loginPanel.add(submitButton);
        panel.add(loginPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            // When submit button is clicked...
            // Getting the text in the fields
            String username = nameField.getText();
            String email = emailField.getText();
            String credit = creditField.getText();
            String password = new String(passwordField.getPassword());

            // Validating the input of the customer
            boolean valid = newCustomerValidation(username, email, credit, password);
            if (valid) {  // If it's valid, creates a new customer, shows a message and moves to the login menu
                Customer customer = new Customer(username, email, Integer.parseInt(credit), password);
                customers.insertCustomer(customer);
                saveLoadManager.saveCustomers(customers);
                JOptionPane.showMessageDialog(panel, "Customer successfully created");
                loginMenu(panel, "Customer", customers, saveLoadManager, movieManager);
            } else {  // if it's not valid, shows another message
                JOptionPane.showMessageDialog(panel, "Invalid credentials.\nCheck that you entered a valid email and your credit card is a 4 digit integer.\nAlso be sure that you filled all of the fields.");
            }
        });

        backButton.addActionListener(e -> {
            // If the back button is clicked, removes everything and returns to the welcome menu.
            panel.removeAll();
            welcomeMenu(panel, customers, saveLoadManager, movieManager);
        });

        // Update the panel
        panel.revalidate();
        panel.repaint();
    }

    private static void loginMenu(JPanel panel, String userType, CustomerStorage customers, SaveLoadManager saveLoadManager, MovieManager movieManager) {
        // The login menu that can be used both for admin and the customer
        panel.removeAll();  // Clear the panel

        // Creating the page title using the input string
        JLabel loginTitleLabel = new JLabel(userType + " Login", JLabel.CENTER);
        loginTitleLabel.setFont(titleFont);
        panel.add(loginTitleLabel, BorderLayout.NORTH);

        JPanel loginPanel = new JPanel(new GridLayout(3, 1, 20, 5));  // Creating a panel for storing the login elements
        loginPanel.setBackground(panel.getBackground());  // Setting the background color to the parent

        // Creating labels and buttons
        JLabel usernameLabel = new JLabel("Username", JLabel.CENTER);
        JLabel passwordLabel = new JLabel("Password", JLabel.CENTER);
        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Go Back");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        usernameLabel.setFont(textFont);
        usernameField.setFont(textFont);
        passwordLabel.setFont(textFont);
        passwordField.setFont(textFont);
        loginButton.setFont(textFont);
        backButton.setFont(textFont);
        Dimension buttonSize = new Dimension(200, 50);  // Setting button size
        loginButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(buttonSize);

        panel.add(Box.createVerticalStrut(20), BorderLayout.CENTER);  // Adding spacing

        // Creating another panel for the buttons and adding buttons in it
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(panel.getBackground());  // Setting background color to the parent
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createHorizontalStrut(60));  // Adding spacing
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);  // Add buttons to the bottom of the panel

        // Adding the login fields to the login panel
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(backButton);
        loginPanel.add(loginButton);

        // Add login panel to the center of the main panel
        panel.add(loginPanel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> {
            // When the login button is clicked...
            String username = usernameField.getText();  // Retrieve the text in the fields
            String password = new String(passwordField.getPassword());
            if (userType.equals("Admin")) {  // If the user type is admin
                if (adminValidation(username, password)) {  // Perform the admin validation and if validated, move to the admin menu
                    JOptionPane.showMessageDialog(panel, "Admin successfully logged in");
                    adminMenu(panel, customers, saveLoadManager, movieManager);
                } else {  // If validation was unsuccessfull show a message
                    JOptionPane.showMessageDialog(panel, "Admin username / password is wrong");
                }
            } else if (userType.equals("Customer")) {  // If the user type is customer
                if (customerValidation(username, password, customers)) {  // Perform customer validation and if validated
                    Customer currentCustomer = customers.lookUpCustomer(Integer.parseInt(username));  // Find the customer in the customerStorage
                    JOptionPane.showMessageDialog(panel, "Customer successfully logged in");
                    customerMenu(panel, currentCustomer, customers, saveLoadManager, movieManager);  // Move to the customer menu with the customer object
                } else {  // If the customer couldn't be validated show a message
                    JOptionPane.showMessageDialog(panel, """
                            Customer username / password is wrong.
                            Your username is the last 4 digits of your credit card.
                            Your password is 'password' by default or the password you set.
                            Be sure that you're registered in the system!""");
                }
            }
        });

        backButton.addActionListener(e -> {
            // If the back button is clicked, return to the welcome menu
            panel.removeAll();
            welcomeMenu(panel, customers, saveLoadManager, movieManager);
        });

        // Update the panel
        panel.revalidate();
        panel.repaint();
    }

    private static void customerMenu(JPanel panel, Customer customer, CustomerStorage customers, SaveLoadManager saveLoadManager, MovieManager movieManager) {
        // Customer menu that has the different options for the customer
        panel.removeAll();  // Clear the screen

        // Setting the title of the windwo
        JLabel adminTitleLabel = new JLabel("Welcome Back " + customer.getName(), JLabel.CENTER);
        adminTitleLabel.setFont(titleFont);
        panel.add(adminTitleLabel, BorderLayout.NORTH);

        // Setting up buttons and creating a button panel
        JButton accessMoviesByID = new JButton("Access Movie by ID");  // Creating buttons
        JButton accessWishlist = new JButton("Access Wishlist");
        JButton accessHaveWatched = new JButton("Access the Watched Movies List");
        JButton printMoviesByDate = new JButton("View the movies in order of release date");
        JButton goMainMenu = new JButton("Logout to the main menu");
        accessMoviesByID.setFont(textFont);  // Setting the textFont
        accessWishlist.setFont(textFont);
        printMoviesByDate.setFont(textFont);
        goMainMenu.setFont(textFont);
        accessHaveWatched.setFont(textFont);
        JPanel buttonPanel = new JPanel();  // Creating a panel for storing the buttons
        buttonPanel.setBackground(panel.getBackground());  // Using the parent background
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 15));
        buttonPanel.add(accessMoviesByID);
        buttonPanel.add(accessWishlist);
        buttonPanel.add(printMoviesByDate);
        buttonPanel.add(goMainMenu);
        panel.add(buttonPanel, BorderLayout.CENTER);

        accessMoviesByID.addActionListener(e -> {
            // If the button is clicked run the method that creates the screen with the access movies by id interface
            accessMoviesByID(panel, customer, customers, saveLoadManager, movieManager);
        });

        accessWishlist.addActionListener(e -> {
            // If the button is clicked run the method that creates the screen with the wishlist access interface

        });

        printMoviesByDate.addActionListener(e -> {
            // If thew button is clicked, run the method that creates the screen that prints the movies by release date
            viewByReleaseDateCustomer(panel, customer, customers, saveLoadManager, movieManager);
        });

        goMainMenu.addActionListener(e -> {
            // If the button is clicked, it returns to the main menu.
            loginMenu(panel, "Customer", customers, saveLoadManager, movieManager);
        });

        // Updating the screen
        panel.revalidate();
        panel.repaint();
    }

    private static void viewByReleaseDateCustomer(JPanel panel, Customer customer, CustomerStorage customers, SaveLoadManager saveLoadManager, MovieManager movieManager) {
        // Function to print the movies by release date in the screen
        panel.removeAll();  // Clearing the screen

        // Adding the title label
        JLabel titleLabel = new JLabel("Movies by Release Date", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Create a table model
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Movie Name");
        tableModel.addColumn("Release Date");
        tableModel.addColumn("ID");
        tableModel.addColumn("Score");
        tableModel.addColumn("Availability");

        printAscendingDate(movieManager.getMoviesByDate(), tableModel);  // Special in-order traversal to retrieve nodes by date and saving them into the tableModel

        // Creating the table
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Verdana", Font.PLAIN, 15));
        table.setRowHeight(25);

        // Setting column widths
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);

        // Adding a scrollPane to the table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Set a fixed size
        panel.add(scrollPane, BorderLayout.CENTER);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(textFont);
        backButton.addActionListener(e -> customerMenu(panel, customer, customers, saveLoadManager, movieManager));
        panel.add(backButton, BorderLayout.SOUTH);

        // Refreshing the window
        panel.revalidate();
        panel.repaint();
    }

    private static void accessMoviesByID(JPanel panel, Customer customer, CustomerStorage customers, SaveLoadManager saveLoadManager, MovieManager movieManager) {
        panel.removeAll();  // Clearing the screen

        // Title label
        JLabel titleLabel = new JLabel("Access Movies By ID", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Search Panel for saving the input field, button and text fields
        JPanel searchPanel = new JPanel(new GridLayout(6, 2, 20, 0));
        searchPanel.setBackground(panel.getBackground());

        // Text-field for entering the movieID
        JTextField movieIdField = new JTextField(10);
        movieIdField.setFont(textFont);

        // Setting up the search button and movie labels
        JButton searchButton = new JButton("Search Movie");
        JLabel movieTitleLabel = new JLabel("Title: ");
        JLabel movieReleaseDateLabel = new JLabel("Release Date: ");
        JLabel movieIDLabel = new JLabel("ID: ");
        JLabel movieRatingLabel = new JLabel("Rating: ");
        JLabel movieAvailabilityLabel = new JLabel("Availability: ");
        JLabel empty = new JLabel("");
        JButton addWishlistButton = new JButton("Add Movie to the Wishlist");
        JButton addHaveWatchedButton = new JButton("Add Movie to the Have Watched");
        searchButton.setFont(textFont);  // Setting the font
        movieTitleLabel.setFont(textFont);
        movieReleaseDateLabel.setFont(textFont);
        movieIDLabel.setFont(textFont);
        movieRatingLabel.setFont(textFont);
        movieAvailabilityLabel.setFont(textFont);
        addWishlistButton.setFont(textFont);
        addHaveWatchedButton.setFont(textFont);
        addWishlistButton.setPreferredSize(new Dimension(250, 40)); // Setting the size of the buttons
        addHaveWatchedButton.setPreferredSize(new Dimension(250, 40));

        // Adding the button and the text fields to the searchPanel
        searchPanel.add(movieIdField);
        searchPanel.add(searchButton);
        searchPanel.add(movieTitleLabel);
        searchPanel.add(movieReleaseDateLabel);
        searchPanel.add(movieIDLabel);
        searchPanel.add(movieRatingLabel);
        searchPanel.add(movieAvailabilityLabel);
        searchPanel.add(empty);
        panel.add(searchPanel, BorderLayout.CENTER);  // Adding the search panel to the main panel

        // Creating a back button for going back to the customer menu
        JButton backButton = new JButton("Back");
        backButton.setFont(textFont);
        backButton.addActionListener(e -> customerMenu(panel, customer, customers, saveLoadManager, movieManager));

        searchButton.addActionListener(e -> {
            // Search button that looks for a movie by id
            String movieId = movieIdField.getText();
            if (intValidation(movieId, 10000, 99999)) {  // Checking if the input is a valid integer in the range
                int movieIDInt = Integer.parseInt(movieId);
                Movie foundMovie = movieManager.getMoviesByID().searchMovieByID(movieIDInt);  // If so, looking for the movie
                if (foundMovie != null) {  // If the movie is found, update the text fields and show the details
                    movieTitleLabel.setText("Title: " + foundMovie.getTitle());
                    movieReleaseDateLabel.setText("Release Date: " + foundMovie.convertToDate());
                    movieRatingLabel.setText("Rating: " + foundMovie.getScore());
                    movieIDLabel.setText("ID: " + foundMovie.getID());
                    movieAvailabilityLabel.setText("Availability: " + foundMovie.getAvailability());
                    searchPanel.add(addWishlistButton);  // Also show the buttons as options
                    searchPanel.add(addHaveWatchedButton);

                    addWishlistButton.addActionListener(e12 -> {
                        // If the add wishlist button is clicked, add movie to the wishlist and update the wishlist
                        customer.getWishlist().addMovie(foundMovie);
                        saveLoadManager.saveWishlist(customer.getWishlist());
                        JOptionPane.showMessageDialog(panel, "Movie added to wishlist");
                    });

                    addHaveWatchedButton.addActionListener(e1 -> {
                        // If the have watched button is clicked, add the movie and update the have watched
                        customer.getWatchedList().insertMovie(foundMovie);
                        saveLoadManager.saveHaveWatched(customer.getWatchedList());
                        JOptionPane.showMessageDialog(panel, "Movie added to haveWatched");
                    });
                } else {
                    // If the movie is not found
                    JOptionPane.showMessageDialog(panel, "No Movie Found with the specific movie ID");
                }
            } else {
                // If the input is invalid
                JOptionPane.showMessageDialog(panel, "Please enter a valid movie ID (10000 - 99999)");
            }
        });

        panel.add(backButton, BorderLayout.SOUTH);

        // Updating the screen
        panel.revalidate();
        panel.repaint();
    }

    private static void adminMenu(JPanel panel, CustomerStorage customers, SaveLoadManager saveLoadManager, MovieManager movieManager) {
        // Creating the admin menu
        panel.removeAll();  // Clearing the screen

        // Creating the main title
        JLabel adminTitleLabel = new JLabel("Admin Menu", JLabel.CENTER);
        adminTitleLabel.setFont(titleFont);
        panel.add(adminTitleLabel, BorderLayout.NORTH);

        // Create buttons for admin options
        JButton addMovieButton = new JButton("Add a new Movie");
        JButton leastRatedMovieButton = new JButton("View the least rated movie");
        JButton moviesByDateButton = new JButton("View the movies in order of release date");
        JButton goMainMenu = new JButton("Logout to the main menu");

        // Set font and size for buttons
        Font buttonFont = textFont;
        addMovieButton.setFont(buttonFont);
        leastRatedMovieButton.setFont(buttonFont);
        moviesByDateButton.setFont(buttonFont);
        goMainMenu.setFont(buttonFont);

        // Add buttons to panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(panel.getBackground());
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 15));
        buttonPanel.add(addMovieButton);
        buttonPanel.add(leastRatedMovieButton);
        buttonPanel.add(moviesByDateButton);
        buttonPanel.add(goMainMenu);
        panel.add(buttonPanel, BorderLayout.CENTER);

        addMovieButton.addActionListener(e -> {
            // Calls the function that handles adding a new movie to the system
            newMovie(panel, customers, saveLoadManager, movieManager);
        });

        leastRatedMovieButton.addActionListener(e -> {
            // Calls the function that removes the leastRatedMovie from the system
            removeLeastRated(panel, customers, saveLoadManager, movieManager);
        });

        moviesByDateButton.addActionListener(e -> {
            // Calls the function that will print the movies by release date
            viewByReleaseDateAdmin(panel, customers, saveLoadManager, movieManager);
        });

        goMainMenu.addActionListener(e -> {
            // Calls the function that will return to the main admin menu
            loginMenu(panel, "Admin", customers, saveLoadManager, movieManager);
        });

        // Updating the screen
        panel.revalidate();
        panel.repaint();
    }

    private static void removeLeastRated(JPanel panel, CustomerStorage customers, SaveLoadManager saveLoadManager, MovieManager movieManager) {
        // Function to remove the least rated movie from the system
        panel.removeAll();  // Clearing the screen

        // Setting up the screen title
        JLabel leastRatedTitleLabel = new JLabel("Least Rated Movie", JLabel.CENTER);
        leastRatedTitleLabel.setFont(titleFont);
        panel.add(leastRatedTitleLabel, BorderLayout.NORTH);

        // Saving the leastRated movie in a variable
        Movie leastRated = movieManager.getLeastRatedMovie();
        if (leastRated != null) {  // If the least rated movie is not null (not empty)
            JPanel movieInfoPanel = new JPanel(new GridLayout(5, 1));  // Create a new panel for displaying the information of the movie
            movieInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            movieInfoPanel.setBackground(panel.getBackground());

            // Creating fields for the attributes of the movie
            JLabel titleLabel = new JLabel("Title: " + leastRated.getTitle());
            JLabel releaseDateLabel = new JLabel("Release Date: " + leastRated.convertToDate());
            JLabel idLabel = new JLabel("ID: " + leastRated.getID());
            JLabel ratingLabel = new JLabel("Rating: " + leastRated.getScore());
            JLabel availabilityLabel = new JLabel("Availability: " + leastRated.getAvailability());
            titleLabel.setFont(textFont);  // Setting the font
            releaseDateLabel.setFont(textFont);
            idLabel.setFont(textFont);
            ratingLabel.setFont(textFont);
            availabilityLabel.setFont(textFont);
            movieInfoPanel.add(titleLabel);  // Adding the elements to the panel
            movieInfoPanel.add(releaseDateLabel);
            movieInfoPanel.add(idLabel);
            movieInfoPanel.add(ratingLabel);
            movieInfoPanel.add(availabilityLabel);
            panel.add(movieInfoPanel, BorderLayout.CENTER);  // Adding the infoPanel to the main panel

            // Adding the buttons
            JButton removeButton = new JButton("Remove Least Rated Movie");
            JButton backButton = new JButton("Go Back");
            removeButton.setFont(textFont);
            backButton.setFont(textFont);

            // Action listener for removeButton
            removeButton.addActionListener(e -> {
                // If the user choose to remove the least rated movie
                leastRated.setAvailability(false);  // Set the availability to false
                movieManager.deleteMinScoreMovie();  // Delete the min rated movie
                saveLoadManager.saveMovies(movieManager);  // Update the save/load
                removeLeastRated(panel, customers, saveLoadManager, movieManager);  // Return to the screen again to update
            });

            // If back-button is clicked, return to the adminMenu
            backButton.addActionListener(e -> adminMenu(panel, customers, saveLoadManager, movieManager));

            // Adding the buttons to the screen
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center align the buttons
            buttonPanel.add(removeButton);
            buttonPanel.add(backButton);
            buttonPanel.setBackground(panel.getBackground());
            panel.add(buttonPanel, BorderLayout.SOUTH);
        } else {
            // If there are no movies in the system, prints a message
            JLabel noMoviesLabel = new JLabel("No available movies to show", JLabel.CENTER);
            noMoviesLabel.setFont(titleFont);
            panel.add(noMoviesLabel, BorderLayout.CENTER);

            // Adding a back button that'd return to the adminMenu
            JButton backButton = new JButton("Go Back");
            backButton.setFont(textFont);
            backButton.addActionListener(e -> adminMenu(panel, customers, saveLoadManager, movieManager));
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(backButton);
            buttonPanel.setBackground(panel.getBackground());
            panel.add(buttonPanel, BorderLayout.SOUTH);
        }

        // Updating the screen
        panel.revalidate();
        panel.repaint();
    }

    private static void viewByReleaseDateAdmin(JPanel panel, CustomerStorage customers, SaveLoadManager saveLoadManager, MovieManager movieManager) {
        // Admin function to view the movies by releaseDate
        panel.removeAll();  // Clearing the screen

        // Setting the window title
        JLabel titleLabel = new JLabel("Movies by Release Date", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Creating a table model
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Movie Name");  // Column names
        tableModel.addColumn("Release Date");
        tableModel.addColumn("ID");
        tableModel.addColumn("Score");
        tableModel.addColumn("Availability");

        // Saving the nodes into the tableModel by the special in-order traversal
        printAscendingDate(movieManager.getMoviesByDate(), tableModel);

        // Creating a table from the data
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Verdana", Font.PLAIN, 15));
        table.setRowHeight(25);

        // Setting column widths
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);

        // Adding a scroll pane to the screen
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Setting up the back button
        JButton backButton = new JButton("Back");
        backButton.setFont(textFont);
        backButton.addActionListener(e -> adminMenu(panel, customers, saveLoadManager, movieManager)); // If back button is clicked, go back to the adminMenu
        panel.add(backButton, BorderLayout.SOUTH);

        // Updating the screen
        panel.revalidate();
        panel.repaint();
    }

    private static void newMovie(JPanel panel, CustomerStorage customers, SaveLoadManager saveLoadManager, MovieManager movieManager) {
        // Function that creates the interface for creating new movies
        panel.removeAll();  // Clearing the scree

        // Creating the title
        JLabel loginTitleLabel = new JLabel("New Movie Creation", JLabel.CENTER);
        loginTitleLabel.setFont(titleFont); // Setting font and size
        panel.add(loginTitleLabel, BorderLayout.NORTH); // Add the login title label to the top of the panel

        // Creating login panel for storing the login elements
        JPanel loginPanel = new JPanel(new GridLayout(6, 1, 10, 5));
        loginPanel.setBackground(panel.getBackground());  // Setting background to the parent's background

        // Setting up the input labels, text-fields and buttons
        JLabel movieNameLabel = new JLabel("Movie Name", JLabel.CENTER);
        JLabel releaseDateLabel = new JLabel("Release Date", JLabel.CENTER);
        JLabel idLabel = new JLabel("ID", JLabel.CENTER);
        JLabel ratingLabel = new JLabel("Rating", JLabel.CENTER);
        JLabel availableLabel = new JLabel("Available (0/1)", JLabel.CENTER);
        JTextField movieNameField = new JTextField();
        JTextField releaseDateField = new JTextField();
        JTextField idField = new JTextField();
        JTextField ratingField = new JTextField();
        JTextField avaliableField = new JTextField();
        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Go Back");
        movieNameLabel.setFont(textFont);  // Setting the fonts
        movieNameField.setFont(textFont);
        releaseDateLabel.setFont(textFont);
        releaseDateField.setFont(textFont);
        ratingLabel.setFont(textFont);
        ratingField.setFont(textFont);
        idLabel.setFont(textFont);
        idField.setFont(textFont);
        availableLabel.setFont(textFont);
        avaliableField.setFont(textFont);
        submitButton.setFont(textFont);
        backButton.setFont(textFont);
        Dimension buttonSize = new Dimension(200, 50);  // Setting button sizes
        submitButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(buttonSize);

        panel.add(Box.createVerticalStrut(20), BorderLayout.CENTER); // Adding spacing

        // Adding buttons to a panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(panel.getBackground());
        buttonPanel.add(submitButton);
        buttonPanel.add(Box.createHorizontalStrut(60));
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> {
            // If the submit button is clicked...
            String movieName = movieNameField.getText();  // Retrieve the data in the text fields
            String releaseDate = releaseDateField.getText();
            String id = idField.getText();
            String rating = ratingField.getText();
            String available = avaliableField.getText();
            if (newMovieValidation(movieName, releaseDate, id, rating, available)) {  // Perform validation
                int releaseDateNumeric = Integer.parseInt(releaseDateField.getText());
                int idNumeric = Integer.parseInt(idField.getText());
                int ratingNumeric = Integer.parseInt(ratingField.getText());
                boolean availableBoolean = Integer.parseInt(avaliableField.getText()) == 1;
                Movie newMovie = new Movie(movieName, releaseDateNumeric, idNumeric, ratingNumeric, availableBoolean);  // If valid, create a new movie
                movieManager.insert(newMovie);  // Insert the movie in the movie manager
                saveLoadManager.saveMovies(movieManager);  // Save the data
                JOptionPane.showMessageDialog(panel, movieName + " added successfully");  // Print a message to the screen
                adminMenu(panel, customers, saveLoadManager, movieManager);  // Return to the admin menu
            } else {  // If validation is failed, print a message to the screen and list the rules.
                JOptionPane.showMessageDialog(panel, """
                        Invalid Input!
                        Please be sure that all of the fields are filled.
                        Release Date is in the valid range of (10000101-99999999)
                        ID is in the valid range of 10000-99999
                        Rating is in the valid range of 0-100
                        And availability is 0 or 1""");
            }
        });

        backButton.addActionListener(e -> adminMenu(panel, customers, saveLoadManager, movieManager));  // If the back button is clicked, return to the admin menu

        // Add the elements to the login panel
        loginPanel.add(movieNameLabel);
        loginPanel.add(movieNameField);
        loginPanel.add(releaseDateLabel);
        loginPanel.add(releaseDateField);
        loginPanel.add(idLabel);
        loginPanel.add(idField);
        loginPanel.add(ratingLabel);
        loginPanel.add(ratingField);
        loginPanel.add(availableLabel);
        loginPanel.add(avaliableField);
        loginPanel.add(submitButton);
        loginPanel.add(backButton);

        // Adding the login panel to the screen
        panel.add(loginPanel, BorderLayout.CENTER);

        // Updating the screen
        panel.revalidate();
        panel.repaint();
    }

    private static boolean adminValidation(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    private static boolean customerValidation(String username, String password, CustomerStorage customers) {
        if (!intValidation(username, 1000, 9999)) {
            return false;
        } else {
            int usernameInt = Integer.parseInt(username);
            Customer foundCustomer = customers.lookUpCustomer(usernameInt);
            if (foundCustomer == null) {
                return false;
            } else {
                String customerPassword = foundCustomer.getPassword();
                return password.equals(customerPassword);
            }
        }
    }

    private static boolean newCustomerValidation(String username, String email, String credit, String password) {
        // Checking if the entered credentials are valid email / integer / not empty.
        return intValidation(credit, 1000, 9999) && emailValidation(email) && !username.isEmpty() && !password.isEmpty();
    }

    private static boolean intValidation(String textInt, int lower, int higher) {
        // Function that checks if an input is a valid integer in the valid range
        int intNumber;
        try {
            intNumber = Integer.parseInt(textInt);  // Trying to parse int
        } catch (NumberFormatException e) {  // If this fails, this means that it's not a valid integer
            return false;
        }
        return (intNumber >= lower) && (intNumber <= higher);  // Checkin for the range
    }

    private static boolean emailValidation(String email) {
        // Function that performs email validation by checking different rules:
        // 1. Email contains a valid ending
        // 2. Email contains @ (doesn't start or end with it)
        // 3. Both sides of the @ is full (preventing @yahoo.com or derin@.com)
        return email.contains("@") && !email.startsWith("@") && !email.endsWith("@") && !email.contains("@.") && (email.endsWith(".com") || email.endsWith(".edu") || email.endsWith(".gov") || email.endsWith(".org") || email.endsWith(".net") || email.endsWith(".info") || email.endsWith(".biz"));
    }

    private static boolean newMovieValidation(String movieName, String releaseDate, String ID, String rating, String available) {
        // Performing validation in the new movie creation process.
        // Checks if the name field is not empty and the other fields are integers in the valid range
        return !movieName.isEmpty() && intValidation(releaseDate, 10000101, 99999999) && intValidation(ID, 10000, 99999) && intValidation(rating, 0, 100) && intValidation(available, 0, 1);
    }

    private static void printAscendingDate(MoviesByDate DateBST, DefaultTableModel tableModel) {
        // Special in-order traversal to print the movies by date and adding them to a table
        ascend(DateBST.getRoot(), tableModel);
        System.out.println();
    }

    private static void ascend(Movie currentMovie, DefaultTableModel tableModel) {
        // Recursive ascend function which will append the elements of the movie into an object and append it to the table
        if (currentMovie != null) {
            ascend(currentMovie.getRightDateMovie(), tableModel);
            Object[] rowData = {currentMovie.getTitle(), currentMovie.convertToDate(), currentMovie.getID(), currentMovie.getScore(), currentMovie.getAvailability()};
            tableModel.addRow(rowData);
            ascend(currentMovie.getLeftDateMovie(), tableModel);
        }
    }
}

