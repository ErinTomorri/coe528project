package coe528project;

/**
 * Silver State implementation for customers with less than 1000 points
 * Implements the CustomerState interface and handles point redemption
 */
public class SilverState implements CustomerState {
    
    /**
     * Return the state name "Silver"
     */
    @Override
    public String getStateName() {
        return "Silver";
    }
    
    /**
     * Handle points redemption for Silver customers.
     * Customers can redeem points at a rate of 1 CAD per 100 points.
     * Ensures that total cost is not negative and updates customer points accordingly.
     * 
     * @param customer The customer redeeming points
     * @param cost The initial cost before redemption
     * @return The final cost after redemption
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
