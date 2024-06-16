import java.util.*;
class Physical_Goods extends Product{
    private float weight;
    private int quantity_available;

    private static ArrayList<Physical_Goods> products = new ArrayList<>(); //to add,remove product

    public Physical_Goods(String s_id , String p_id,String p_name,float price,
                         int quantity_available,String date_added,String brand,
                         float weight){
                            super(s_id , p_id,p_name,price,date_added,brand);
                            this.weight = weight;
                            this.quantity_available = quantity_available;
    }

    public void setWeight(float weight){this.weight = weight;}
    public void setQuantity(int quantity_available){this.quantity_available = quantity_available;}

    public float getWeight(){return weight;}
    public int getQuantity(){return quantity_available;}

    public void addProduct(Physical_Goods product) {
        products.add(product);
        System.out.println("Product added successfully.");
    }

    public void updateProduct(String p_id, String p_name, float price,
                              int quantity_available,  String date_added, String brand,
                              float weight) {   //cannot send an object,with this we analyse one by one to update any changes.
        for (Physical_Goods product : products) {
            if (product.getPId().equals(p_id)) { //Means product ID should all be the correct one,so i refer product ID.If wanna change product id , i think need to do another function for that.
                product.setPName(p_name);
                product.setPrice(price);
                this.quantity_available = quantity_available;
                product.setDate(date_added);
                product.setBrand(brand);
                product.setWeight(weight);
                System.out.println("Product updated successfully.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void removeProduct(String p_id) { //usig arraylist , easier to remove product using productid
        for (Physical_Goods product : products) {
            if (product.getPId().equals(p_id)) {
                products.remove(product);
                System.out.println("Product removed successfully.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void printDetails() {
        System.out.printf("%-15s %-15s %-10.2f %-10.2f %-24d %-20s %-15s\n" , getPId() , getPName() , weight , getPrice() , quantity_available , getDate() , getBrand());
    }
}
