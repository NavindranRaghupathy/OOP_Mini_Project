import java.util.*;
import java.io.*;

public class Commerce {

    static void menu()
    {
        System.out.println("\nWelcome to our E-commerce website !");
        System.out.println("\n1) Admin \n2) Customer \n3) Seller \n4) Shipping Agent");
        System.out.print("Please select an option : ");
    }

    static void SellerMenu()
    {
        System.out.println("\n1) View Products \n2) Add Products \n3) Update Products \n4) Remove Products \n5) Update Profile \n6) Logout");
        System.out.print("Please select an option : ");
    }

    static void CustMenu()
    {
        System.out.println("\n1) View Order \n2) Add Product to cart \n3) Remove Product from cart \n4) Make Payment \n5) Update Profile \n6) Logout");
        System.out.print("Please select an option : ");
    }

    static void CustMenu2()
    {
        System.out.println("\n1) Login \n2) Sign Up ");
        System.out.print("Please select an option : ");
    }

    static void ShippingAgentMenu(){
        System.out.println("\n1) Choose order \n2) Update order status \n3) View order \n4) Update profile \n5) Logout");
        System.out.print("Please select an option : ");
    }

    static void AdminMenu(){
        System.out.println("\n1) Manage Users \n2) Update Profile \n3) Logout");
        System.out.print("Please select an option : ");
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

    static Admin adminLoginCheck (ArrayList<User> admin)
    {
            String uname2;
            String pass2;

            Console console = System.console();

            System.out.println();

            uname2 = console.readLine("Username: ");
            char[] password = console.readPassword("Passowrd: ");
            pass2 = new String(password);

            for(User s : admin)
            {
                if(s.getUsername().equals(uname2) && s.getPass().equals(pass2))
                {
                    System.out.println("\nWelcome, " + s.getName());
                    Admin a = new Admin(s.getID() , s.getName() , s.getEmail() , s.getUsername() , s.getPass());
                    return a;
                }
             }
             return new Admin("" , "" , "" , "" , "");
    }

    static ArrayList<User> readShippingAgent(Scanner in)
    {
        in.useDelimiter(",|\\n");
        String name="" , id="" , email="" , uname="" , pass="" ;
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
        
            //System.out.println(name + " " + id + " " + email + " " + uname + " " + pass + " " + store);
            s.add(new Shipping_Agent(id, name, email, uname, pass, availability));
        }
            in.close();
            return s;
    }

    static Shipping_Agent ShippingAgentLoginCheck (ArrayList<User> shippingAgent)
    {
            String uname2;
            String pass2;
            System.out.println();

            Console console = System.console();

            uname2 = console.readLine("Username: ");
            char[] password = console.readPassword("Password: ");
            pass2 = new String(password);

            for(User sa : shippingAgent)
            {
                if(sa.getUsername().equals(uname2) && sa.getPass().equals(pass2))
                {
                    System.out.println("Welcome, " + sa.getName());
                    Shipping_Agent SA = new Shipping_Agent(sa.getID() , sa.getName() , sa.getEmail() , sa.getUsername() , sa.getPass() , sa.isAvailability() );
                    return SA;
                }
             } 
             return new Shipping_Agent("" , "" , "" , "" , "" ,false);
    }

    static ArrayList<User> readAdmin(Scanner in)
    {
        in.useDelimiter(",|\\n");
        String name="" , id="" , email="" , uname="" , pass="" ;
        ArrayList<User> s = new ArrayList<>();

        while(in.hasNext())
        {
            id = in.next();
            name = in.next();
            email = in.next();
            uname = in.next();
            pass = in.next().trim();
        
            //System.out.println(name + " " + id + " " + email + " " + uname + " " + pass);
            s.add(new Admin(id, name, email, uname, pass));
        }
            in.close();
            return s;
    }

    public static int checkvalidNum(Scanner sc ,int num)
    {
        try{
            num = sc.nextInt();
            return num;
        }catch(InputMismatchException e){
            System.out.println("Invalid input entered!\n");
            return 0;
        }
    }


