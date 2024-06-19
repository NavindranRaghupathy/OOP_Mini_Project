import java.util.*;
import java.io.*;

public class Commerce {

    static void menu()
    {
        System.out.println("Welcome to our E-commerce website !");
        System.out.println("\n1) Admin \n2) Customer \n3) Seller \n4) Shipping Agent");
        System.out.print("Please selsect an option : ");
    }

    static void SellerMenu()
    {
        System.out.println("\n1) View Products \n2) Add Products \n3) Update Products \n4) Remove Products \n5) Update Profile \n6) Logout");
        System.out.print("Please selsect an option : ");
    }

    static void CustMenu()
    {
        System.out.println("\n1) View Order \n2) Add Product to cart \n3) Remove Product from cart \n4) Make Payment \n5) Update Profile \n6) Logout");
        System.out.print("Please selsect an option : ");
    }

    static void CustMenu2()
    {
        System.out.println("\n1) Login \n2) Sign Up ");
        System.out.print("Please selsect an option : ");
    }

    static void ShippingAgentMenu(){
        System.out.println("\n1) Order to be delivered \n2) Update order status \n3) View order \n4)Update profile");
        System.out.print("Please selsect an option : ");
    }

    static ArrayList<Physical_Goods> readPhysical(Scanner in)
    {
        ArrayList<Physical_Goods> p = new ArrayList<>();
        in.useDelimiter(",|\\n");
        String temp;
        String sid="" , pid="" , pname="" , date="" , brand;
        float price=0,weight;
        int quantity=0;
        while(in.hasNext())
        {
            sid = in.next();
            pid = in.next();
            pname = in.next();
            temp = in.next();
            price = Float.parseFloat(temp);
            temp = in.next();
            quantity = Integer.parseInt(temp);
            date = in.next();
            brand = in.next();
            temp = in.next();
            weight = Float.parseFloat(temp);

            //System.out.println(sid + " " + pid + " " + pname + " " + price + " " + quantity + " " + date + " " + brand + " " + weight);
            p.add(new Physical_Goods(sid , pid, pname, price, quantity , date , brand , weight));
        }
            in.close();
            return p;
    }

    static ArrayList<Services> readServices(Scanner in)
    {
        ArrayList<Services> s = new ArrayList<>();
        in.useDelimiter(",|\\n");
        String temp;
        String sid="" , pid="" , pname="" , date="" , brand="", duration="";
        float price=0;
        while(in.hasNext())
        {
            sid = in.next();
            pid = in.next();
            pname = in.next();
            date = in.next();
            brand = in.next();
            duration = in.next();
            temp = in.next();
            price = Float.parseFloat(temp);

            //System.out.println(sid + " " + pid + " " + pname + " " + price + " " + " " + date + " " + brand + " " + duration);
            s.add(new Services(sid , pid, pname, price , date , brand , duration));
        }
            in.close();
            return s;
    }

    static ArrayList<User> readCustomer(Scanner in)
    {
        in.useDelimiter(",|\\n");
        String name="" , id="" , email="" , uname="" , pass="" , address="" , phone="";
        //Customer c[] = new Customer[10];
        ArrayList<User> c = new ArrayList<>();
        //int i=0;
        while(in.hasNext())
        {
            name = in.next();
            id = in.next();
            email = in.next();
            uname = in.next();
            pass = in.next();
            address = in.next();
            phone = in.nextLine();

            phone = phone.substring(1);
            //System.out.println(name + " " + id + " " + email + " " + uname + " " + pass + " " + address + " " + phone);
            // c[i] = new Customer(id , name , email , uname , pass , address , phone);
            // i++;
            c.add(new Customer(id, name, email, uname, pass, address, phone));
        }
            in.close();
            return c;
    }

    static Customer customerLoginCheck (ArrayList<User> cust){

            String uname2;
            String pass2;
            System.out.println();

            Console console = System.console();

            uname2 = console.readLine("Username: ");
            char[] password = console.readPassword("Passowrd: ");
            pass2 = new String(password);

            for(User cu : cust)
            {
                if(cu.getUsername().equals(uname2) && cu.getPass().equals(pass2))
                {
                    System.out.println("\nWelcome, " + cu.getName());
                    Customer c = new Customer(cu.getID() , cu.getName() , cu.getEmail() , cu.getUsername() , cu.getPass() , cu.getAddress() , cu.getPhoneNo());
                    return c;
                }
             }
             return new Customer("" , "" , "" , "" , "" ,"" , "");
    }

