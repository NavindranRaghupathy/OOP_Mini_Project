import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Services extends Product{
    // private String Sdescription;
     private String duration;
     private static ArrayList<Review> review;
 
     public Services(String s_id , String p_id,String p_name,float price,
                     String date_added,String brand,
                     String duration){
         super(s_id , p_id,p_name,price,date_added,brand);
         this.duration = duration;
     }
 
     public void setDuration(String duration){this.duration = duration;}

     public String getDuration(){return duration;}

     public void readReview()
     {
        if(review==null){
            try{
                review = new ArrayList<>();
                Scanner in2 = new Scanner(new File("/workspaces/OOP_Mini_Project/src/Review.csv.csv"));
    
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
 
     public void printDetails(){
        //System.out.print(getPId());
        readReview();
            
        boolean found = false;
        for(Review r : review)
        {
            if(getPId().equals(r.getPID()))
            {
                System.out.printf("%-15s %-10s %-10.2f %-20s %-15s %-15s %-4.1f\n" , getPId() , getPName() , getPrice() , getDate() , getBrand() , duration , (r.getRate()/r.getTotalCust()));
                found = true;
                break;
            }
        }

        if(!found)
            System.out.printf("%-15s %-10s %-10.2f %-20s %-15s %-15s %-4s\n" , getPId() , getPName() , getPrice() , getDate() , getBrand() , duration , "N/A");
        
     }
 }
