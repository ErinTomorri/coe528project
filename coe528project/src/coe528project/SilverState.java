package coe528project;

/**
 * Silver State implementation for customers with less than 1000 points
 * 
 * Students need to implement this class to:
 * 1. Represent the Silver state in the State Pattern
 * 2. Handle point redemption for Silver customers
 */
public class SilverState implements CustomerState {
    
    /**
     * Return the state name
     * 
     * TODO: Return "Silver"
     */
    @Override
    public String getStateName() {
        // TODO: Return the state name
        return null;
    }
    
    /**
     * Handle points redemption for Silver customers
     * 
     * TODO: Calculate discount based on points (1 CAD per 100 points)
     * TODO: Ensure total cost is not negative
     * TODO: Update customer points
     * TODO: Return the cost after redemption
     */
    @Override
    public double handlePointsRedemption(Customer customer, double cost) {
        // TODO: Implement points redemption logic
        return 0.0;
    }
} 