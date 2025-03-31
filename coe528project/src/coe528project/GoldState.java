package coe528project;

/**
 * Gold State implementation for customers with 1000+ points
 * 
 * This class represents the Gold state in the State Design Pattern
 */
public class GoldState implements CustomerState {
    private Customer customer;
    
    /**
     * Constructor for GoldState
     * 
     * @param customer The customer in this state
     */
    public GoldState(Customer customer) {
        this.customer = customer;
    }
    
    /**
     * Get the customer
     * 
     * @return The customer
     */
    @Override
    public Customer getCustomer() {
        return customer;
    }
    
    /**
     * Set the customer
     * 
     * @param customer The customer
     */
    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    /**
     * Return the state name
     * 
     * @return "Gold"
     */
    @Override
    public String getStateName() {
        return "Gold";
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
        int points = customer.getPoints();
        double maxDiscount = points / 100.0; // Each 100 points = $1 discount
        double discount = Math.min(maxDiscount, cost); // Ensure cost doesn't go negative
        
        // Deduct used points from customer
        int pointsUsed = (int) (discount * 100);
        customer.setPoints(points - pointsUsed);
        
        return cost - discount;
    }
} 
