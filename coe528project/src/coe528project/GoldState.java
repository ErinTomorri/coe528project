package coe528project;

/**
 * Gold State implementation for customers with 1000+ points
 * 
 * Students need to implement this class to:
 * 1. Represent the Gold state in the State Pattern
 * 2. Handle point redemption for Gold customers
 */
public class GoldState implements CustomerState {
    
    /**
     * Return the state name
     * 
     * TODO: Return "Gold"
     */
    @Override
    public String getStateName() {
        // TODO: Return the state name
        return null;
    }
    
    /**
     * Handle points redemption for Gold customers
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