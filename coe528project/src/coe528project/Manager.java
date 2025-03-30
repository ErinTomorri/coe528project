
package coe528project;
import java.util.ArrayList;

public class Manager extends Profile{
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    public Manager() {
        super(ADMIN_USERNAME, ADMIN_PASSWORD);      
        setRole();
    }

    
    @Override
    public void setRole() {
    super.role="Admin";
    }
    public static boolean validate(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
}
