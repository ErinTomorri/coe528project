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
    
    // Customer table elements
    private TableView<Customer> customerTableView;
    private TableColumn<Customer, String> customerUsernameColumn;
    private TableColumn<Customer, String> customerPasswordColumn;
    private TableColumn<Customer, Integer> customerPointsColumn;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.bookStore = BookStore.getInstance();
        
        // Set up window close handling to save data
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            bookStore.saveData();
        });
        
        // Set the title
        primaryStage.setTitle("Book Store Application");
        
        // Show login screen
        showLoginScreen();
        
        // Display the stage
        primaryStage.show();
    }
    
    /**
     * Show the login screen
     */
    private void showLoginScreen() {
        // Create login form elements
        Label titleLabel = new Label("Book Store Login");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        
        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();
        usernameField.setPromptText("Enter username");
        
        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> handleLogin());
        
        // Create the layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(loginButton, 1, 3);
        
        // Set the scene
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
    }
    
    /**
     * Handle login button click
     */
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        Profile user = bookStore.login(username, password);
        
        if (user != null) {
            currentUser = user;
            if (user.isAdmin()) {
                showOwnerStartScreen();
            } else if (user.isCustomer()) {
                showCustomerStartScreen((Customer) user);
            }
        } else {
            showAlert("Login Failed", "Invalid username or password");
        }
    }
    
    /**
     * Show the owner start screen
     */
    private void showOwnerStartScreen() {
        // Create buttons with event handlers
        Label titleLabel = new Label("Owner Menu");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        
        Button booksButton = new Button("Books");
        booksButton.setOnAction(e -> showOwnerBooksScreen());
        
        Button customersButton = new Button("Customers");
        customersButton.setOnAction(e -> showOwnerCustomersScreen());
        
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> logout());
        
        // Create layout
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.getChildren().addAll(titleLabel, booksButton, customersButton, logoutButton);
        
        // Set the scene
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
    }
    
    /**
     * Show the owner books screen
     */
    private void showOwnerBooksScreen() {
        // Create top table of books
        bookTableView = new TableView<>();
        
        bookNameColumn = new TableColumn<>("Book Name");
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        bookPriceColumn = new TableColumn<>("Book Price");
        bookPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        bookTableView.getColumns().addAll(bookNameColumn, bookPriceColumn);
        bookTableView.setItems(FXCollections.observableArrayList(bookStore.getBooks()));
        
        // Create middle form for adding books
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        
        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField();
        
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                
                Book book = new Book(name, price);
                bookStore.addBook(book);
                bookTableView.getItems().add(book);
                
                nameField.clear();
                priceField.clear();
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid price");
            }
        });
        
        // Create bottom buttons
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Book selectedBook = bookTableView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                bookStore.removeBook(selectedBook);
                bookTableView.getItems().remove(selectedBook);
            } else {
                showAlert("No Selection", "Please select a book to delete");
            }
        });
        
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showOwnerStartScreen());
        
        // Create the layout
        BorderPane borderPane = new BorderPane();
        
        // Top part - table
        VBox topBox = new VBox(10);
        topBox.setPadding(new Insets(10));
        topBox.getChildren().add(bookTableView);
        borderPane.setTop(topBox);
        
        // Middle part - add form
        GridPane middleGrid = new GridPane();
        middleGrid.setHgap(10);
        middleGrid.setVgap(10);
        middleGrid.setPadding(new Insets(10));
        
        middleGrid.add(nameLabel, 0, 0);
        middleGrid.add(nameField, 1, 0);
        middleGrid.add(priceLabel, 0, 1);
        middleGrid.add(priceField, 1, 1);
        middleGrid.add(addButton, 2, 0, 1, 2);
        
        borderPane.setCenter(middleGrid);
        
        // Bottom part - action buttons
        HBox bottomBox = new HBox(10);
        bottomBox.setPadding(new Insets(10));
        bottomBox.getChildren().addAll(deleteButton, backButton);
        borderPane.setBottom(bottomBox);
        
        // Set the scene
        Scene scene = new Scene(borderPane, 500, 400);
        primaryStage.setScene(scene);
    }
    
    /**
     * Show the owner customers screen
     */
    private void showOwnerCustomersScreen() {
        // Create top table of customers
        customerTableView = new TableView<>();
        
        customerUsernameColumn = new TableColumn<>("Username");
        customerUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        
        customerPasswordColumn = new TableColumn<>("Password");
        customerPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        customerPointsColumn = new TableColumn<>("Points");
        customerPointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        
        customerTableView.getColumns().addAll(customerUsernameColumn, customerPasswordColumn, customerPointsColumn);
        customerTableView.setItems(FXCollections.observableArrayList(bookStore.getCustomers()));
        
        // Create middle form for adding customers
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
            if (!username.isEmpty() && !password.isEmpty()) {
                Customer customer = new Customer(username, password);
                bookStore.addCustomer(customer);
                customerTableView.getItems().add(customer);
                
                usernameField.clear();
                passwordField.clear();
            } else {
                showAlert("Invalid Input", "Username and password cannot be empty");
            }
        });
        
        // Create bottom buttons
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                bookStore.removeCustomer(selectedCustomer);
                customerTableView.getItems().remove(selectedCustomer);
            } else {
                showAlert("No Selection", "Please select a customer to delete");
            }
        });
        
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showOwnerStartScreen());
        
        // Create the layout
        BorderPane borderPane = new BorderPane();
        
        // Top part - table
        VBox topBox = new VBox(10);
        topBox.setPadding(new Insets(10));
        topBox.getChildren().add(customerTableView);
        borderPane.setTop(topBox);
        
        // Middle part - add form
        GridPane middleGrid = new GridPane();
        middleGrid.setHgap(10);
        middleGrid.setVgap(10);
        middleGrid.setPadding(new Insets(10));
        
        middleGrid.add(usernameLabel, 0, 0);
        middleGrid.add(usernameField, 1, 0);
        middleGrid.add(passwordLabel, 0, 1);
        middleGrid.add(passwordField, 1, 1);
        middleGrid.add(addButton, 2, 0, 1, 2);
        
        borderPane.setCenter(middleGrid);
        
        // Bottom part - action buttons
        HBox bottomBox = new HBox(10);
        bottomBox.setPadding(new Insets(10));
        bottomBox.getChildren().addAll(deleteButton, backButton);
        borderPane.setBottom(bottomBox);
        
        // Set the scene
        Scene scene = new Scene(borderPane, 500, 400);
        primaryStage.setScene(scene);
    }
    
    /**
     * Show the customer start screen
     */
    private void showCustomerStartScreen(Customer customer) {
        // Create welcome message
        Label welcomeLabel = new Label("Welcome " + customer.getUsername() + 
                ". You have " + customer.getPoints() + " points. Your status is " + 
                customer.getStatus());
        welcomeLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        
        // Create table of books with checkboxes
        TableView<BookWithCheckbox> bookTable = new TableView<>();
        
        TableColumn<BookWithCheckbox, String> nameColumn = new TableColumn<>("Book Name");
        nameColumn.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getBook().getName()));
        
        TableColumn<BookWithCheckbox, Double> priceColumn = new TableColumn<>("Book Price");
        priceColumn.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getBook().getPrice()).asObject());
        
        TableColumn<BookWithCheckbox, CheckBox> selectColumn = new TableColumn<>("Select");
        selectColumn.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getCheckBox()));
        
        // Add columns to table
        bookTable.getColumns().addAll(nameColumn, priceColumn, selectColumn);
        
        // Create checkboxes for each book and add to table
        ObservableList<BookWithCheckbox> booksWithCheckboxes = FXCollections.observableArrayList();
        for (Book book : bookStore.getBooks()) {
            CheckBox checkBox = new CheckBox();
            booksWithCheckboxes.add(new BookWithCheckbox(book, checkBox));
        }
        
        bookTable.setItems(booksWithCheckboxes);
        
        // Create bottom buttons
        Button buyButton = new Button("Buy");
        buyButton.setOnAction(e -> {
            List<Book> selectedBooks = new ArrayList<>();
            for (BookWithCheckbox bookWithCheckbox : booksWithCheckboxes) {
                if (bookWithCheckbox.getCheckBox().isSelected()) {
                    selectedBooks.add(bookWithCheckbox.getBook());
                }
            }
            
            if (!selectedBooks.isEmpty()) {
                double totalCost = bookStore.processPurchase(customer, selectedBooks, false);
                showCustomerCostScreen(customer, totalCost);
            } else {
                showAlert("No Selection", "Please select at least one book to buy");
            }
        });
        
        Button redeemButton = new Button("Redeem Points and Buy");
        redeemButton.setOnAction(e -> {
            List<Book> selectedBooks = new ArrayList<>();
            for (BookWithCheckbox bookWithCheckbox : booksWithCheckboxes) {
                if (bookWithCheckbox.getCheckBox().isSelected()) {
                    selectedBooks.add(bookWithCheckbox.getBook());
                }
            }
            
            if (!selectedBooks.isEmpty()) {
                double totalCost = bookStore.processPurchase(customer, selectedBooks, true);
                showCustomerCostScreen(customer, totalCost);
            } else {
                showAlert("No Selection", "Please select at least one book to buy");
            }
        });
        
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> logout());
        
        // Create the layout
        BorderPane borderPane = new BorderPane();
        
        // Top part - welcome message
        VBox topBox = new VBox(10);
        topBox.setPadding(new Insets(10));
        topBox.getChildren().add(welcomeLabel);
        borderPane.setTop(topBox);
        
        // Middle part - book table
        VBox middleBox = new VBox(10);
        middleBox.setPadding(new Insets(10));
        middleBox.getChildren().add(bookTable);
        borderPane.setCenter(middleBox);
        
        // Bottom part - action buttons
        HBox bottomBox = new HBox(10);
        bottomBox.setPadding(new Insets(10));
        bottomBox.getChildren().addAll(buyButton, redeemButton, logoutButton);
        borderPane.setBottom(bottomBox);
        
        // Set the scene
        Scene scene = new Scene(borderPane, 600, 400);
        primaryStage.setScene(scene);
    }
    
    /**
     * Show the customer cost screen
     */
    private void showCustomerCostScreen(Customer customer, double totalCost) {
        // Create UI components
        Label costLabel = new Label("Total Cost: $" + String.format("%.2f", totalCost));
        costLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        Label pointsLabel = new Label("Points: " + customer.getPoints() + ", Status: " + customer.getStatus());
        pointsLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> logout());
        
        // Create the layout
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.getChildren().addAll(costLabel, pointsLabel, logoutButton);
        
        // Set the scene
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
    }
    
    /**
     * Log out the current user
     */
    private void logout() {
        currentUser = null;
        showLoginScreen();
    }
    
    /**
     * Show an alert dialog
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Helper class to associate a book with a checkbox in the customer table
     */
    private class BookWithCheckbox {
        private Book book;
        private CheckBox checkBox;
        
        public BookWithCheckbox(Book book, CheckBox checkBox) {
            this.book = book;
            this.checkBox = checkBox;
        }
        
        public Book getBook() {
            return book;
        }
        
        public CheckBox getCheckBox() {
            return checkBox;
        }
    }
    
    /**
     * Main method
     */
    public static void main(String[] args) {
        launch(args);
    }
} 
