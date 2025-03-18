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
    
    /**
     * Constructor for BookStore
     * 
     * TODO: Initialize fields and load data from files
     */
    public BookStore() {
        // TODO: Initialize books, customers and manager
        // TODO: Load data from files
    }
    
    /**
     * Add a book to the bookstore
     * 
     * TODO: Add the book to the collection
     */
    public void addBook(Book book) {
        // TODO: Add book to books list
    }
    
    /**
     * Remove a book from the bookstore
     * 
     * TODO: Remove the book from the collection
     */
    public void removeBook(Book book) {
        // TODO: Remove book from books list
    }
    
    /**
     * Add a customer to the bookstore
     * 
     * TODO: Add the customer to the collection
     */
    public void addCustomer(Customer customer) {
        // TODO: Add customer to customers list
    }
    
    /**
     * Remove a customer from the bookstore
     * 
     * TODO: Remove the customer from the collection
     */
    public void removeCustomer(Customer customer) {
        // TODO: Remove customer from customers list
    }
    
    /**
     * Get all books in the bookstore
     * 
     * TODO: Return the list of books
     */
    public List<Book> getBooks() {
        // TODO: Return books list
        return null;
    }
    
    /**
     * Get all customers in the bookstore
     * 
     * TODO: Return the list of customers
     */
    public List<Customer> getCustomers() {
        // TODO: Return customers list
        return null;
    }
    
    /**
     * Get the manager
     * 
     * TODO: Return the manager
     */
    public Manager getManager() {
        // TODO: Return manager
        return null;
    }
    
    /**
     * Find a customer by username
     * 
     * TODO: Search for and return the customer with the given username
     */
    public Customer findCustomer(String username) {
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
        // TODO: Check if it's the manager
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
        // TODO: Calculate total cost
        // TODO: Apply points if requested
        // TODO: Add points for this purchase (10 points per CAD)
        // TODO: Return the total cost
        return 0.0;
    }
    
    /**
     * Load data from files
     * 
     * TODO: Load books.txt and customers.txt
     */
    private void loadData() {
        // TODO: Call loadBooks() and loadCustomers()
    }
    
    /**
     * Save data to files
     * 
     * TODO: Save to books.txt and customers.txt
     */
    public void saveData() {
        // TODO: Call saveBooks() and saveCustomers()
    }
    
    /**
     * Load books from books.txt
     * 
     * TODO: Read the books.txt file and populate the books list
     */
    private void loadBooks() {
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
        // TODO: Open books.txt for writing
        // TODO: Write each book's name and price to the file
    }
    
    /**
     * Load customers from customers.txt
     * 
     * TODO: Read the customers.txt file and populate the customers list
     */
    private void loadCustomers() {
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
        // TODO: Open customers.txt for writing
        // TODO: Write each customer's username, password, and points to the file
    }
} 