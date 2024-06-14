
public class shipping_agent {
    private boolean availability;
    private String shippingStatus;


    public shipping_agent(boolean availability, String shippingStatus) {
        this.availability = availability;
        this.shippingStatus = shippingStatus;
    }

    // Setters
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    // Getters
    public boolean isAvailability() {
        return availability;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    // Methods
    public void updateShippingStatus(String newStatus) {
        this.shippingStatus = newStatus;
    }

    public void viewAssignedShipment() {
        // Implement view assigned shipment logic
    }
}
