package coe528project;

/**
 * Interface for implementing State Design Pattern for customer states
 * 
 * Students need to implement this interface and concrete state classes to:
 * 1. Define different states (Silver, Gold)
 * 2. Handle point redemption logic for each state
 */
public interface CustomerState {
    protected Customer customer
    /**
     * Get the name of the state
     * 
     * TODO: Return the state name (e.g., "Silver" or "Gold")
     */
     public CustomerState(Customer customer) {
        this.customer = customer;
    }
    public abstract String getStateName();
}
    /**
     * Handle point redemption based on state (done in bookstore.java and customer.java)
     * 
     * TODO: Implement point redemption logic
     * TODO: For every 100 points, 1 CAD is deducted from the cost 
     * TODO: Update customer points
     * TODO: Return the remaining cost after redemption
     */
