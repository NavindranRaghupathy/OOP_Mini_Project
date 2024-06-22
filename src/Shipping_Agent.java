import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Shipping_Agent extends User {
    private boolean availability;
    private ArrayList<Shipment> shipment;
    private ArrayList<Shipment> AgentShipment;
    ArrayList<String>shippingStatus;
    private boolean st = false;
    private static boolean st2 = false;
    private static boolean st3 = false;




    public Shipping_Agent(String id, String name, String email, String username, String pass , boolean availability) {
        super(id, name, email, username, pass);
        this.availability = availability;
        shippingStatus = new ArrayList<String>();
        AgentShipment = new ArrayList<>();
        shipment = new ArrayList<>();
    }

    // Setters
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }


    // Getters
    public boolean isAvailability() {
        return availability;
    }



    public void viewProfile()
    {
        System.out.println("\nName : " + getName());
        System.out.println("Email : " + getEmail());
        System.out.println("Availability : " + availability);
        
    }

    public void updateProfile(Scanner sc , ArrayList<User> user)
    {
        viewProfile();
        String name , uname , pass;
        boolean availability1 = false;
        int opt;
        do{
            System.out.println("\n1) Name \n2) Username \n3) Password \n4) Availability\n");
            System.out.print("Please select an option : ");
            opt = sc.nextInt();
            sc.nextLine();


            switch(opt)
            {
                case 1 : System.out.print("\nName : ");
                        name = sc.nextLine();
                        setName(name);
                        break;
                
                case 2 : System.out.print("\nUsername : ");
                        uname = sc.nextLine();
                        setUsername(uname);
                        break;
                case 3 : System.out.print("\nPassword : ");
                        pass = sc.nextLine();
                        setPass(pass);
                        break;
                case 4 : System.out.print("\nAvailability: ");
                        System.out.println("\n1)Available \n2)Not Available");
                        System.out.print("Choose your availability : ");
                        int opt2 = sc.nextInt();
                        switch(opt2){
                            case 1: availability1 = true; break;
                            case 2: availability1 = false; break;
                            default: break;
                        }
                        setAvailability(availability1);
                        break;

                default : System.out.println("Invalid option selected!");
                        break;
            }
        
        }while(opt<1 | opt>4);

        for (int i = 0; i < user.size(); i++) 
        {
            if (getID().equals(user.get(i).getID()))
             {
                user.set(i, this);
                break;
             }
        }
    }

    public void Logout(ArrayList<User> user){

        try (PrintWriter writer = new PrintWriter(new FileWriter("Shipping_Agent.csv"))) {
            for (User u : user) {
                    writer.print(u.getID() + ",");
                    writer.print(u.getName() + ",");
                    writer.print(u.getEmail() + ",");
                    writer.print(u.getUsername() + ",");
                    writer.print(u.getPass() + ",");
                    writer.print(u.isAvailability());
                    writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
        
    }

    

    public void ReadShipping()
    { 
        if(!st2){
        try{
            Scanner in2 = new Scanner(new File("/workspaces/OOP_Mini_Project/src/shipping.csv"));
        in2.useDelimiter(",|\\n");
        String temp;
        String cid="" , pid="" , pname="" , sId="" , address = "";
        int qty = 0;
        while(in2.hasNext())
        {
            sId = in2.next().trim();
            cid = in2.next().trim();
            pid = in2.next().trim();
            pname = in2.next().trim();
            address = in2.next();
            temp = in2.next().trim();
            qty = Integer.parseInt(temp);
            
            //System.out.println(sid + " " + pid + " " + pname + " " + price + " " + quantity + " " + date + " " + brand + " " + weight);
            shipment.add(new Shipment(sId , address,cid,pid,pname,qty));
        }
            in2.close();
    }catch(IOException e){
        System.out.println("File not found");
    }
    st2 = true;
}
}

public void ViewFile(){
    System.out.printf("\n%-4s %-4s %-4s %-15s %-10s %-30s\n","SID","CID","PID","PName","Quantity","Address");
    for(Shipment s: shipment){
        System.out.printf("%-4s %-4s %-4s %-15s %-10d %-30s\n",s.getShippingId(),s.getCustId(),s.getProductId(),s.getProducName(),s.getQuantity(),s.getAddress());
    }

}



public void AnotherFile(){
    String outputFileName = ( this.getName() + "_shipping.csv");

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {

            for (int i=0;i<AgentShipment.size();i++) {
                writer.print(AgentShipment.get(i).getShippingId() + "," );
                writer.print(AgentShipment.get(i).getCustId() + ",");
                writer.print(AgentShipment.get(i).getProductId() + ",");
                writer.print(AgentShipment.get(i).getProducName() + ",");
                writer.print(AgentShipment.get(i).getQuantity() + ",");
                writer.print(AgentShipment.get(i).getAddress() + ",");
                writer.print(shippingStatus.get(i));
                writer.println();
                
            }
            System.out.println("\nShipping data copied to " + outputFileName);
            
            
        } catch (IOException e) {
            System.out.println("An error occurred while copying shipping data.");
        }
    }

public void AgentFile(Scanner kv){
    
    String id = "";
    try{
        st= true;
        String outputFileName = ( this.getName() + "_shipping.csv");
        Scanner in2 = new Scanner(new File(outputFileName));

    in2.useDelimiter(",|\\n");
    String temp;
    String cid , pid, pname, sId, address;
    int qty = 0;
    
    if(!st3){
    while(in2.hasNext())
    {
        sId = in2.next().trim();
        cid = in2.next().trim();
        pid = in2.next().trim();
        pname = in2.next().trim();
        temp = in2.next().trim();
        qty = Integer.parseInt(temp);
        address = in2.next();
        address.replaceAll("\n", "");
        temp = in2.next().trim();
        shippingStatus.add(temp);
        
        //System.out.println(sId + " " + pid + " " + pname + " " + qty + " " + address + " " + temp);
        AgentShipment.add(new Shipment(sId , address,cid,pid,pname,qty));
    }
    st3 = true;
}
        in2.close();
        //ViewOrderForDelivery();

}catch(IOException e){
    System.out.println("\nNo orders!");
}

    System.out.print("Please enter Shipping Id : ");
    id = kv.next();
    id = id.toUpperCase();
    for (Shipment s : shipment) {

    if(id.equals(s.getShippingId())){
        AgentShipment.add(new Shipment(s.getShippingId() , s.getAddress() , s.getCustId() , s.getProductId() , s.getProducName() , s.getQuantity()));
        shippingStatus.add(null);
        // writer.println();
    }
}

    for(int i=0;i<shipment.size();i++)
    {
        if(id.equals(shipment.get(i).getShippingId()))
        {
            shipment.remove(i);
        }
    }

    ViewFile();
    writeShipping();
    AnotherFile();
}

public void writeShipping(){
    try (PrintWriter writer = new PrintWriter(new FileWriter("shipping.csv"))) {
        for (Shipment s: shipment) {
           
                writer.print(s.getShippingId() + ",");
                writer.print(s.getCustId() + ",");
                writer.print(s.getProductId() + ",");
                writer.print(s.getProducName()+ ",");
                writer.print(s.getAddress() + ",");
                writer.print(s.getQuantity());
                writer.println();
            
        }
    } catch (IOException e) {
        System.out.println("Error : File not found");
    }
}

public void UpdateOrder(Scanner kv){
    System.out.printf("\n%-4s %-4s %-4s %-15s %-10s %-35s %-15s\n","SID","CID","PID","PName","Quantity","Address","Shipping Status");
    for(int i=0;i<AgentShipment.size();i++){
        System.out.printf("%-4s %-4s %-4s %-15s %-10d %-35s %-15s \n",AgentShipment.get(i).getShippingId(),AgentShipment.get(i).getCustId(),AgentShipment.get(i).getProductId(),AgentShipment.get(i).getProducName(),AgentShipment.get(i).getQuantity(),AgentShipment.get(i).getAddress(),shippingStatus.get(i));
    }
    System.out.print("\nChoose the Shipping ID: ");
    String sid = kv.next();
    sid = sid.toUpperCase();
    int opt;
    do{
        System.out.print("\n1)Delivered \n2)Shipped \n3)Pending");
        System.out.print("\nSelect shipping status: ");
        opt = kv.nextInt();
    }while(opt<1 || opt>3);
    String status = "";
    switch(opt){
        case 1: status = "Delivered";break;
        case 2: status = "Shipped";break;
        case 3: status = "Pending";break;
        default :System.out.print("Invalid choice");
                 break;
    }
    for(int i=0;i<AgentShipment.size();i++){
        if(sid.equals(AgentShipment.get(i).getShippingId())){
           shippingStatus.set(i,status);
        }
    }
    AnotherFile();
}

public void ViewOrderForDelivery(){

    System.out.printf("\n%-4s %-4s %-4s %-15s %-10s %-35s %-15s\n","SID","CID","PID","PName","Quantity","Address","Shipping Status");
    for(int i=0;i<AgentShipment.size();i++){
        System.out.printf("%-4s %-4s %-4s %-15s %-10d %-35s %-15s \n",AgentShipment.get(i).getShippingId(),AgentShipment.get(i).getCustId(),AgentShipment.get(i).getProductId(),AgentShipment.get(i).getProducName(),AgentShipment.get(i).getQuantity(),AgentShipment.get(i).getAddress(),shippingStatus.get(i));
    }
    }

    public void UpdateStatus(){
        ViewOrderForDelivery();
        
    }
}


