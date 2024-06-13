class Product{
    private String p_id;
    private String p_name;
    private String description;
    private float price;
    private int quantity_available;
    private float rating; //not sure if its like this or the rating class is called here or not
    private String date_added;
    private String brand;

    public Product(String p_id,String p_name,String description,float price,
                   int quantity_available,String date_added,String brand){
        this.p_id = p_id;
        this.p_name = p_name;
        this.description = description;
        this.price = price;
        this.quantity_available = quantity_available;
        this.date_added = date_added;
        this.brand = brand;
    }

    public void setPId(String p_id){this.p_id = p_id;}
    public void setPName(String p_name){this.p_name = p_name;}
    public void setPrice(float price){this.price = price;}
    public void setDescription(String description){this.description = description;}
    public void setQuantity(int quantity_available){this.quantity_available = quantity_available;}
    public void setRating(float rating){this.rating = rating;}
    public void setDate(String date_added){this.date_added = date_added;}
    public void setBrand(String brand){this.brand = brand;}

    public String getPId(){return p_id;}
    public String getPName(){return p_name;}
    public float getPrice(){return price;}
    public int getQuantity(){return quantity_available;}
    public float getRating(){return rating;}
    public String getDate(){return date_added;}
    public String getBrand(){return brand;}
    //public String getDescription(){return description;}

    public void printDetails() {
        System.out.println("Product ID: " + p_id);
        System.out.println("Name: " + p_name);
        System.out.println("Description: " + description);
        System.out.println("Price: RM" + price);
        System.out.println("Quantity Available: " + quantity_available);
        System.out.println("Rating: " + rating);
        System.out.println("Date Added: " + date_added);
        System.out.println("Brand: " + brand);
    }
}
