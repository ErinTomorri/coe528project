
package coe528project;
import java.util.ArrayList;

public class Manager extends Profile{
    public Manager() {
        super.setUsername("admin");
        super.setPass("admin");        
        setRole();
    }

    
    @Override
    public void setRole() {
    super.role="Manager";
    }
}