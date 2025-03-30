package coe528project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * BookStore class manages books and customers
 * 
 * Students need to implement this class to:
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
     * TODO: Initialize fields and load data from files
     */
    public BookStore() {
        books = new ArrayList<>();
        customers = new ArrayList<>();
        loadData();
        // TODO: Initialize books, customers and manager
        // TODO: Load data from files
    }

    public static BookStore getInstance() {
        if (instance == nul) {
            instance = new BookStore();
        }
        return instance;
    }
    /**
     * Add a book to the bookstore
     * 
     * TODO: Add the book to the collection
     */
    public void addBook(Book book) {
        books.add(book);
        // TODO: Add book to books list
    }
    
    /**
     * Remove a book from the bookstore
     * 
     * TODO: Remove the book from the collection
     */
    public void removeBook(Book book) {
        books.remove(book);
        // TODO: Remove book from books list
    }
    
    /**
     * Add a customer to the bookstore
     * 
     * TODO: Add the customer to the collection
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
        // TODO: Add customer to customers list
    }
    
    /**
     * Remove a customer from the bookstore
     * 
     * TODO: Remove the customer from the collection
     */
    public void removeCustomer(Customer customer) {
        customers.remove(customer);
        // TODO: Remove customer from customers list
    }
    
    /**
     * Get all books in the bookstore
     * 
     * TODO: Return the list of books
     */
    public List<Book> getBooks() {
        // TODO: Return books list
        return books;
    }
    
    /**
     * Get all customers in the bookstore
     * 
     * TODO: Return the list of customers
     */
    public List<Customer> getCustomers() {
        // TODO: Return customers list
        return customers;
    }
    
    /**
     * Get the manager
     * 
     * TODO: Return the manager
     */
    public Manager getManager() {
        // TODO: Return manager
        return manager;
    }
    
    /**
     * Find a customer by username
     * 
     * TODO: Search for and return the customer with the given username
     */
    public Customer findCustomer(String username, String password) {
        for (Customer c : customers) {
            if (c.getUsername().equal(username) && c.getPassword().equals(password)) {
                return c;
            }
        }
        // TODO: Find and return customer
        return null;
    }
    
    /**
     * Check if a user can login
     * 
     * TODO: Check if the username and password match a valid user
     * TODO: Return the Profile object if login successful, null otherwise
     */
    public Profile login(String username, String password) {
        if (Manager.validate(username, password)) {
            Profile profile = Profile.getInstance();
            profile.setManager(true);
            profile.setCustomer(null);
            return profile;
        }
        // TODO: Check if it's the manager
        Customer customer = findCustomer(username, password);
        if (customer != null) {
            Profile profile = Profile.getInstance();
            profile.setManager(false);
            profile.setCustomer(customer);
            return profile;
        }
        // TODO: Check if it's a customer
        // TODO: Return the profile or null
        return null;
    }
    
    /**
     * Process a purchase
     * 
     * TODO: Calculate the total cost of the selected books
     * TODO: Apply points if requested
     * TODO: Add new points earned from the purchase
     * TODO: Return the final cost
     */
    public double processPurchase(Customer customer, List<Book> selectedBooks, boolean usePoints) {
        double totalCost = 0.0;
        for (Book book : selectedBooks) {
            totalCost += book.getPrice();
        }
        
        double finalCost = totalCost;
        
        // Apply points if requested
        if (usePoints && customer.getPoints() > 0) {
            double discountAmount = customer.getPoints() / 100.0; // $1 per 100 points
            
            if (discountAmount >= totalCost) {
                // Enough points to cover the entire purchase
                int pointsRedeemed = (int) (totalCost * 100);
                customer.redeemPoints(pointsRedeemed);
                finalCost = 0.0;
            } else {
                // Partial discount
                int pointsRedeemed = customer.getPoints();
                customer.redeemPoints(pointsRedeemed);
                finalCost = totalCost - discountAmount;
            }
        }
        
        // Add points for this purchase (10 points per CAD)
        if (finalCost > 0) {
            int pointsEarned = (int) (finalCost * 10);
            customer.addPoints(pointsEarned);
        }
        
        return finalCost;
    }
        // TODO: Calculate total cost
        // TODO: Apply points if requested
        // TODO: Add points for this purchase (10 points per CAD)
        // TODO: Return the total cost
    
    /**
     * Load data from files
     * 
     * TODO: Load books.txt and customers.txt
     */
    private void loadData() {
        loadBooks();
        loadCustomers();
        // TODO: Call loadBooks() and loadCustomers()
    }
    
    /**
     * Save data to files
     * 
     * TODO: Save to books.txt and customers.txt
     */
    public void saveData() {
        saveBooks();
        saveCustomers();
        // TODO: Call saveBooks() and saveCustomers()
    }
    
    /**
     * Load books from books.txt
     * 
     * TODO: Read the books.txt file and populate the books list
     */
    private void loadBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt))) {
           String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == ) {
                    String name = parts[0];
                    double price = Double.parseDouble(part[1]);
                    books.add(new Book(name, price));
                }
            }
        }
        catch (FileNotFoundException e) {
        }
        catch (IOException e) {
            System.err.println("Error loading books:" + e.getMessage());
        }
        // TODO: Open and read books.txt
        // TODO: Parse each line to get book name and price
        // TODO: Create Book objects and add them to the books list
    }
    
    /**
     * Save books to books.txt
     * 
     * TODO: Write the books list to books.txt
     */
    private void saveBooks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("books.txt"))) {
            for (Book book : books) {
                writer.println(book.toString());
            }
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
        // TODO: Open books.txt for writing
        // TODO: Write each book's name and price to the file
    }
    
    /**
     * Load customers from customers.txt
     * 
     * TODO: Read the customers.txt file and populate the customers list
     */
    private void loadCustomers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0];
                    String password = parts[1];
                    int points = Integer.parseInt(parts[2]);
                    customers.add(new Customer(username, password, points));
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, that's okay for a new system
        } catch (IOException e) {
            System.err.println("Error loading customers: " + e.getMessage());
        }
        // TODO: Open and read customers.txt
        // TODO: Parse each line to get username, password, and points
        // TODO: Create Customer objects and add them to the customers list
    }
    
    /**
     * Save customers to customers.txt
     * 
     * TODO: Write the customers list to customers.txt
     */
    private void saveCustomers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("customers.txt"))) {
            for (Customer customer : customers) {
                writer.println(customer.toString());
            }
        } catch (IOException e) {
            System.err.println("Error saving customers: " + e.getMessage());
        }
        // TODO: Open customers.txt for writing
        // TODO: Write each customer's username, password, and points to the file
    }
} 
