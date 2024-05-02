/*
Customer storage to store the customers by their credit card numbers.
This is a dictionary implemented with the separate chaining method
 */

package mainStructures;

import java.io.Serializable;

public class CustomerStorage implements Serializable {
    private static final long serialVersionUID = 12345678910L;
    private Customer[] customers;
    private final int size = 31;  // We determined the size here in order to easily modify the program

    public CustomerStorage() {
        customers = new Customer[size];
    }

    public Customer lookUpCustomer(int customerCredit) {
        // Function to lookup for a customer in the customer-dictionary with their credit card number
        Customer customer = customers[hash(customerCredit)];
        while (customer != null) {
            if (customer.getCredit() == customerCredit) {
                return customer;
            }
            customer = customer.getNextCustomer();
        }
        return null;
    }

    public void deleteCustomer(int customerCredit) {
        // Function to delete a customer from the dictionary
        Customer target = lookUpCustomer(customerCredit);
        if (target == null) {
            return;
        }

        Customer prev = null;
        Customer current = customers[hash(target.getCredit())];

        while (current != null) {
            if (current == target) {
                if (prev == null) {
                    customers[hash(target.getCredit())] = current.getNextCustomer();
                } else {
                    prev.setNextCustomer(current.getNextCustomer());
                }
                current.setNextCustomer(null);
                return;
            }
            prev = current;
            current = current.getNextCustomer();
        }
    }

    public void insertCustomer(Customer newCustomer) {
        // Function to insert a customer to the customer dictionary
        if (lookUpCustomer(newCustomer.getCredit()) != null) {
            return;
        }
        System.out.println("Inserting customer: " + newCustomer.getName());
        Customer temp = customers[hash(newCustomer.getCredit())];
        if (temp == null) {
            customers[hash(newCustomer.getCredit())] = newCustomer;
            return;
        }
        while (temp.getNextCustomer() != null) {
            temp = temp.getNextCustomer();
        }
        temp.setNextCustomer(newCustomer);
    }

    public void printCustomers() {
        // Function to print the customers credit-card numbers
        for (int i = 0; i < size; i++) {
            System.out.print("|" + i + "|");
            System.out.print(" - ");
            if (customers[i] == null) {
                System.out.print("null");
            } else {
                Customer temp = customers[i];
                while (temp != null) {
                    System.out.print(temp.getCredit());
                    temp = temp.getNextCustomer();
                    System.out.print(" --> ");
                    if (temp == null) {
                        System.out.print("null");
                    }
                }
            }
            System.out.println();
        }
    }

    private int hash(int key) {
        // Simple hash function to find the right index
        return key % size;
    }
}
