import java.util.*;
import java.time.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;



class Cart{
    private String cart_id;
    private ArrayList<Product> product;
    private ArrayList<Integer> quantity;
    private ArrayList<Float> price;
    private double totalPrice=0 , afterDisc;
    private LocalDate date_created;
    private Payment payment;

    public Cart(String cart_id)
    {
        this.cart_id = cart_id;
        date_created = LocalDate.now();
        quantity = new ArrayList<>();
        price = new ArrayList<>();
        product = new ArrayList<>();
    }

    public void addItem(Product p , int quantity , float price)
    {
        product.add(p);
        this.quantity.add(quantity);
        this.price.add(price);
        totalPrice += price*quantity;
    }

    public void removeItem(String id)
    {
        //System.out.print(product.size());
        for(int i=0;i<product.size();i++)
        {        
            if(id.equals(product.get(i).getPId()))
            {
                product.remove(i);
                quantity.remove(i);
                price.remove(i);
                totalPrice -= price.get(i)*quantity.get(i);
                break;
            } 
        }
    }

    public void discount()
    {
        if (totalPrice>100)
            afterDisc = totalPrice - (totalPrice * 0.01);
        else if(totalPrice>200)
            afterDisc = totalPrice - (totalPrice * 0.02);
        else if(totalPrice>500)
            afterDisc = totalPrice - (totalPrice * 0.03);
        else
            afterDisc = totalPrice;
    }



    public void printCart()
    {
        // System.out.println("Hello");
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("Cart ID : " + cart_id);
        System.out.println("Date created : " + date_created);
        System.out.printf("\n%-4s %-15s %-15s %-10s %-10s %-10s \n" , "ID", "Items" , "Brand" , "Quantity" , "Price" , "Amount");
        for(int i=0;i<product.size();i++)
        {
            System.out.printf("%-4s %-15s %-15s %-10d %-10.2f %-10.2f \n" , product.get(i).getPId() , product.get(i).getPName() , product.get(i).getBrand() , quantity.get(i) , price.get(i) , ((quantity.get(i))*(price.get(i))));
        }
        System.out.printf("%41s %4s\n" , " " , "----------------------");
        System.out.printf("%54s RM %5.2f \n" , "Total Price" , totalPrice);
        System.out.println("-------------------------------------------------------------------");
    }

    public void writeReceipt(String name)
    {
        if(payment.getStatus()==true)
        {
            String filename = name + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("\nDate : " + payment.getDate());
            writer.println("Payment ID : " + payment.getPaymentId());
            writer.println("-------------------------------------");
            writer.printf("%-4s %-25s %-7s \n", "Qty", "Product", "Amount");
            writer.println("-------------------------------------");
            for (int i = 0; i < product.size(); i++) {
                writer.printf("%-4d %-25s %-7.2f \n", quantity.get(i), product.get(i).getPName(), (quantity.get(i)*price.get(i)));
            }
            writer.println("-------------------------------------");
            writer.printf("%-30s %-14s \n", "Paid by    :", name);
            writer.printf("%-26s RM %-6.2f \n", "Total AMT  :", totalPrice);
            writer.printf("%-26s RM %-6.2f \n", "After Disc :", afterDisc);
            writer.println("-------------------------------------");
            writer.println("-------------------------------------");
            writer.println("\nCASHIER NAME : Automated");
            writer.println("-------------------------------------");
            writer.printf("%22s\n", "THANK YOU");
            writer.println("-------------------------------------");
        } catch (IOException e) {
            System.out.println("Error with file !");
        }
    }
    }

    public void printReceipt(String name)
    {
        if(payment.getStatus()==true)
        {
            System.out.println("\nDate : " + payment.getDate());
            System.out.println("Payment ID : " + payment.getPaymentId());
            System.out.println("-------------------------------------");
            System.out.printf("%-4s %-25s %-7s \n" , "Qty" , "Product" , "Amount");
            System.out.println("-------------------------------------");
            for(int i=0;i<product.size();i++)
            {
                System.out.printf("%-4d %-25s %-7.2f \n" , quantity.get(i) , product.get(i).getPName() , (quantity.get(i)*price.get(i)));
            }
            System.out.println("-------------------------------------");
            System.out.printf("%-30s %-14s \n" , "Paid by    :" , name);
            System.out.printf("%-26s RM %-6.2f \n" , "Total AMT  :" , totalPrice);
            System.out.printf("%-26s RM %-6.2f \n" , "After Disc :" , afterDisc);
            System.out.println("-------------------------------------");
            System.out.println("-------------------------------------");
            System.out.println("\nCASHIER NAME : " + "Automated");
            System.out.println("-------------------------------------");
            System.out.printf("%22s\n" , "THANK YOU");
            System.out.println("-------------------------------------");

        }
    }

    public void checkout()
    {
        discount();
        payment = new Payment(cart_id, afterDisc);
        payment.pay();
    }
}
