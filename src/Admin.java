import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Admin {

    private ArrayList<Product> products;
    private ArrayList<Customer> customers;
    private ArrayList<Seller> sellers;
    private ArrayList<Shipping_Agent> agent;
    

    public Admin(String id , String name , String email , String username , String pass)
    {
        // super(id , name , email , username , pass);
        products = new ArrayList<>();
        customers = new ArrayList<>();
        sellers = new ArrayList<>();
        agent = new ArrayList<>();
    }

    public void Login()
    {
        String uname;
        String pass;

        Console console = System.console();

        uname = console.readLine("Username: ");
        char[] password = console.readPassword("Passowrd: ");
        pass = new String(password);

        System.out.println("Username : " + uname);
        System.out.println("Password : " + pass);

    }

    public void Logout()
    {
        System.exit(0);
    }

    public void viewProduct()
    {
        System.out.printf("%-20s %-20s %-10s %-10s %-10s %-10s \n" , "Product id" , "Product Name" , "Price" , "Quantity" , "Rating" , "Review");
        for(int i=0; i<products.size();i++)
        {
            // System.out.printf("%-20s %-20s %-10.2f %-10d %-10.1f %-10s \n" , products.get(i).getPId() , products.get(i).getPName()  , products.get(i).getPrice()  , products.get(i).getQuantity()  , products.get(i).getRating()  , products.get(i).getReview() );
        }
    }

    public void manageProducts(Scanner sc)
    {
        viewProduct();
        System.out.println("Enter id of product you want to delete: ");
        String id = sc.nextLine();

        for(int i=0;i<products.size();i++)
        {
            if(products.get(i).getPId().equals(id))
                products.remove(i);
        }
    }

    public void manageUsers(Scanner sc)
    {
        System.out.println("1) Customer /n2)Seller /n3)Shipping Agent");
        System.out.println("Select user: ");
        int opt = sc.nextInt();
        int opt2;

        switch(opt)
        {
            case 1 : System.out.println("Customer details: ");
                     //Print cust details
                     System.out.println("Enter id of customer you want to delete: ");
                     String id = sc.next();
                     for(int i=0;i<customers.size();i++)
                     {
                        if(customers.get(i).getID().equals(id))
                        {
                            customers.remove(i);
                            break;
                        }
                     }
                     break;

            case 2 : System.out.println("1) Add /n2)Delete");
                     System.out.print("Please select an option : ");
                     opt2 = sc.nextInt();
                     if(opt2==1)
                     {
                        System.out.println("Seller name : ");
                        String name = sc.nextLine();
                        System.out.println("Seller ID : ");
                        String Sid = sc.next();
                        System.out.println("Seller email : ");
                        String email = sc.next();
                        System.out.println("Seller username : ");
                        String uname = sc.next();
                        System.out.println("Seller password : ");
                        String pass = sc.next();
                        System.out.println("Store name : ");
                        String store = sc.next();

                        // sellers.add(new (name , Sid , email , uname , pass , store));
                     }

                     else if(opt2==2)
                     {
                        System.out.print("Please enter id to remove the seller: ");
                        String id2 = sc.next();
                        for(int i=0;i<customers.size();i++)
                        {
                            if(sellers.get(i).getID().equals(id2))
                            {
                                sellers.remove(i);
                                break;
                            }
                        }
                     }
                     break;

            case 3 : System.out.println("1) Add /n2)Delete");
                    System.out.print("Please select an option : ");
                    opt2 = sc.nextInt();
                    if(opt2==1)
                    {
                    System.out.println("Shipping agent name : ");
                    String name = sc.nextLine();
                    System.out.println("Shipping agent ID : ");
                    String Sid = sc.next();
                    System.out.println("Shipping agent email : ");
                    String email = sc.next();
                    System.out.println("Shipping agent username : ");
                    String uname = sc.next();
                    System.out.println("Shipping agent password : ");
                    String pass = sc.next();
                    System.out.println("Shipping agent name : ");
                    String store = sc.next();
                    String available = null;
                    String shipStat = null;

                    // agent.add(new (name , Sid , email , uname , pass , available , shipStat));
                    }

                    else if(opt2==2)
                    {
                        System.out.print("Please enter id to remove the shipping agent: ");
                        String id2 = sc.next();
                        for(int i=0;i<customers.size();i++)
                        {
                            if(agent.get(i).getID().equals(id2))
                            {
                                agent.remove(i);
                                break;
                            }
                        }
                    }
                    break;

        }
        
    }


}
