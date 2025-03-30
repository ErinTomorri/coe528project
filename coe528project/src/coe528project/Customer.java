
package coe528project;

public class Customer extends Profile {
    private int points;
    private CustomerState state;

    public Customer(String username, String password) {
        super(username, password);
        this.points = 0;
        updateState();
    }

    public Customer(String username, String password, int points) {
        super(username, password);
        setRole();
        this.points = points;
        updateState();
    }
    
    /**
     * Get the customer's points
     * @return The customer's points
     */
    public int getPoints() {
        return points;
    }
    
    /**
     * Set the customer's points
     * @param points The new points value
     */
    public void setPoints(int points) {
        this.points = points;
        updateState();
    }
    
    /**
     * Add points to the customer's account
     * @param pointsToAdd The points to add
     */
    public void addPoints(int pointsToAdd) {
        this.points += pointsToAdd;
        updateState();
    }
    
    /**
     * Get the customer's state
     * @return The customer's state
     */
    public CustomerState getState() {
        return state;
    }
    
    /**
     * Set the customer's state
     * @param state The new state
     */
    public void setState(CustomerState state) {
        this.state = state;
    }
    
    /**
     * Get the customer's status name
     * @return The status name (Silver or Gold)
     */
    public String getStatus() {
        return state.getStateName();
    }
    
    /**
     * Update the customer's state based on points
     */
    private void updateState() {
        if (points < 1000) {
            state = new SilverState(this);
        } else {
            state = new GoldState(this);
        }
    }
    
    /**
     * Redeem points for a purchase
     * @param cost The cost of the purchase
     * @return The remaining cost after points redemption
     */
    public double redeemPoints(double cost) {
        int pointsToRedeem = Math.min(points, (int)(cost * 100));
        double discount = pointsToRedeem / 100.0;
        
        points -= pointsToRedeem;
        updateState();
        
        return Math.max(0, cost - discount);
    }

    @Override
    public void setRole() {
        super.role = "Customer";
    }
    @Override
    public String toString() {
        return super.toString() + "," + points;
    }
}
