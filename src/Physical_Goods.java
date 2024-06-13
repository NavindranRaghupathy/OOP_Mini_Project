import java.util.*;
class Physical_Goods extends Product{
    private float weight;
    private char size;
    private String location;
    private float shipping_cost;
    private static ArrayList<Physical_Goods> products = new ArrayList<>(); //to add,remove product

    public Physical_Goods(String p_id,String p_name,String description,float price,
                         int quantity_available,String date_added,String brand,
                         float weight , char size , String location , float shipping_cost){
                            super(p_id,p_name,description,price,quantity_available,date_added,brand);
                            this.weight = weight;
                            this.size = size;
                            this.location = location;
                            this.shipping_cost = shipping_cost;
    }

    public void setWeight(float weight){this.weight = weight;}
    public void setSize(char size){this.size = size;}
    public void setLocation(String location){this.location = location;}
    public void setShippingCost(float shipping_cost){this.shipping_cost = shipping_cost;}

    public float getWeight(){return weight;}
    public char getSize(){return size;}
    public String getLocation(){return location;}
    public float getShippingCost(){return shipping_cost;}

    public void addProduct(Physical_Goods product) {
        products.add(product);
        System.out.println("Product added successfully.");
    }

    public void updateProduct(String p_id, String p_name, String description, float price,
                              int quantity_available, float rating, String date_added, String brand,
                              float weight, char size, String location, float shipping_cost) {   //cannot send an object,with this we analyse one by one to update any changes.
        for (Physical_Goods product : products) {
            if (product.getPId().equals(p_id)) { //Means product ID should all be the correct one,so i refer product ID.If wanna change product id , i think need to do another function for that.
                product.setPName(p_name);
                product.setDescription(description);
                product.setPrice(price);
                product.setQuantity(quantity_available);
                product.setRating(rating);
                product.setDate(date_added);
                product.setBrand(brand);
                product.setWeight(weight);
                product.setSize(size);
                product.setLocation(location);
                product.setShippingCost(shipping_cost);
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
        super.printDetails();
        System.out.println("Weight: " + weight);
        System.out.println("Size: " + size);
        System.out.println("Location: " + location);
        System.out.println("Shipping Cost: RM" + shipping_cost);
    }

}