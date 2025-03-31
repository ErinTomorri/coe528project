package coe528project;

/**
 * Manager class representing the bookstore owner
 */
public class Manager extends Profile {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    
    /**
     * Constructor for Manager class
     * 
     * Creates a manager with predefined admin credentials
     */
    public Manager() {
        super(ADMIN_USERNAME, ADMIN_PASSWORD);      
        setRole();
    }

    /**
     * Set the role for this profile
     */
    @Override
    public void setRole() {
        super.role = "Admin";
    }
    
    /**
     * Validate admin username and password
     * 
     * @param username The username to validate
     * @param password The password to validate
     * @return True if credentials are valid, false otherwise
     */
    public static boolean validate(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
}
