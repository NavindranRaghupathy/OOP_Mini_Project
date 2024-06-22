import java.time.LocalDate; //import all LocalDate class from java.time package
import java.time.format.DateTimeFormatter; //import DateTimeFormatter from java.time.format package
import java.util.*; //import all the classes from java.util package
import java.io.*; //import all the classes from java.io package

class Seller extends User {
    private String Store_name; //variable that stores store name of seller
    private ArrayList<Physical_Goods> product; // Arraylist that store physical product object
    private ArrayList<Services> service; // Arraylist that store service object

    public Seller(String id, String name, String email, String username, String pass, String Store_name) { //Constructor to initialize item with the given attributes
        super(id, name , email, username, pass);
        this.Store_name = Store_name;
        product = new ArrayList<>();
        service = new ArrayList<>();
    }

    public String getStoreName() { // Accessor for Store_name variable
        return Store_name;
    }

    public void writePhysicalGoods() //Method to save the physical product information into physical_product.csv
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter("physical_product.csv"))) {
            for (Physical_Goods p : product) {
                    writer.print(p.getSID() + ",");
                    writer.print(p.getPId() + ",");
                    writer.print(p.getPName() + ",");
                    writer.print(p.getPrice() + ",");
                    writer.print(p.getQuantity() + ",");
                    writer.print(p.getDate() + ",");
                    writer.print(p.getBrand() + ",");
                    writer.print(p.getWeight());
                    writer.println();
            }
        } catch (IOException e) {
            System.out.println("Error : File not found");
        }
    }

    public void writeServices() //Method to save the service information into Service_Product.csv
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Service_Product.csv"))) {
            for (Services s : service) {
                    writer.print(s.getSID() + ",");
                    writer.print(s.getPId() + ",");
                    writer.print(s.getPName() + ",");
                    writer.print(s.getDate() + ",");
                    writer.print(s.getBrand() + ",");
                    writer.print(s.getDuration() + ",");
                    writer.print(s.getPrice());
                    writer.println();
            }
        } catch (IOException e) {
            System.out.println("Error : File not found");
        }
    }

    public void ReadPhysicalProduct(Scanner in) //Method used to read the Physical Product from a csv file and save it to the product arraylist
    {
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
            product.add(new Physical_Goods(sid , pid, pname, price, quantity , date , brand , weight));
        }
            in.close();
    }

    public void ReadServiceProduct(Scanner in) //Method used to read the Services from a csv file and save it to the service arraylist
    {
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
            service.add(new Services(sid , pid, pname, price , date , brand , duration));
        }
            in.close();
    }

    public void addProduct(Scanner sc) { //Method that the seller use to add products

        //viewProduct();
        int opt1;
        do{
            System.out.print("\n1) Physical goods \n2) Services \n");
            System.out.print("\nPlease select an option : ");
            opt1 = sc.nextInt();
            sc.nextLine();
        }while(opt1<1 || opt1>2);

        System.out.println("\nPlease enter the product details =>");

        if(opt1==1)
        {
            System.out.print("\nProduct Name : ");
            String pname = sc.nextLine();
            System.out.print("Price : ");
            float price = sc.nextFloat();
            System.out.print("Quantity Available : ");
            int quantity = sc.nextInt();
            sc.nextLine();
            //System.out.print("Date : ");
            //String date = sc.nextLine();
            String date;
            System.out.print("Brand : ");
            String brand = sc.nextLine();
            System.out.print("Weight : ");
            float weight = sc.nextFloat();
            sc.nextLine();

            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = currentDate.format(formatter);


            String sub = product.get(product.size()-1).getPId();
            sub = sub.substring(1, sub.length());
            int id1 = Integer.parseInt(sub);
            String pid = "P" + (id1+1);
            product.add(new Physical_Goods(getID() , pid, pname, price, quantity , date , brand , weight));

            //writePhysicalGoods();
        }

        else if(opt1==2)
        {
            System.out.print("\nProduct Name : ");
            String pname = sc.nextLine();
            System.out.print("Price : ");
            float price = sc.nextFloat();
            sc.nextLine();
            // System.out.print("Date : ");
            // String date = sc.nextLine();
            String date;
            System.out.print("Brand : ");
            String brand = sc.nextLine();
            System.out.print("Duration : ");
            String duration = sc.nextLine();

            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = currentDate.format(formatter);

            String sub = service.get(service.size()-1).getPId();
            sub = sub.substring(1, sub.length());
            int id1 = Integer.parseInt(sub);
            String pid = "C" + (id1+1);
            service.add(new Services(getID() , pid, pname, price , date , brand , duration));

            //writeServices();
        }
    }
    

    public void updateProduct(Scanner sc) { //Method that the seller use to update products
        viewProduct();
        int opt1;

        // do{
        //     System.out.print("\n1) Physical goods \n2) Services \n");
        //     System.out.print("\nPlease select an option : ");
        //     opt1 = sc.nextInt();
        //     sc.nextLine();
        // }while(opt1<1 || opt1>2);
        System.out.print("\nPlease enter ID of product : ");
        String id1 = sc.next();
        id1 = id1.toUpperCase();

        if(id1.charAt(0)=='P')
        {
            System.out.print("\n1) Product Name \n2) Price \n3) Brand \n4) Quantity");
            System.out.print("\nPlease select an option : ");
            opt1 = sc.nextInt();
            sc.nextLine();

            if(opt1==1)
            {
                System.out.print("\nPlease enter new Product name : ");
                String name = sc.nextLine();
                for(Physical_Goods p : product)
                {
                    if(p.getPId().equals(id1))
                        p.setPName(name);
                }
            }
            else if(opt1==2)
            {
                System.out.print("\nPlease enter new Product price : RM ");
                float price = sc.nextFloat();
                for(Physical_Goods p : product)
                {
                    if(p.getPId().equals(id1))
                        p.setPrice(price);
                }
            }
            else if(opt1==3)
            {
                System.out.print("\nPlease enter new Product brand : ");
                String b = sc.nextLine();
                for(Physical_Goods p : product)
                {
                    if(p.getPId().equals(id1))
                        p.setBrand(b);
                }
            }
            else if(opt1==4)
            {
                System.out.print("\nPlease enter Product quantity : ");
                int q = sc.nextInt();
                sc.nextLine();
                for(Physical_Goods p : product)
                {
                    if(p.getPId().equals(id1))
                        p.setQuantity(q);
                }
            }

            //writePhysicalGoods();
            
        }

        else if(id1.charAt(0)=='C')
        {
            System.out.print("\n1) Product Name \n2) Price \n3) Brand \n4) Duration");
            System.out.print("\nPlease select an option : ");
            opt1 = sc.nextInt();
            sc.nextLine();

            if(opt1==1)
            {
                System.out.print("\nPlease enter new Product name : ");
                String name = sc.nextLine();
                for(Services s : service)
                {
                    if(s.getPId().equals(id1))
                        s.setPName(name);
                }
            }
            else if(opt1==2)
            {
                System.out.print("\nPlease enter new Product price : RM ");
                float price = sc.nextFloat();
                for(Services s : service)
                {
                    if(s.getPId().equals(id1))
                        s.setPrice(price);
                }
            }
            else if(opt1==3)
            {
                System.out.print("\nPlease enter new Product brand : ");
                String b = sc.nextLine();
                for(Services s : service)
                {
                    if(s.getPId().equals(id1))
                        s.setBrand(b);
                }
            }

            else if(opt1==4)
            {
                System.out.print("\nPlease enter new Product duration : ");
                String d = sc.nextLine();
                for(Services s : service)
                {
                    if(s.getPId().equals(id1))
                        s.setBrand(d);
                }
            }

            //writeServices();
            
        }
        

    }

    
    public void deleteProduct(Scanner sc) //Method that the seller use to delete products
    {
        viewProduct();
        // int opt1;

        // do{
        //     System.out.print("\n1) Physical goods \n2) Services \n");
        //     System.out.print("\nPlease select an option : ");
        //     opt1 = sc.nextInt();
        //     sc.nextLine();
        // }while(opt1<1 || opt1>2);

        System.out.print("\nPlease enter ID of product : ");
        String id1 = sc.next();
        id1 = id1.toUpperCase();

        if(id1.charAt(0)=='P')
        {
            for(int i=0;i<product.size();i++)
            {
                if(product.get(i).getPId().equals(id1))
                    product.remove(i);

            }

            //writePhysicalGoods();
        }

        else if(id1.charAt(0)=='C')
        {
            for(int i=0;i<service.size();i++)
            {
                if(service.get(i).getPId().equals(id1))
                    service.remove(i);

            }

            //writeServices();
        }
        
    }

    public void viewProfile() //Method that display the information about the seller (name , email and store name)
    {
        System.out.println("\nName : " + getName());
        System.out.println("Email : " + getEmail());
        System.out.println("Store Name : " + Store_name);
    }

    public void updateProfile(Scanner sc , ArrayList<User> user) //Method that the seller use to update his profile (name , email , username , password and store name)
    {
        viewProfile();
        String name , email , uname , pass ;
        int opt;
        do{
            System.out.println("\n1) Name \n2) Email \n3) Username \n4) Password \n5) Strore name \n");
            System.out.print("Please select an option : ");
            opt = sc.nextInt();
            sc.nextLine();


            switch(opt)
            {
                case 1 : System.out.print("\nName : ");
                        name = sc.nextLine();
                        setName(name);
                        break;
                case 2 : System.out.print("\nEmail : ");
                        email = sc.nextLine();
                        setEmail(email);
                        break;
                case 3 : System.out.print("\nUsername : ");
                        uname = sc.nextLine();
                        setUsername(uname);
                        break;
                case 4 : System.out.print("\nPassword : ");
                        pass = sc.nextLine();
                        setPass(pass);
                        break;
                case 5 : System.out.print("\nStore name : ");
                        this.Store_name = sc.nextLine();
                        break;
                default : System.out.println("Invalid option selected!");
                        break;
            }
        
        }while(opt<1 | opt>6);

        for(int i=0;i<user.size();i++)
        {
            if(getID().equals(user.get(i).getID()))
            {
                user.set(i, this);
                break;
            }
                
        }
    }

    public void viewProduct() {  // Method that that seller use to display the contents of the product and service arraylist
        System.out.println("\nPhysical Products : ");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.printf("%-15s %-15s %-10s %-10s %-24s %-20s %-15s %-10s\n" , "Product ID" , "Name" , "Weight" , "Price" , "Quantity Available" , "Date Added" , "Brand" , "Review");
        for(Physical_Goods p : product)
        {
            if(p.getSID().equals(getID()))
                p.printDetails();
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
        System.out.println("\nServices : ");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.printf("%-15s %-10s %-10s %-20s %-15s %-15s %-10s\n" , "Product ID" , "Name" , "Price" , "Date Added" , "Brand" , "Duration" , "Review");
        for(Services s : service)
        {
            if(s.getSID().equals(getID()))
                s.printDetails();
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public void Logout(ArrayList<User> user) { //Method that the seller use to log out from the system
        writePhysicalGoods();
        writeServices();
        try (PrintWriter writer = new PrintWriter(new FileWriter("Seller.csv"))) {
            for (User u : user) {
                    writer.print(u.getID() + ",");
                    writer.print(u.getName() + ",");
                    writer.print(u.getEmail() + ",");
                    writer.print(u.getUsername() + ",");
                    writer.print(u.getPass() + ",");
                    writer.print(u.getStoreName());
                    writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

}
