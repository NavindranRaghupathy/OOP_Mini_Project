import java.util.ArrayList;

class Product{
    private String s_id;
    private String p_id;
    private String p_name;
    private float price;
    private String date_added;
    private String brand;

    public Product(String s_id , String p_id,String p_name,float price,String date_added,String brand){
        this.s_id = s_id;
        this.p_id = p_id;
        this.p_name = p_name;
        this.price = price;
        this.date_added = date_added;
        this.brand = brand;
    }

    public void setSID(String s_id){this.s_id = s_id;}
    public void setPId(String p_id){this.p_id = p_id;}
    public void setPName(String p_name){this.p_name = p_name;}
    public void setPrice(float price){this.price = price;}
    public void setDate(String date_added){this.date_added = date_added;}
    public void setBrand(String brand){this.brand = brand;}

    public String getSID(){return s_id;}
    public String getPId(){return p_id;}
    public String getPName(){return p_name;}
    public float getPrice(){return price;}
    public String getDate(){return date_added;}
    public String getBrand(){return brand;}

    public void printDetails(){}
    
}
