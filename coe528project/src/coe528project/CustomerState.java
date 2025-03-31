package coe528project;

/**
 * Interface for implementing State Design Pattern for customer states
 * 
 * This interface defines the behavior for different customer states (Silver, Gold)
 * and is part of the State Design Pattern implementation.
 */
public interface CustomerState {
    
    /**
     * Get the name of the state
     * 
     * @return The state name (e.g., "Silver" or "Gold")
     */
    public String getStateName();
    
    /**
     * Get the customer associated with this state
     * 
     * @return The customer
     */
    public Customer getCustomer();
    
    /**
     * Set the customer associated with this state
     * 
     * @param customer The customer
     */
    public void setCustomer(Customer customer);
    
    /**
     * Handle points redemption based on state
     * 
     * @param customer The customer redeeming points
     * @param cost The initial cost before redemption
     * @return The remaining cost after point redemption
     */
    public double handlePointsRedemption(Customer customer, double cost);
}
