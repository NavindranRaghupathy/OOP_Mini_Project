import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;


class Customer extends User implements InnerCust_Int {
    private String address;
    private String phone_no;
    private ArrayList<Physical_Goods> product;
    private ArrayList<Services> service;
    private Cart cart;
    private Review[] review;

    public Customer(String id, String name, String email, String username, String pass, String address, String phone_no) {
        super(id, name, email, username, pass);
        this.address = address;
        this.phone_no = phone_no;
        cart = new Cart(id);
        review = new Review[50];
    }

    public Customer() {

    }



    public void ReadCustPhysical(ArrayList<Physical_Goods> p)
    {
        product = p;
    }

    public void ReadCustService(ArrayList<Services> s)
    {
        service = s;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phone_no;
    }

    public void viewProduct()
    {
        System.out.println("\nPhysical Products : ");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.printf("%-15s %-15s %-10s %-10s %-24s %-20s %-15s %-10s\n" , "Product ID" , "Name" , "Weight" , "Price" , "Quantity Available" , "Date Added" , "Brand" , "Review");
        for(Physical_Goods p : product)
        {
                p.printDetails();
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        System.out.println("\nServices : ");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.printf("%-15s %-10s %-10s %-20s %-15s %-15s %-10s\n" , "Product ID" , "Name" , "Price" , "Date Added" , "Brand" , "Duration" , "Review");
        for(Services s : service)
        {
                s.printDetails();
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public void viewOrder() {
        cart.printCart();
    }

    public void addProduct(Scanner sc) {
        viewProduct();
        System.out.print("\nPlease enter the product ID : ");
        String id = sc.next();
        id = id.toUpperCase();

        if(id.charAt(0)=='P')
        {
            int quantity = 0;
            do{
                try{
                    System.out.print("Please enter the quantity : ");
                    quantity = sc.nextInt();
                    sc.nextLine();
                }catch(InputMismatchException e)
                {
                    System.out.println("Invalid quantity entered!");
                    quantity = 0;
                    sc.nextLine();
                }
            }while(quantity<=0);
            for(Physical_Goods pro : product)
            {
                if(id.equals(pro.getPId()))
                {
                    if(pro.getQuantity()>quantity)
                    {
                        pro.setQuantity(pro.getQuantity()-quantity);
                        cart.addItem(pro , quantity , pro.getPrice());
                        //System.out.println(pro.getQuantity());
                    }
                }
            }

        }
        else if(id.charAt(0)=='C')
        {
            for(Services ser : service)
            {
                if(id.equals(ser.getPId()))
                {
                    cart.addItem(ser , 1 , ser.getPrice());
                }
            }
        }

        //viewOrder();
    }

    public void deleteProduct(Scanner sc)
    {
        viewOrder();

        System.out.print("\nPlease enter the product ID to delete : ");
        String id = sc.next();
        id = id.toUpperCase();
        int q = cart.removeItem(id);

        if(id.charAt(0)=='P')
        {
            for(Physical_Goods pro : product)
            {
                if(id.equals(pro.getPId()))
                {
                        pro.setQuantity(pro.getQuantity()+q);
                }
            }

        }

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

    public void checkout(Scanner in , Scanner sc) {
        cart.checkout(getName() , getID() , address);
        // cart.printReceipt(getName());
        // cart.writeReceipt(getName());
        writePhysicalGoods();
        giveReview(in , sc);
        
        cart.removeAllProduct();
    }

    public void updateProfile(Scanner sc , ArrayList<User> user)
    {
        viewProfile();
        String name , email , uname , pass ;
        int opt;
        do{
            System.out.println("\n1) Name \n2) Email \n3) Username \n4) Password \n5) Address \n6) Phone number\n");
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
                case 5 : System.out.print("\nAdress : ");
                        this.address = sc.nextLine();
                        break;
                case 6 : System.out.print("\nPhone num : ");
                        this.phone_no = sc.nextLine();
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

    public void SignUp(ArrayList<User> user , Scanner sc)
    {
        String name="" , id="" , email="" , uname="" , pass="" , address="" , phone="" , pass2="";
            System.out.println("Please enter following details");
            System.out.print("\nName : ");
            name = sc.nextLine();
            System.out.print("Email : ");
            email = sc.nextLine();
            System.out.print("Address : ");
            address = sc.nextLine();
            System.out.print("Phone no. : ");
            phone = sc.nextLine();
            System.out.print("Username : ");
            uname = sc.nextLine();
            System.out.print("Pass : ");
            pass = sc.nextLine();
            do{
            System.out.print("\nRe-enter pass : ");
            pass2 = sc.nextLine();
        }while(!pass.equals(pass2));

        String sub = user.get(user.size()-1).getID();
        sub = sub.substring(1, sub.length());
        int id1 = Integer.parseInt(sub);
        id = "A" + (id1+1);
        //System.out.println(id);
        user.add(new Customer(id, name, email, uname, pass2, address, phone));

    }

    public int readReview(Scanner in)
    {
        in.useDelimiter(",|\\n");
        String temp;
        String pid="" , rid="" , date="";
        float rating;
        int i =0,total;
        while(in.hasNext())
        {
            pid = in.next();
            rid  = in.next();
            date = in.next();
            temp = in.next();
            rating = Float.parseFloat(temp);
            temp = in.next().trim();
            total = Integer.parseInt(temp);

            //System.out.println(pid + " " + rid + " " + date + " " + rating + " " + total);
            review[i] = new Review(pid , rid , rating , total , date);
            i++;
        }
            in.close();
            return i;
    }

    public void writeReview(int n)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Review.csv"))) {
            for (int i=0;i<n;i++) {
                    writer.print(review[i].getPID() + ",");
                    writer.print(review[i].getID() + ",");
                    writer.print(review[i].getDate() + ",");
                    writer.print(review[i].getRate() + ",");
                    writer.print(review[i].getTotalCust());
                    writer.println();
            }
        } catch (IOException e) {
            System.out.println("Error while creating the file!");
        }
    }

    public void giveReview(Scanner in , Scanner sc)
    {
        int n=0;
		n = readReview(in);
        boolean found = false;

        ArrayList<Product> p = cart.getProduct();

        System.out.println("\nPlease give rating for the products you order =>\n");
        for(int i=0;i<p.size();i++)
        {
            System.out.print(p.get(i).getPName() + "(" + p.get(i).getBrand() + ") : ");
            float rating = sc.nextFloat();

            for(int j=0;j<n;j++)
            {
                if(p.get(i).getPId().equals(review[j].getPID()))
                {
                    review[j].setRate(rating);
                    review[j].setTotal();
                    found = true;
                    break;
                }   
            }

            if(!found)
            {
                String sub = review[n-1].getID();
                sub = sub.substring(1, sub.length());
                int id1 = Integer.parseInt(sub);
                String rid = "R" + (id1+1);

                review[n] = new Review(p.get(i).getPId() , rid , rating);
                n++;
            }
        }

        writeReview(n);
    }

    public void viewProfile()
    {
        System.out.println("\nName : " + getName());
        System.out.println("Email : " + getEmail());
        System.out.println("Address : " + address);
        System.out.println("Phone num : " + phone_no);
    }

    public void Logout(ArrayList<User> user)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter("customer.csv"))) {
            for (User u : user) {
                    writer.print(u.getName() + ",");
                    writer.print(u.getID() + ",");
                    writer.print(u.getEmail() + ",");
                    writer.print(u.getUsername() + ",");
                    writer.print(u.getPass() + ",");
                    writer.print(u.getAddress() + ",");
                    writer.print(u.getPhoneNo());
                    writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
