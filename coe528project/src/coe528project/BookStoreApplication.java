package coe528project;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

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
    
    ObservableList booklist = FXCollections.observableArrayList(
          // new Book("Hungary Hippo",2.99),
           //new Book("Blood Like Majic",24.99)
                
        );
    
    ObservableList Customerlist = FXCollections.observableArrayList(
          // new Book("Hungary Hippo",2.99),
           //new Book("Blood Like Majic",24.99)
                
        );
    
    private Stage primaryStage;
    private BookStore bookStore;
    private Profile currentUser;
    
    // Login screen elements
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    
    // Book table elements
    @FXML
    private TableView<Book> bookTableView;
    @FXML
    public TableColumn<Book, String> bookNameColumn;
    @FXML
    public TableColumn<Book, Double> bookPriceColumn;
    @FXML
    private TableColumn<Book, CheckBox> bookSelectColumn;
    
    @FXML
    private TextField AddBookName;
    
    @FXML
    private TextField AddBookPrice;
    
    @FXML
    private TextField AddCustomerName;
    
    @FXML
    private TextField AddCustomerPoints;
    
    @FXML
    private TextField AddCustomerPassword;
    
    
    // Customer table elements
    private TableView<Customer> customerTableView;
    private TableColumn<Customer, String> customerUsernameColumn;
    private TableColumn<Customer, String> customerPasswordColumn;
    private TableColumn<Customer, Integer> customerPointsColumn;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        // TODO: Initialize the primary stage and bookstore
        this.primaryStage = primaryStage;
        this.bookStore = new BookStore();
        // TODO: Set up window close handling to save data
        // TODO: Show login screen
        
        
        showLoginScreen();
        primaryStage.show();
        
    }
    @FXML
    public void CheckLogin(ActionEvent event) throws IOException{
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        handleLogin();
        
        //changeScene("Customer.FXML");
    }
    
    public void LogoutButton(ActionEvent event) throws IOException{
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        logout();
    }
    public void BookButton (ActionEvent event) throws IOException{
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        showOwnerBooksScreen();
        
    }
    
     public void CustomerButton (ActionEvent event) throws IOException{
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        showOwnerCustomersScreen();
        
    }
    
    public void AddBookButton (ActionEvent event) throws IOException{
        String addname =  AddBookName.getText(); // Access the private field
        String addprice =  AddBookPrice.getText();
        Double price = Double.valueOf(addprice);
        System.out.println(addname);
        Book bookadded = new Book(addname,price);
        BookStore.getInstance().addBook(bookadded);
        System.out.println(BookStore.getInstance().getBooks());
        BookStore.getInstance().saveData();
        bookTableView.getItems().add(bookadded);
        
        System.out.println(bookTableView);
        
    }
    
    public void AddCustomerButton (ActionEvent event) throws IOException{
        String addname =  AddBookName.getText(); // Access the private field
        String addprice =  AddBookPrice.getText();
        Double price = Double.valueOf(addprice);
        System.out.println(addname);
        Book bookadded = new Book(addname,price);
        BookStore.getInstance().addBook(bookadded);
        System.out.println(BookStore.getInstance().getBooks());
        BookStore.getInstance().saveData();
        bookTableView.getItems().add(bookadded);
        
        System.out.println(bookTableView);
        
    }
    
    
    
    public void DeleteBookButton (ActionEvent event) throws IOException{
        Book selectedBook = bookTableView.getSelectionModel().getSelectedItem(); // Get selected book
    if (selectedBook != null) {
        bookTableView.getItems().remove(selectedBook); // Remove it from TableView
        BookStore.getInstance().removeBook(selectedBook);
    } else {
        System.out.println("No book selected.");
    }
       
    }
    
    public void BackOwnerButton (ActionEvent event) throws IOException{
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
       FXMLLoader loader = new FXMLLoader(getClass().getResource("Manger.fxml"));
        Parent root = loader.load();

        // Create a new scene
        Scene newScene = new Scene(root);

        // Set the new scene on the primary stage
        primaryStage.setScene(newScene);
    
    }
    
    public void changeScene(String FXML) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML));
        Parent root = loader.load();

        // Create a new scene
        Scene newScene = new Scene(root);

        // Set the new scene on the primary stage
        primaryStage.setScene(newScene);
        
        
    }
    
    /**
     * Show the login screen
     * 
     * TODO: Implement this method to create a login form with:
     * - Username field
     * - Password field
     * - Login button
     */
    private void showLoginScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();

        // Create a new scene
        Scene newScene = new Scene(root);

        // Set the new scene on the primary stage
        primaryStage.setScene(newScene);
        
        
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
    private void handleLogin() throws IOException {
        String username = usernameField.getText(); // Access the private field
        String password = passwordField.getText();
        
        System.out.println(username);
        if (BookStore.getInstance().login(username,password) instanceof Customer){
            System.out.println("Customer Screen: "+username);
            showCustomerStartScreen(BookStore.getInstance().findCustomer("Kamsi","123"));
        } else if (BookStore.getInstance().login(username,password) instanceof Manager){
            System.out.println("Yay");
            currentUser = BookStore.getInstance().getManager();
            showOwnerStartScreen();
            
        }
        
      
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
    private void showOwnerStartScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Manger.fxml"));
        Parent root = loader.load();

        // Create a new scene
        Scene newScene = new Scene(root);

        // Set the new scene on the primary stage
        primaryStage.setScene(newScene);
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
    private void showOwnerBooksScreen() throws IOException {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BookShelf.fxml"));
        BookStore.getInstance().getBooks().addAll(booklist);
        Parent root = loader.load();
        
        BookStoreApplication controller = loader.getController();

        // Create a new scene
        Scene newScene = new Scene(root);

        // Set the new scene on the primary stage
        
        
        controller.customerUsernameColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("name"));
        controller.customerPasswordColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("Price"));
        controller.customerPointsColumn.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("Price"));
        
        //controller.bookTableView.setItems(booklist);
        controller.customerTableView.getItems().addAll(BookStore.getInstance().getCustomers());
        
        primaryStage.setScene(newScene);
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
    private void showOwnerCustomersScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerShelf.fxml"));
        Parent root = loader.load();
        
        Scene newScene = new Scene(root);
        
        BookStoreApplication controller = loader.getController();

       
        

        // Set the new scene on the primary stage
        
        
        controller.bookNameColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        controller.bookPriceColumn.setCellValueFactory(new PropertyValueFactory<Book,Double>("Price"));
        
        //controller.bookTableView.setItems(booklist);
        controller.bookTableView.getItems().addAll(BookStore.getInstance().getBooks());
        primaryStage.setScene(newScene);
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
    private void showCustomerStartScreen(Customer customer) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Customer.fxml"));
        Parent root = loader.load();

        // Create a new scene
        Scene newScene = new Scene(root);

        // Set the new scene on the primary stage
        primaryStage.setScene(newScene);
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
    
    
    private void logout() throws IOException {
        // TODO: Reset current user
        showLoginScreen();
        currentUser = null;
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
        Profile Kamsi = new Customer ("Kamsi","123",10);
        //Profile Ethan = new Manger ("Kamsi","123",10);
        BookStore.getInstance().addCustomer((Customer)Kamsi);
        //BookStore.getInstance().addManger((Customer)Kamsi);
        launch(args);
        
        
    }
} 
