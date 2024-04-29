/*
A save-load manager class to store the methods that handles the serialization of our program.
We need these function to save our data to files and read the data from the files.
We created a separate file for these functions in order to be organized
 */

package managers;

import structures.*;

import java.io.*;

public class SaveLoadManager implements Serializable {
    public void saveCustomers(CustomerStorage customers) {
        // Function that save the customer storage dictionary to the customers.ser file
        try {
            FileOutputStream file = new FileOutputStream("data/customers.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(customers);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CustomerStorage loadCustomers() {
        // Function that reads the dictionary that stores the customers from the customers.ser file
        CustomerStorage customers = new CustomerStorage();
        try {
            File file = new File("data/customers.ser");
            boolean fileCreated = file.createNewFile();
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInput);
            customers = (CustomerStorage) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("data/customer.ser file is not found");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (EOFException e) {
            System.out.println("data/customer.ser is Empty");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void saveWishlist(Wishlist wishlist) {
        // Customer that saves the wishlist queue to the wishlist.ser file
        try {
            FileOutputStream file = new FileOutputStream("data/wishlist.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(wishlist);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Wishlist loadWishlist() {
        // Function that loads the wishlist queue from the wishlist.ser file
        Wishlist wishlist = new Wishlist();
        try {
            File file = new File("data/wishlist.ser");
            boolean fileCreated = file.createNewFile();
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInput);
            wishlist = (Wishlist) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("data/wishlist.ser file is not found");
        } catch (EOFException e) {
            System.out.println("data/wishlist.ser is Empty");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wishlist;
    }

    public void saveMovieScoresHeap(MovieScoresHeap movieScoresHeap) {
        // Function that saves the movie scores heap to the movieScoresHeap.ser file
        try {
            FileOutputStream file = new FileOutputStream("data/movieScoresHeap.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(movieScoresHeap);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MovieScoresHeap loadMovieScoresHeap() {
        // Function that loads the movie scores heap from the movieScoresHeap.ser file
        MovieScoresHeap movieScoresHeap = new MovieScoresHeap();
        try {
            File file = new File("data/movieScoresHeap.ser");
            boolean fileCreated = file.createNewFile();
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInput);
            movieScoresHeap = (MovieScoresHeap) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("data/movieScoresHeap.ser file is not found");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (EOFException e) {
            System.out.println("data/movieScoresHeap.ser is Empty");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieScoresHeap;
    }

    public void saveMoviesByID(MoviesByID moviesByID) {
        // Function that saves the ID BST to the moviesByID.ser file
        try {
            FileOutputStream file = new FileOutputStream("data/moviesByID.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(moviesByID);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MoviesByID loadMoviesByID() {
        // Function that loads the ID BST from the moviesByID.ser file
        MoviesByID moviesByID = new MoviesByID();
        try {
            File file = new File("data/moviesByID.ser");
            boolean fileCreated = file.createNewFile();
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInput);
            moviesByID = (MoviesByID) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("data/moviesByID.ser file is not found");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (EOFException e) {
            System.out.println("data/moviesByID.ser is Empty");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moviesByID;
    }

    public void saveMoviesByDate(MoviesByDate moviesByDate) {
        // Function that saves the Date BST to moviesByDate.ser file
        try {
            FileOutputStream file = new FileOutputStream("data/moviesByDate.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(moviesByDate);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MoviesByDate loadMoviesByDate() {
        // Function that loads the Date BST to moviesByDate.ser file
        MoviesByDate moviesByDate = new MoviesByDate();
        try {
            File file = new File("data/moviesByDate.ser");
            boolean fileCreated = file.createNewFile();
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInput);
            moviesByDate = (MoviesByDate) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("data/moviesByDate.ser file is not found");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (EOFException e) {
            System.out.println("data/moviesByDate.ser is Empty");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moviesByDate;
    }

    public void saveAllData(CustomerStorage customerStorage, Wishlist wishlist, MovieScoresHeap movieScoresHeap, MoviesByID moviesByID, MoviesByDate moviesByDate) {
        System.out.println("Saving all data...");
        saveCustomers(customerStorage);
        saveWishlist(wishlist);
        saveMovieScoresHeap(movieScoresHeap);
        saveMoviesByID(moviesByID);
        saveMoviesByDate(moviesByDate);
        System.out.println("Done");
    }

    public void clearAllData() {
        try {
            new FileWriter("data/customers.ser", false).close();
            new FileWriter("data/wishlist.ser", false).close();
            new FileWriter("data/moviesByID.ser", false).close();
            new FileWriter("data/moviesByDate.ser", false).close();
            new FileWriter("data/movieScoresHeap.ser", false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