    static ArrayList<User> readSeller(Scanner in)
    {
        in.useDelimiter(",|\\n");
        String name="" , id="" , email="" , uname="" , pass="" , store="";
        ArrayList<User> s = new ArrayList<>();

        while(in.hasNext())
        {
            id = in.next();
            name = in.next();
            email = in.next();
            uname = in.next();
            pass = in.next();
            store = in.nextLine();
            store = store.substring(1);
            //System.out.println(name + " " + id + " " + email + " " + uname + " " + pass + " " + store);
            s.add(new Seller(id, name, email, uname, pass, store));
        }
            in.close();
            return s;
    }

    static Seller sellerLoginCheck (ArrayList<User> sell)
    {
            String uname2;
            String pass2;

            Console console = System.console();

            System.out.println();

            uname2 = console.readLine("Username: ");
            char[] password = console.readPassword("Passowrd: ");
            pass2 = new String(password);

            for(User s : sell)
            {
                if(s.getUsername().equals(uname2) && s.getPass().equals(pass2))
                {
                    System.out.println("\nWelcome, " + s.getName());
                    Seller se = new Seller(s.getID() , s.getName() , s.getEmail() , s.getUsername() , s.getPass() , s.getStoreName());
                    return se;
                }
             }
             return new Seller("" , "" , "" , "" , "" ,"");
    }

    static ArrayList<User> readShippingAgent(Scanner in)
    {
        in.useDelimiter(",|\\n");
        String name="" , id="" , email="" , uname="" , pass="" , ShippingStatus="";
        boolean availability = false;
        ArrayList<User> s = new ArrayList<>();

        while(in.hasNext())
        {
            id = in.next();
            name = in.next();
            email = in.next();
            uname = in.next();
            pass = in.next();
            availability = Boolean.parseBoolean(in.next());
        ShippingStatus = in.next();
            //System.out.println(name + " " + id + " " + email + " " + uname + " " + pass + " " + store);
            s.add(new Shipping_Agent(id, name, email, uname, pass, availability,ShippingStatus));
        }
            in.close();
            return s;
    }

    static Shipping_Agent ShippingAgentLoginCheck (ArrayList<User> shippingAgent)
    {
            String uname2;
            String pass2;

            Console console = System.console();

            uname2 = console.readLine("Username: ");
            char[] password = console.readPassword("Password: ");
            pass2 = new String(password);

            for(User sa : shippingAgent)
            {
                if(sa.getUsername().equals(uname2) && sa.getPass().equals(pass2))
                {
                    System.out.println("Welcome, " + sa.getName());
                    Shipping_Agent SA = new Shipping_Agent(sa.getID() , sa.getName() , sa.getEmail() , sa.getUsername() , sa.getPass() , sa.isAvailability() , sa.getShippingStatus() );
                    return SA;
                }
             }
             return new Shipping_Agent("" , "" , "" , "" , "" ,false,"");
    }


