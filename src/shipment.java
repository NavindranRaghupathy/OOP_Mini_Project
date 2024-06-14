public class shipment {
    private String shipping_id;
    private String ship_address;
    private String ship_method;
    private String shipping_cost;
    private String date_ship;
    private String date_deliver;

    public shipment(String shipping_id, String ship_address, String ship_method, String shipping_cost, String date_ship, String date_deliver) {
        this.shipping_id = shipping_id;
        this.ship_address = ship_address;
        this.ship_method = ship_method;
        this.shipping_cost = shipping_cost;
        this.date_ship = date_ship;
        this.date_deliver = date_deliver;
    }

    // Setters
    public void setShippingId(String shipping_id) {
        this.shipping_id = shipping_id;
    }

    public void setAddress(String ship_address) {
        this.ship_address = ship_address;
    }

    public void setMethod(String ship_method) {
        this.ship_method = ship_method;
    }

    public void setShippingCost(String shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public void setDateShip(String date_ship) {
        this.date_ship = date_ship;
    }

    public void setDateDeliver(String date_deliver) {
        this.date_deliver = date_deliver;
    }

    // Getters
    public String getShippingId() {
        return shipping_id;
    }

    public String getAddress() {
        return ship_address;
    }

    public String getMethod() {
        return ship_method;
    }

    public String getShippingCost() {
        return shipping_cost;
    }

    public String getDateShip() {
        return date_ship;
    }

    public String getDateDeliver() {
        return date_deliver;
    }

    // Methods
    public void printShipping() {
        System.out.println("Shipping ID: " + shipping_id);
        System.out.println("Address: " + ship_address);
        System.out.println("Method: " + ship_method);
        System.out.println("Shipping Cost: " + shipping_cost);
        System.out.println("Date Shipped: " + date_ship);
        System.out.println("Date Delivered: " + date_deliver);
    }
}