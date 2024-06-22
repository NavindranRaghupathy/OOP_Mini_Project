class Shipment {
    private String shipping_id;
    private String ship_address;
    private String custId;
    private String ProductId;
    private String ProductName;
    private int quantity;
    
    

    // Constructor to initialize Shipment object with provided details
    public Shipment(String shipping_id, String ship_address,String custId,String ProductId,String ProductName ,int quantity) {
        this.shipping_id = shipping_id;  
        this.ship_address = ship_address;
        this.custId = custId; 
        this.ProductId = ProductId; 
        this.ProductName = ProductName; 
        this.quantity = quantity;
      
      
    }

    // Setter method for shipping_id
    public void setShippingId(String shipping_id) {
        this.shipping_id = shipping_id;
    }

    // Setter method for ship_address
    public void setAddress(String ship_address) {
        this.ship_address = ship_address;
    }

    // Getter method for shipping_id
    public String getShippingId() {
        return shipping_id;
    }

    // Getter method for ship_address
    public String getAddress() {
        return ship_address;
    }
    
    public String getCustId(){return custId;}  // Getter method for custId
    public String getProductId(){return ProductId;} // Getter method for ProductId
    public String getProducName(){return ProductName;} // Getter method for ProductName
    public int getQuantity(){return quantity;} // Getter method for quantity

   

   

    // Method to print the shipping details
    public void printShipping() {
        System.out.println("Shipping ID: " + shipping_id);
        System.out.println("Address: " + ship_address);
       
        
    }
}
