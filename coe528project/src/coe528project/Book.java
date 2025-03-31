package coe528project;

/**
 * Book class representing a book in the bookstore
 * 
 * Students need to implement this class to:
 * 1. Store book information (name and price)
 * 2. Provide getter and setter methods
 */
public class Book {
    private String name;
    private double price;
    
    /**
     * Constructor for Book class
     * 
     * @param name The name of the book
     * @param price The price of the book
     */
    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    /**
     * Get the name of the book
     * 
     * @return The book name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Set the name of the book
     * 
     * @param name The new book name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the price of the book
     * 
     * @return The book price
     */
    public double getPrice() {
        return this.price;
    }
    
    /**
     * Set the price of the book
     * 
     * @param price The new book price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * String representation of the book
     * 
     * @return A string with book name and price
     */
    @Override
    public String toString() {
        return name + "," + price;
    }
}
