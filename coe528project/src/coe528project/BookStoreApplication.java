package coe528project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Main application class for the BookStore
 * 
 * Students need to implement this class to:
 * 1. Create a single-window GUI application
 * 2. Implement login screen
 * 3. Implement owner screens (start, books, customers)
 * 4. Implement customer screens (start, cost)
 * 5. Handle book purchases and point redemption
 */
public class BookStoreApplication extends Application {
    
    private Stage primaryStage;
    private BookStore bookStore;
    private Profile currentUser;
    
    // Login screen elements
    private TextField usernameField;
    private PasswordField passwordField;
    
    // Book table elements
    private TableView<Book> bookTableView;
    private TableColumn<Book, String> bookNameColumn;
    private TableColumn<Book, Double> bookPriceColumn;
    private TableColumn<Book, CheckBox> bookSelectColumn;
    
    // Customer table elements
    private TableView<Customer> customerTableView;
    private TableColumn<Customer, String> customerUsernameColumn;
    private TableColumn<Customer, String> customerPasswordColumn;
    private TableColumn<Customer, Integer> customerPointsColumn;
    
    @Override
    public void start(Stage primaryStage) {
        // TODO: Initialize the primary stage and bookstore
        this.primaryStage = primaryStage;
        this.bookStore = new BookStore();
        // TODO: Set up window close handling to save data
        // TODO: Show login screen
    }
    
    /**
     * Show the login screen
     * 
     * TODO: Implement this method to create a login form with:
     * - Username field
     * - Password field
     * - Login button
     */
    private void showLoginScreen() {
        // TODO: Create login form elements
        // TODO: Set up event handlers
        // TODO: Create the layout
        // TODO: Set the scene
    }
    
    /**
     * Handle login button click
     * 
     * TODO: Implement this method to:
     * - Validate username and password
     * - Navigate to appropriate screen based on user type
     */
    private void handleLogin() {
        // TODO: Get username and password
        // TODO: Call bookStore.login()
        // TODO: Display appropriate screen based on user role
    }
    
    /**
     * Show the owner start screen
     * 
     * TODO: Implement this method to create the owner start screen with:
     * - Books button
     * - Customers button
     * - Logout button
     */
    private void showOwnerStartScreen() {
        // TODO: Create buttons with event handlers
        // TODO: Create layout
        // TODO: Set the scene
    }
    
    /**
     * Show the owner books screen
     * 
     * TODO: Implement this method to create a screen to manage books:
     * - Table of books (name, price)
     * - Form to add new books
     * - Delete button for selected book
     * - Back button
     */
    private void showOwnerBooksScreen() {
        // TODO: Create table with columns
        // TODO: Create form controls
        // TODO: Set up event handlers
        // TODO: Create layout
        // TODO: Set the scene
    }
    
    /**
     * Show the owner customers screen
     * 
     * TODO: Implement this method to create a screen to manage customers:
     * - Table of customers (username, password, points)
     * - Form to add new customers
     * - Delete button for selected customer
     * - Back button
     */
    private void showOwnerCustomersScreen() {
        // TODO: Create table with columns
        // TODO: Create form controls
        // TODO: Set up event handlers
        // TODO: Create layout
        // TODO: Set the scene
    }
    
    /**
     * Show the customer start screen
     * 
     * TODO: Implement this method to create a screen for customers to:
     * - Display welcome message with points and status
     * - Show table of books with checkboxes for selection
     * - Provide buttons to buy, redeem points and buy, and logout
     */
    private void showCustomerStartScreen(Customer customer) {
        // TODO: Create table with checkboxes
        // TODO: Create welcome message
        // TODO: Create buttons with event handlers
        // TODO: Create layout
        // TODO: Set the scene
    }
    
    /**
     * Handle buy button click
     * 
     * TODO: Implement this method to:
     * - Get selected books
     * - Process purchase
     * - Show cost screen
     */
    private void handleBuy(Customer customer, List<Book> books, List<CheckBox> checkBoxes, boolean usePoints) {
        // TODO: Find selected books
        // TODO: Call bookStore.processPurchase()
        // TODO: Show customer cost screen
    }
    
    /**
     * Show the customer cost screen
     * 
     * TODO: Implement this method to show:
     * - Total cost
     * - Updated points and status
     * - Logout button
     */
    private void showCustomerCostScreen(Customer customer, double totalCost) {
        // TODO: Create labels
        // TODO: Create logout button
        // TODO: Create layout
        // TODO: Set the scene
    }
    
    /**
     * Log out the current user
     * 
     * TODO: Implement this method to:
     * - Reset current user
     * - Return to login screen
     */
    private void logout() {
        // TODO: Reset current user
        // TODO: Show login screen
    }
    
    /**
     * Show an alert dialog
     * 
     * TODO: Implement this method to display alerts to the user
     */
    private void showAlert(String title, String message) {
        // TODO: Create and show an alert dialog
    }
    
    /**
     * Main method
     */
    public static void main(String[] args) {
        launch(args);
    }
} 
