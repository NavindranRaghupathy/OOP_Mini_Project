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
        System.out.println("\n1) View Products \n2) Add Products \n3) Update Products \n4) Remove Products");
        System.out.print("Please selsect an option : ");
    }

    static void CustMenu()
    {
        System.out.println("\n1) View Order \n2) Add Product to cart \n3) Update Product in Cart \n4) Remove Product from cart \n5) Make Payment");
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

    static Customer customerLoginCheck (ArrayList<User> cust)
    {
            String uname2;
            String pass2;

            Console console = System.console();

            uname2 = console.readLine("Username: ");
            char[] password = console.readPassword("Passowrd: ");
            pass2 = new String(password);

            for(User cu : cust)
            {
                if(cu.getUsername().equals(uname2) && cu.getPass().equals(pass2))
                {
                    System.out.println("Welcome, " + cu.getName());
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

            uname2 = console.readLine("Username: ");
            char[] password = console.readPassword("Passowrd: ");
            pass2 = new String(password);

            for(User s : sell)
            {
                if(s.getUsername().equals(uname2) && s.getPass().equals(pass2))
                {
                    System.out.println("Welcome, " + s.getName());
                    Seller se = new Seller(s.getID() , s.getName() , s.getEmail() , s.getUsername() , s.getPass() , s.getStoreName());
                    return se;
                }
             }
             return new Seller("" , "" , "" , "" , "" ,"");
    }
    public static void main(String[] args)throws IOException
    {
        Scanner sc = new Scanner(System.in);
        Scanner in = new Scanner (new File("customer.csv"));
        ArrayList<User> customers = readCustomer(in);

        in = new Scanner (new File("Seller.csv"));
        ArrayList<User> sellers = readSeller(in);

        User agents[] = new Shipping_Agent[10];

        User current;
        menu();
        int opt1 = sc.nextInt();
        sc.nextLine();

        switch(opt1)
        {
            case 1 : break;

            case 2 : current = customerLoginCheck(customers);
                     if(current.getName().equals(""))
                     {
                        current.SignUp(customers , sc);
                        in = new Scanner (new File("customer.csv"));
                        customers = readCustomer(in);
                        current = customerLoginCheck(customers);
                     }else
                     {
                        in = new Scanner (new File("physical_product.csv"));
                        ArrayList<Physical_Goods> physical = readPhysical(in);
                        in = new Scanner (new File("Service_Product.csv"));
                        ArrayList<Services> service = readServices(in);
                        current.ReadCustPhysical(physical);
                        current.ReadCustService(service);
                        char cont='y';

                        do{
                        CustMenu();
                        int opt2 = sc.nextInt();
                        sc.nextLine();
                        switch(opt2)
                        {
                            case 1 : current.viewOrder();
                                     System.out.print("\nDo you want to continue(y/n) : ");
                                     cont = sc.next().charAt(0);
                                    break;
                            case 2 : current.buyProducts(sc);
                                    System.out.print("\nDo you want to continue(y/n) : ");
                                    cont = sc.next().charAt(0);
                                    break;
                            // case 3 : current.updateProduct(sc);
                            //         break;
                            case 4 : current.deleteProduct(sc);
                                    break;
                            case 5 : current.checkout();
                                     System.out.print("\nDo you want to continue(y/n) : ");
                                     cont = sc.next().charAt(0);
                                     break;
                            default : System.out.println("Invalid option choosed !");
                                    break;
                        }

                     }while(cont=='y');
                    }
                     break;

            case 3 : current = sellerLoginCheck(sellers);
                    if(current.getName().equals(""))
                    {
                        System.out.println("Incorrect Username or password");
                        System.exit(0);
                    }
                     in = new Scanner (new File("physical_product.csv"));
                     current.ReadPhysicalProduct(in);
                     in = new Scanner (new File("Service_Product.csv"));
                     current.ReadServiceProduct(in);
                     SellerMenu();
                     int opt2 = sc.nextInt();
                     sc.nextLine();
                     switch(opt2)
                     {
                        case 1 : current.viewProduct();
                                 break;
                        case 2 : current.addProduct(sc);
                                 break;
                        case 3 : current.updateProduct(sc);
                                 break;
                        case 4 : current.deleteProduct(sc);
                                 break;
                        default : System.out.println("Invalid option choosed !");
                                  break;
                     }
                     break;
                     
                     
        }

        sc.close();
        in.close();
    }
}
