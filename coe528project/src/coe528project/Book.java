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
     * TODO: Initialize book with name and price
     */
    public Book(String name, double price) {
        this.name = name;
        this.price = price;
        // TODO: Set the name and price
    }
    
    /**
     * Get the name of the book
     * 
     * TODO: Return the book name
     */
    public String getName() {
        // TODO: Return the name
        return this.name;
    }
    
    /**
     * Set the name of the book
     * 
     * TODO: Set the book name
     */
    public void setName(String name) {
        // TODO: Set the name
        this.name = name;
    }
    
    /**
     * Get the price of the book
     * 
     * TODO: Return the book price
     */
    public double getPrice() {
        // TODO: Return the price
        return this.price;
    }
    
    /**
     * Set the price of the book
     * 
     * TODO: Set the book price
     */
    public void setPrice(double price) {
        // TODO: Set the price
        this.price = price;
    }
    
    /**
     * String representation of the book
     * 
     * TODO: Return a string with book name and price
     */
    @Override
    public String toString() {
        // TODO: Return formatted string with name and price
        return ("Name: "+name+" $"+price);
    }
}