    public static void main(String[] args)throws IOException
    {
        Scanner sc = new Scanner(System.in);
        Scanner in = new Scanner (new File("customer.csv"));
        ArrayList<User> customers = readCustomer(in);

        in = new Scanner (new File("Seller.csv"));
        ArrayList<User> sellers = readSeller(in);

        in = new Scanner(new File("Shipping_Agent.csv"));
        ArrayList<User> shippingAgent = readShippingAgent(in);

       
        // User agents[] = new Shipping_Agent[10];

        User current = null;
        menu();
        int opt1 = sc.nextInt();
        sc.nextLine();
        char cont = 'y';

        switch(opt1)
        {
            case 1 : break;

            case 2 : CustMenu2();
                     int custopt = sc.nextInt();
                     sc.nextLine();

                     if(custopt == 1)
                     {  
                        do{
                            current = customerLoginCheck(customers);
                        }while(current.getName().equals(""));
                     }
                     
                     else if(custopt==2)
                     {
                        current = new Customer();
                        current.SignUp(customers , sc);
                        // in = new Scanner (new File("customer.csv"));
                        // customers = readCustomer(in);
                        current = customerLoginCheck(customers);
                     }
                
                        in = new Scanner (new File("physical_product.csv"));
                        ArrayList<Physical_Goods> physical = readPhysical(in);
                        in = new Scanner (new File("Service_Product.csv"));
                        ArrayList<Services> service = readServices(in);
                        current.ReadCustPhysical(physical);
                        current.ReadCustService(service);
                        int opt2;

                        do{
                        CustMenu();
                        opt2 = sc.nextInt();
                        sc.nextLine();
                        switch(opt2)
                        {
                            case 1 : current.viewOrder();
                                    //  System.out.print("\nDo you want to continue(y/n) : ");
                                    //  cont = sc.next().charAt(0);
                                    break;
                            case 2 : current.buyProducts(sc);
                                    // System.out.print("\nDo you want to continue(y/n) : ");
                                    // cont = sc.next().charAt(0);
                                    break;
                            case 3 : current.deleteProduct(sc);
                                    break;
                            case 4 : in = new Scanner (new File("Review.csv"));
                            		 current.checkout(in , sc);
                                    //  System.out.print("\nDo you want to continue(y/n) : ");
                                    //  cont = sc.next().charAt(0);
                                     break;
                            case 5 : current.updateProfile(sc , customers);
                                    //  System.out.print("\nDo you want to continue(y/n) : ");
                                    //  cont = sc.next().charAt(0);
                                     break;
                            case 6 : current.Logout(customers);
                                    //  cont = 'n';
                                     break;
                            default : System.out.println("\nInvalid option choosed !\n");
                                    break;
                        }

                     }while(opt2>=1 && opt2<=6);
                     break;

            case 3 : 
                    do{
                        current = sellerLoginCheck(sellers);
                        if(current.getName().equals("")){
                            System.out.println("Incorrect Username or password");
                            //System.exit(0);
                        }
                    }while(current.getName().equals(""));
                     in = new Scanner (new File("physical_product.csv"));
                     current.ReadPhysicalProduct(in);
                     in = new Scanner (new File("Service_Product.csv"));
                     current.ReadServiceProduct(in);
                     do{
                     SellerMenu();
                     opt2 = sc.nextInt();
                     sc.nextLine();
                     switch(opt2)
                     {
                        case 1 : current.viewProduct();
                                System.out.print("\nDo you want to continue(y/n) : ");
                                cont = sc.next().charAt(0);
                                 break;
                        case 2 : current.addProduct(sc);
                                System.out.print("\nDo you want to continue(y/n) : ");
                                cont = sc.next().charAt(0);
                                 break;
                        case 3 : current.updateProduct(sc);
                                System.out.print("\nDo you want to continue(y/n) : ");
                                cont = sc.next().charAt(0);
                                 break;
                        case 4 : current.deleteProduct(sc);
                                System.out.print("\nDo you want to continue(y/n) : ");
                                cont = sc.next().charAt(0);
                                 break;
                        case 5 : current.updateProfile(sc, sellers);
                                 System.out.print("\nDo you want to continue(y/n) : ");
                                 cont = sc.next().charAt(0);
                                 break;
                        case 6 : current.Logout(sellers);
                                 cont = 'n';
                                 break;
                        default : System.out.println("Invalid option choosed !");
                                  break;
                     }
                    }while(cont=='y');
                     break;
                     
                case 4 : current = ShippingAgentLoginCheck(shippingAgent);
                     if(current.getName().equals(""))
                    {
                        System.out.println("Incorrect Username or password");
                        System.exit(0);
                    }
                    ShippingAgentMenu();
                    int opt4 = sc.nextInt();
                    switch(opt4)
                    {
                        case 1 : current.ReadShipping();
                                 current.AnotherFile();
                                 break;
                        case 2 : break;
                        case 3 : current.ViewOrderForDelivery();
                                    break;
                        case 4 : 
                        if (current instanceof Shipping_Agent) {
                            ((Shipping_Agent) current).updateProfile(sc, shippingAgent , "Shipping_Agent.csv");
                            System.out.print("\nDo you want to continue(y/n) : ");
                                     cont = sc.next().charAt(0);
                        }
                         else {
                            System.out.println("Error: Current user is not a Shipping_Agent.");
                            System.out.print("\nDo you want to continue(y/n) : ");
                                     cont = sc.next().charAt(0);
                        }
                        break;
                    default:
                        System.out.println("Invalid option selected!");
                        break;
                    }

            default : break;
            }

        sc.close();
        in.close();
    }
}
