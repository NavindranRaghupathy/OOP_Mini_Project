import java.util.*;
import java.io.*;

class Physical_Goods extends Product{
    private float weight;
    private int quantity_available;
    private static ArrayList<Review> review;


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

    public void readReview()
     {
        if(review==null){
            try{
                review = new ArrayList<>();
                Scanner in2 = new Scanner(new File("Review.csv"));
    
                in2.useDelimiter(",|\\n");
                String temp;
                String pid="" , rid="" , date="";
                float rating;
                int total;
                while(in2.hasNext())
                {
                    pid = in2.next();
                    rid  = in2.next();
                    date = in2.next();
                    temp = in2.next();
                    rating = Float.parseFloat(temp);
                    temp = in2.next().trim();
                    total = Integer.parseInt(temp);
    
                    //System.out.println(pid + " " + rid + " " + date + " " + rating + " " + total);
                    review.add(new Review(pid , rid , rating , total , date));
                }
                    in2.close();
            }catch(IOException e)
            {
                System.out.println("File not found!");
            }
        }
        
     }

    public void printDetails() {

        readReview();
            
        boolean found = false;
        for(Review r : review)
        {
            if(getPId().equals(r.getPID()))
            {
                System.out.printf("%-15s %-15s %-10.2f %-10.2f %-24d %-20s %-15s %-4.1f\n" , getPId() , getPName() , weight , getPrice() , quantity_available , getDate() , getBrand() , (r.getRate()/r.getTotalCust()));
                found = true;
                break;
            }
        }

        if(!found)
            System.out.printf("%-15s %-15s %-10.2f %-10.2f %-24d %-20s %-15s %-4s\n" , getPId() , getPName() , weight , getPrice() , quantity_available , getDate() , getBrand() , "N/A");
        
     }
        
}
