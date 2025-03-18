
package coe528project;

public abstract class Profile {
    protected String username;
    protected String password;
    protected String role;

    public void setPass(String pass) {
        this.password = pass;
    }

    public String getPass() { 
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

    public abstract void setRole();    
}


