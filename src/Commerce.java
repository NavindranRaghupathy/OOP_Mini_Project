import java.util.*; //import all the classes from java.util package
import java.io.*; //import all the classes from java.io package

public class Commerce {

    static void menu() // Static method to display the main menu
    {
        System.out.println("\nWelcome to our E-commerce website !");
        System.out.println("\n1) Admin \n2) Customer \n3) Seller \n4) Shipping Agent");
        System.out.print("Please select an option : ");
    }

    static void SellerMenu() // Static method to display seller menu
    {
        System.out.println("\n1) View Products \n2) Add Products \n3) Update Products \n4) Remove Products \n5) Update Profile \n6) Logout");
        System.out.print("Please select an option : ");
    }

    static void CustMenu() // Static method to display customer menu
    {
        System.out.println("\n1) View Order \n2) Add Product to cart \n3) Remove Product from cart \n4) Make Payment \n5) Update Profile \n6) Logout");
        System.out.print("Please select an option : ");
    }

    static void CustMenu2() // Static method to display customer login and signup menu
    {
        System.out.println("\n1) Login \n2) Sign Up ");
        System.out.print("Please select an option : ");
    }

    static void ShippingAgentMenu(){ // Static method to display shipping agent menu
        System.out.println("\n1) Choose order \n2) Update order status \n3) View order \n4) Update profile \n5) Logout");
        System.out.print("Please select an option : ");
    }

    static void AdminMenu(){ // Static method to display admin menu
        System.out.println("\n1) Manage Users \n2) Update Profile \n3) Logout");
        System.out.print("Please select an option : ");
    }

    static ArrayList<Physical_Goods> readPhysical(Scanner in) // Static method to read the Physical product from a csv file and return the physical products that are saved in arraylist
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

    static ArrayList<Services> readServices(Scanner in) // Static method to read the Services from a csv file and return the services that are saved in arraylist
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

    static ArrayList<User> readCustomer(Scanner in) // Static method to read Customers from a csv file and return the customers that are saved in arraylist
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

