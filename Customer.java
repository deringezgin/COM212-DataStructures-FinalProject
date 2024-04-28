/*
Customer Node for creating individual customers
It stores the simple customer info: name, credit card number, email
It has a wishlist and a have-watched list
It has a next pointer to use in the customer dictionary which we implemented using separate chaining
 */

import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private String email;
    private int credit;
    private Wishlist wishlist;
    private HaveWatched haveWatched;
    private Customer nextCustomer;

    public Customer(String name, String email, int credit) {
        this.name = name;
        this.credit = credit;
        this.email = email;
        this.wishlist = new Wishlist();
        this.haveWatched = new HaveWatched();
    }

    // Getter and setter functions for accessing and modifying the instance variables of the Customer object
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public HaveWatched getWatchedList() {
        return haveWatched;
    }

    public Customer getNextCustomer() {
        return nextCustomer;
    }

    public void setNextCustomer(Customer nextCustomer) {
        this.nextCustomer = nextCustomer;
    }

    @Override
    public String toString() {
        return "CUSTOMER DETAILS" +
                "\nCustomer Name: " + this.name +
                "\nCustomer Credit Card Number: " + this.credit +
                "\nCustomer Email: " + this.email;
    }
}
