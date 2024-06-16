import java.util.ArrayList;

class Services extends Product{
    // private String Sdescription;
     private String duration;
 
     public Services(String s_id , String p_id,String p_name,float price,
                     String date_added,String brand,
                     String duration){
          super(s_id , p_id,p_name,price,date_added,brand);
         this.duration = duration;
     }
 
     public void setDuration(String duration){this.duration = duration;}

     public String getDuration(){return duration;}
 
     public void printDetails(){
        //System.out.print(getPId());
        System.out.printf("%-15s %-10s %-10.2f %-20s %-15s %-15s \n" , getPId() , getPName() , getPrice() , getDate() , getBrand() , duration);
     }
 }