    static Customer customerLoginCheck (ArrayList<User> cust){ // Static method for the Customer login

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

    static ArrayList<User> readSeller(Scanner in) // Static method to read the Seller from a csv file and return the sellers that are saved in arraylist
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

    static Seller sellerLoginCheck (ArrayList<User> sell) // Static method for the seller login
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

    static Admin adminLoginCheck (ArrayList<User> admin) // Static method for the Admin login
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

    static ArrayList<User> readShippingAgent(Scanner in) // Static method to read the shipping agent from a csv file and return the shiping agent that are saved in arraylist
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

    static Shipping_Agent ShippingAgentLoginCheck (ArrayList<User> shippingAgent) // Static method for the Shipping agent login
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

    static ArrayList<User> readAdmin(Scanner in) // Static method to read the Admin from a csv file and return the adnmin that are saved in arraylist
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

    public static int checkvalidNum(Scanner sc ,int num) // Static method that check whether the entered input is integer or not
    {
        try{
            num = sc.nextInt();
            return num;
        }catch(InputMismatchException e){
            System.out.println("Invalid input entered!\n");
            return 0;
        }
    }


    public static void main(String[] args)throws IOException // Main method of the system
    {
        Scanner sc = new Scanner(System.in);
        
        Scanner in = new Scanner (new File("/workspaces/OOP_Mini_Project/src/Admin.csv")); // Create scanner to read admin csv
        ArrayList<User> admin = readAdmin(in); // arraylist to store admins
       
        int opt1=0;

        do{
        User current = null; // Create user object
        do{  
            menu(); //call static method menu display main menu
            opt1 = checkvalidNum(sc, opt1); //read the option entered by the user
            sc.nextLine();
        }while(opt1==0); //loop if the user choose invalid option

        switch(opt1)
        {
            case 1 : int optAdmin = 0; // Admin
                     do{
                        current = adminLoginCheck(admin); // Admin login
                    }while(current.getName().equals("")); //loop if the user choose invalid option
                     
                    do{
                        do{
                            AdminMenu(); //call static method AdminMenu() display main menu
                            optAdmin = checkvalidNum(sc, optAdmin);
                            sc.nextLine();
                        }while(optAdmin==0);

                        switch(optAdmin){
                            case 1 : current.manageUsers(sc); //call manageUsers method in Admin class using the user object
                                    break;
                            case 2 : current.updateProfile(sc, admin); //call updateProfile method in Admin class using the user object
                                    break;
                            case 3 : current.Logout(admin); //call Logout method in Admin class using the user object
                                    break;
                            default :  System.out.println("Invalid option entered!\n"); //Display 'Invalid option entered!' if invalid option is entered
                                        break;
                        }
                    }while(optAdmin>=1 && optAdmin<=3); //loop if the user choose valid options (one to three)
                     break;

            case 2 : in = new Scanner (new File("/workspaces/OOP_Mini_Project/src/customer.csv")); // Create scanner to read customers csv
                     ArrayList<User> customers = readCustomer(in); // arraylist to store customers
                     int custopt=0;
                        do{
                        CustMenu2(); // call CustMenu2 to display customer login option 
                        custopt = checkvalidNum(sc , custopt);
                        sc.nextLine();
                    }while(custopt==0);

                     if(custopt == 1)
                     {  
                        do{
                            current = customerLoginCheck(customers); // Customer login
                        }while(current.getName().equals(""));
                     }
                     
                     else if(custopt==2)
                     {
                        current = new Customer();
                        current.SignUp(customers , sc); // Call customer sign up method in Customer
                        System.out.println("Account successfully created !");
                        current = customerLoginCheck(customers); // Customer login again after sign up

                     }
                
                        in = new Scanner (new File("/workspaces/OOP_Mini_Project/src/physical_product.csv")); // Create scanner to read physical products csv
                        ArrayList<Physical_Goods> physical = readPhysical(in); // arraylist to store physical products
                        in = new Scanner (new File("/workspaces/OOP_Mini_Project/src/Service_Product.csv")); // Create scanner to read service csv
                        ArrayList<Services> service = readServices(in); // arraylist to store services
                        current.ReadCustPhysical(physical); //call readCustPhysical method in Customer class using the user object
                        current.ReadCustService(service); //call readCustService method in Customer class using the user object
                        int opt2=0;

                        do{
                            do{
                                CustMenu(); //Display the customer options
                                opt2 = checkvalidNum(sc, opt2);
                                sc.nextLine();
                            }while(opt2==0);
                        switch(opt2)
                        {
                            case 1 : current.viewOrder(); //call viewOrder method in Customer class to display the products available
                                    break;
                            case 2 : current.addProduct(sc); //call addProduct method in Customer class to add product to cart
                                    break;
                            case 3 : current.deleteProduct(sc); //call deleteProduct method in Customer class to delete product from cart
                                    break;
                            case 4 : in = new Scanner (new File("/workspaces/OOP_Mini_Project/src/Review.csv")); // Create scanner to read review csv
                            		 current.checkout(in , sc); //call checkout method in Customer class for payment
                                     break;
                            case 5 : current.updateProfile(sc , customers); //call updateProfile method in Customer class to update profile
                                     break;
                            case 6 : current.Logout(customers); //call Logout method in Customer for customer to logout
                                     break;
                              default :  System.out.println("Invalid option entered!\n");
                                        break;
                        }

                     }while(opt2>=1 && opt2<6);
                     break;

            case 3 : in = new Scanner (new File("/workspaces/OOP_Mini_Project/src/Seller.csv")); // Create scanner to read seller csv
                     ArrayList<User> sellers = readSeller(in); // arraylist to store sellers
                    do{
                        current = sellerLoginCheck(sellers); // Seller login
                        if(current.getName().equals("")){
                            System.out.println("Incorrect Username or password");
                            //System.exit(0);
                        }
                    }while(current.getName().equals(""));
                     in = new Scanner (new File("/workspaces/OOP_Mini_Project/src/physical_product.csv"));
                     current.ReadPhysicalProduct(in);
                     in = new Scanner (new File("/workspaces/OOP_Mini_Project/src/Service_Product.csv"));
                     current.ReadServiceProduct(in);
                     int optSell = 0;
                     do{
                        do{
                            SellerMenu(); //Display seller options
                            optSell = checkvalidNum(sc, optSell);
                            sc.nextLine();
                        }while(optSell==0);
                     switch(optSell)
                     {
                        case 1 : current.viewProduct(); //call viewProduct method in Seller class for seller to view the products assiciated with them
                                 break;
                        case 2 : current.addProduct(sc); //call addProduct method in Seller class for seller to add Products 
                                 break;
                        case 3 : current.updateProduct(sc); //call updateProduct method in Seller class for seller to update product details
                                 break;
                        case 4 : current.deleteProduct(sc); //call deleteProduct method in Seller class for seller to delete the product they wish to delete
                                 break;
                        case 5 : current.updateProfile(sc, sellers); //call updateProfile method in Seller class for seller to update their personal details
                                 break;
                        case 6 : current.Logout(sellers); //call Logout method in Seller class for seller to logout
                                 break;
                        default : System.out.println("Invalid option choosed !");
                                  break;
                     }
                    }while(optSell>=1 && optSell<=6);
                     break;
                     
                case 4 : in = new Scanner(new File("/workspaces/OOP_Mini_Project/src/Shipping_Agent.csv")); // Create scanner to read shipping agent csv
                         ArrayList<User> shippingAgent = readShippingAgent(in); // arraylist to store shipping agent

                         do{
                        current = ShippingAgentLoginCheck(shippingAgent); //Shipping agent login
                        if(current.getName().equals(""))
                        {
                            System.out.println("Incorrect Username or password");
                        }}while(current.getName().equals(""));
                        int opt4=0;
                        do{
                            do{
                                ShippingAgentMenu(); //Display shipping agent options
                                opt4 = checkvalidNum(sc , opt4);
                                sc.nextLine();
                            }while(opt4==0);
                        switch(opt4)
                        {
                            case 1 : current.ReadShipping(); //call ReadShipping method in shipping agent class for reading shipping orders
                                    current.ViewFile(); //call viewFile method in shipping agent class for display shipping orders
                                    current.AgentFile(sc); //call AgentFile method in shipping agent class for shipping agent to select shipping orders they prefer
                                    break;
                            case 2 : current.UpdateOrder(sc); //call UpdateOrder method in shipping agent class for shipping agent to update shipping status
                                    break;
                            case 3 : current.ViewOrderForDelivery(); //call ViewOrderForDelivery method in shipping agent class for viewwing shiping agent associated shipping deliveries
                                        break;
                            case 4 : current.updateProfile(sc, shippingAgent); //call updateProfile method in shipping agent class for shipping agent to update their personal details
                                    break;
                            case 5: current.Logout(shippingAgent); //call Logout method in shipping agent for shipping agent to logout
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
