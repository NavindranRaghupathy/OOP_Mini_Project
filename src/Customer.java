import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;


class Customer extends User {
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
        System.out.printf("%-15s %-15s %-10s %-10s %-24s %-20s %-15s \n" , "Product ID" , "Name" , "Weight" , "Price" , "Quantity Available" , "Date Added" , "Brand");
        for(Physical_Goods p : product)
        {
                p.printDetails();
        }

        System.out.printf("\n%-15s %-10s %-10s %-20s %-15s %-15s %-10s\n" , "Product ID" , "Name" , "Price" , "Date Added" , "Brand" , "Duration" , "Review");
        for(Services s : service)
        {
                s.printDetails();
        }
    }

    public void viewOrder() {
        cart.printCart();
    }

    public void buyProducts(Scanner sc) {
        viewProduct();
        System.out.print("\nPlease enter the product ID : ");
        String id = sc.next();


        if(id.charAt(0)=='P')
        {
            System.out.print("Please enter the quantity : ");
            int quantity = sc.nextInt();
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
        cart.removeItem(id);

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
        cart.checkout();
        cart.printReceipt(getName());
        cart.writeReceipt(getName());
        writePhysicalGoods();
        giveReview(in , sc);
        cart.removeAllProduct();
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

        System.out.println("\nPlease give rating for the products you order =>");
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
}