    public static void main(String[] args)throws IOException
    {
        Scanner sc = new Scanner(System.in);
        Scanner in = new Scanner (new File("/workspaces/OOP_Mini_Project/src/customer.csv"));
        ArrayList<User> customers = readCustomer(in);

        in = new Scanner (new File("/workspaces/OOP_Mini_Project/src/Seller.csv"));
        ArrayList<User> sellers = readSeller(in);

        in = new Scanner(new File("/workspaces/OOP_Mini_Project/src/Shipping_Agent.csv"));
        ArrayList<User> shippingAgent = readShippingAgent(in);

        in = new Scanner (new File("/workspaces/OOP_Mini_Project/src/Admin.csv"));
        ArrayList<User> admin = readAdmin(in);
       
        int opt1=0;

        do{
        User current = null;
        do{
            menu();
            opt1 = checkvalidNum(sc, opt1);
            sc.nextLine();
        }while(opt1==0);

        switch(opt1)
        {
            case 1 : int optAdmin = 0;
                     do{
                        current = adminLoginCheck(admin);
                    }while(current.getName().equals(""));
                     
                    do{
                        do{
                            AdminMenu();
                            optAdmin = checkvalidNum(sc, optAdmin);
                            sc.nextLine();
                        }while(optAdmin==0);

                        switch(optAdmin){
                            case 1 : current.manageUsers(sc);
                                    break;
                            case 2 : current.updateProfile(sc, admin);
                                    break;
                            case 3 : current.Logout(admin);
                                    break;
                            default :  System.out.println("Invalid option entered!\n");
                                        break;
                        }
                    }while(optAdmin>=1 && optAdmin<=3);
                     break;

            case 2 : int custopt=0;
                        do{
                        CustMenu2();
                        custopt = checkvalidNum(sc , custopt);
                        sc.nextLine();
                    }while(custopt==0);

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
                        System.out.println("Account successfully created !");
                        current = customerLoginCheck(customers);

                     }
                
                        in = new Scanner (new File("C:\\Users\\gopen\\OneDrive\\Desktop\\OOP VS CODE\\OOP Final Progress\\physical_product.csv"));
                        ArrayList<Physical_Goods> physical = readPhysical(in);
                        in = new Scanner (new File("C:\\Users\\gopen\\OneDrive\\Desktop\\OOP VS CODE\\OOP Final Progress\\Service_Product.csv"));
                        ArrayList<Services> service = readServices(in);
                        current.ReadCustPhysical(physical);
                        current.ReadCustService(service);
                        int opt2=0;

                        do{
                            do{
                                CustMenu();
                                opt2 = checkvalidNum(sc, opt2);
                                sc.nextLine();
                            }while(opt2==0);
                        switch(opt2)
                        {
                            case 1 : current.viewOrder();
                                    break;
                            case 2 : current.addProduct(sc);
                                    break;
                            case 3 : current.deleteProduct(sc);
                                    break;
                            case 4 : in = new Scanner (new File("C:\\Users\\gopen\\OneDrive\\Desktop\\OOP VS CODE\\OOP Final Progress\\Review.csv"));
                            		 current.checkout(in , sc);
                                     break;
                            case 5 : current.updateProfile(sc , customers);
                                     break;
                            case 6 : current.Logout(customers);
                                     break;
                              default :  System.out.println("Invalid option entered!\n");
                                        break;
                        }

                     }while(opt2>=1 && opt2<6);
                     break;

            case 3 : 
                    do{
                        current = sellerLoginCheck(sellers);
                        if(current.getName().equals("")){
                            System.out.println("Incorrect Username or password");
                            //System.exit(0);
                        }
                    }while(current.getName().equals(""));
                     in = new Scanner (new File("C:\\Users\\gopen\\OneDrive\\Desktop\\OOP VS CODE\\OOP Final Progress\\physical_product.csv"));
                     current.ReadPhysicalProduct(in);
                     in = new Scanner (new File("C:\\Users\\gopen\\OneDrive\\Desktop\\OOP VS CODE\\OOP Final Progress\\Service_Product.csv"));
                     current.ReadServiceProduct(in);
                     int optSell = 0;
                     do{
                        do{
                            SellerMenu();
                            optSell = checkvalidNum(sc, optSell);
                            sc.nextLine();
                        }while(optSell==0);
                     switch(optSell)
                     {
                        case 1 : current.viewProduct();
                                 break;
                        case 2 : current.addProduct(sc);
                                 break;
                        case 3 : current.updateProduct(sc);
                                 break;
                        case 4 : current.deleteProduct(sc);
                                 break;
                        case 5 : current.updateProfile(sc, sellers);
                                 break;
                        case 6 : current.Logout(sellers);
                                 break;
                        default : System.out.println("Invalid option choosed !");
                                  break;
                     }
                    }while(optSell>=1 && optSell<=6);
                     break;
                     
                case 4 : current = ShippingAgentLoginCheck(shippingAgent);
                     if(current.getName().equals(""))
                    {
                        System.out.println("Incorrect Username or password");
                        System.exit(0);
                    }
                    int opt4=0;
                    do{
                        do{
                            ShippingAgentMenu();
                            opt4 = checkvalidNum(sc, opt4);
                        }while(opt4==0);
                    switch(opt4)
                    {
                        case 1 : current.ReadShipping();
                                 current.ViewFile();
                                 current.AgentFile(sc);
                                 break;
                        case 2 : current.UpdateOrder(sc);
                                break;
                        case 3 : current.ViewOrderForDelivery();
                                    break;
                        case 4 : current.updateProfile(sc, shippingAgent);
                                 break;
                        case 5: current.Logout(shippingAgent);
                                break;
                    default:
                        System.out.println("Invalid option selected!");
                        break;
                    }
                }while(opt4>=1 && opt4<=5);

            default :  System.out.println("Invalid option entered!\n");
                        break;
            }

        }while(opt1<1 || opt1>4);

        sc.close();
        in.close();
    }

}
