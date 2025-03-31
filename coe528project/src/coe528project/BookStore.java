package coe528project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * BookStore class manages books and customers
 * 
 * This class handles all bookstore operations including:
 * 1. Store and manage books and customers
 * 2. Handle user authentication
 * 3. Process book purchases
 * 4. Save and load data from files
 */
public class BookStore {
    private List<Book> books;
    private List<Customer> customers;
    private Manager manager;
    private static BookStore instance = null;
    
    /**
     * Constructor for BookStore
     * 
     * Initializes fields and loads data from files
     */
    public BookStore() {
        books = new ArrayList<>();
        customers = new ArrayList<>();
        manager = new Manager();
        loadData();
    }

    /**
     * Get the singleton instance of BookStore
     * 
     * @return The BookStore instance
     */
    public static BookStore getInstance() {
        if (instance == null) {
            instance = new BookStore();
        }
        return instance;
    }
    
    /**
     * Add a book to the bookstore
     * 
     * @param book The book to add
     */
    public void addBook(Book book) {
        books.add(book);
    }
    
    /**
     * Remove a book from the bookstore
     * 
     * @param book The book to remove
     */
    public void removeBook(Book book) {
        books.remove(book);
    }
    
    /**
     * Add a customer to the bookstore
     * 
     * @param customer The customer to add
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    
    /**
     * Remove a customer from the bookstore
     * 
     * @param customer The customer to remove
     */
    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }
    
    /**
     * Get all books in the bookstore
     * 
     * @return The list of books
     */
    public List<Book> getBooks() {
        return books;
    }
    
    /**
     * Get all customers in the bookstore
     * 
     * @return The list of customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }
    
    /**
     * Get the manager
     * 
     * @return The manager
     */
    public Manager getManager() {
        return manager;
    }
    
    /**
     * Find a customer by username and password
     * 
     * @param username The username to search for
     * @param password The password to validate
     * @return The customer if found, null otherwise
     */
    public Customer findCustomer(String username, String password) {
        for (Customer c : customers) {
            if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
                return c;
            }
        }
        return null;
    }
    
    /**
     * Check if a user can login
     * 
     * @param username The username to check
     * @param password The password to validate
     * @return The Profile object if login successful, null otherwise
     */
    public Profile login(String username, String password) {
        if (Manager.validate(username, password)) {
            return manager;
        }
        
        Customer customer = findCustomer(username, password);
        if (customer != null) {
            return customer;
        }
        
        return null;
    }
    
    /**
     * Process a purchase
     * 
     * @param customer The customer making the purchase
     * @param selectedBooks The books being purchased
     * @param usePoints Whether to use points for the purchase
     * @return The final cost after applying points (if requested)
     */
    public double processPurchase(Customer customer, List<Book> selectedBooks, boolean usePoints) {
        double totalCost = 0.0;
        for (Book book : selectedBooks) {
            totalCost += book.getPrice();
        }
        
        double finalCost = totalCost;
        
        // Apply points if requested
        if (usePoints && customer.getPoints() > 0) {
            finalCost = customer.redeemPoints(totalCost);
        }
        
        // Add points for this purchase (10 points per CAD)
        if (finalCost > 0) {
            int pointsEarned = (int) (finalCost * 10);
            customer.addPoints(pointsEarned);
        }
        
        return finalCost;
    }
    
    /**
     * Load data from files
     */
    private void loadData() {
        loadBooks();
        loadCustomers();
    }
    
    /**
     * Save data to files
     */
    public void saveData() {
        saveBooks();
        saveCustomers();
    }
    
    /**
     * Load books from books.txt
     */
    private void loadBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    try {
                        double price = Double.parseDouble(parts[1]);
                        books.add(new Book(name, price));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid price format: " + parts[1]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("books.txt not found, creating new file when saving.");
        } catch (IOException e) {
            System.err.println("Error loading books: " + e.getMessage());
        }
    }
    
    /**
     * Save books to books.txt
     */
    private void saveBooks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("books.txt"))) {
            for (Book book : books) {
                writer.println(book.getName() + "," + book.getPrice());
            }
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
    }
    
    /**
     * Load customers from customers.txt
     */
    private void loadCustomers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0];
                    String password = parts[1];
                    try {
                        int points = Integer.parseInt(parts[2]);
                        customers.add(new Customer(username, password, points));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid points format: " + parts[2]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("customers.txt not found, creating new file when saving.");
        } catch (IOException e) {
            System.err.println("Error loading customers: " + e.getMessage());
        }
    }
    
    /**
     * Save customers to customers.txt
     */
    private void saveCustomers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("customers.txt"))) {
            for (Customer customer : customers) {
                writer.println(customer.getUsername() + "," + customer.getPassword() + "," + customer.getPoints());
            }
        } catch (IOException e) {
            System.err.println("Error saving customers: " + e.getMessage());
        }
    }
} 
