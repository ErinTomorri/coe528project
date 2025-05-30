package coe528project;

/**
 * Base class for user profiles
 */
public class Profile {
    protected String username;
    protected String password;
    protected String role;

    public Profile() {
        this.username = "";
        this.password = "";
        this.role = "";
    }
    
    public Profile(String username, String password) {
        this.username = username;
        this.password = password;
        setRole();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() { 
        return password;
    }

    public String getRole() {   
        return role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole() {
        this.role = "User";    
    }
    
    public boolean isAdmin() {
        return "Admin".equals(role);
    }
    
    public boolean isCustomer() {
        return "Customer".equals(role);
    }
    
    @Override
    public String toString() {
        return username + "," + password;
    }
}

