class Shipment {
    private String shipping_id;
    private String ship_address;
    private String custId;
    private String ProductId;
    private String ProductName;
    private int quantity;
    
    


    public Shipment(String shipping_id, String ship_address,String custId,String ProductId,String ProductName ,int quantity) {
        this.shipping_id = shipping_id;  
        this.ship_address = ship_address;
        this.custId = custId; 
        this.ProductId = ProductId; 
        this.ProductName = ProductName; 
        this.quantity = quantity;
      
      
    }

    // Setters
    public void setShippingId(String shipping_id) {
        this.shipping_id = shipping_id;
    }

    public void setAddress(String ship_address) {
        this.ship_address = ship_address;
    }

   
   
    // Getters
    public String getShippingId() {
        return shipping_id;
    }

    public String getAddress() {
        return ship_address;
    }
    
    public String getCustId(){return custId;}
    public String getProductId(){return ProductId;}
    public String getProducName(){return ProductName;}
    public int getQuantity(){return quantity;}

   

   

    // Methods
    public void printShipping() {
        System.out.println("Shipping ID: " + shipping_id);
        System.out.println("Address: " + ship_address);
       
        
    }
}
