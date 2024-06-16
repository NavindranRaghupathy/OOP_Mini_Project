import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Seller extends User {
    private String Store_name;
    private ArrayList<Physical_Goods> product;
    private ArrayList<Services> service;

    public Seller(String id, String name, String email, String username, String pass, String Store_name) {
        super(id, name , email, username, pass);
        this.Store_name = Store_name;
        product = new ArrayList<>();
        service = new ArrayList<>();
    }

    public String getStoreName() {
        return Store_name;
    }

    public void writePhysicalGoods()
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

    public void writeServices()
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

    public void ReadPhysicalProduct(Scanner in)
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

    public void ReadServiceProduct(Scanner in)
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

    public void addProduct(Scanner sc) {

        //viewProduct();
        System.out.print("\n1) Physical goods \n2) Services \n");
        System.out.print("\nPlease select an option : ");
        int opt1 = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter the product details =>");

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

            writePhysicalGoods();
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

            writeServices();
        }
    }
    

    public void updateProduct(Scanner sc) {
        viewProduct();

        System.out.print("\n1) Physical goods \n2) Services \n");
        System.out.print("\nPlease select an option : ");
        int opt1 = sc.nextInt();
        sc.nextLine();

        if(opt1==1)
        {
            System.out.print("\nPlease enter ID of product : ");
            String id1 = sc.next();
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

            writePhysicalGoods();
            
        }

        else if(opt1==2)
        {
            System.out.print("\nPlease enter ID of product : ");
            String id1 = sc.next();
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

            writeServices();
            
        }
        

    }

    
    public void deleteProduct(Scanner sc)
    {
        viewProduct();

        System.out.print("\n1) Physical goods \n2) Services \n");
        System.out.print("\nPlease select an option : ");
        int opt1 = sc.nextInt();
        sc.nextLine();

        System.out.print("\nPlease enter ID of product : ");
        String id1 = sc.next();

        if(opt1==1)
        {
            for(int i=0;i<product.size();i++)
            {
                if(product.get(i).getPId().equals(id1))
                    product.remove(i);

            }

            writePhysicalGoods();
        }

        else if(opt1==2)
        {
            for(int i=0;i<service.size();i++)
            {
                if(service.get(i).getPId().equals(id1))
                    service.remove(i);

            }

            writeServices();
        }
        
    }

    public void viewProduct() {
        System.out.printf("%-15s %-15s %-10s %-10s %-24s %-20s %-15s \n" , "Product ID" , "Name" , "Weight" , "Price" , "Quantity Available" , "Date Added" , "Brand");
        for(Physical_Goods p : product)
        {
            if(p.getSID().equals(getID()))
                p.printDetails();
        }

        System.out.printf("\n%-15s %-10s %-10s %-20s %-15s %-15s \n" , "Product ID" , "Name" , "Price" , "Date Added" , "Brand" , "Duration");
        for(Services s : service)
        {
            if(s.getSID().equals(getID()))
                s.printDetails();
        }
    }

    public void Logout() {}

    public void updateProfile() {}
}
