import java.util.*;
import java.time.*;
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

    public ArrayList<Integer> getQuantity(){
        return quantity;
    }
    public ArrayList<Product> getProduct()
    {
        return product;
    }

    public void addItem(Product p , int q , float price)
    {
        
        boolean found = false;

        for(int i=0;i<product.size();i++)
        {
            if(p.getPId().equals(product.get(i).getPId()))
            {
                quantity.set(i,quantity.get(i)+q);
                totalPrice += this.price.get(i) * q;
                //found = true;
                return;
            }
        }

        if(found==false)
        {
            product.add(p);
            this.quantity.add(q);
            this.price.add(price);
            totalPrice += price*q;
        }
    }

    public void removeAllProduct()
    {
		product.clear();
		quantity.clear();
		price.clear();
        totalPrice = 0.00;
        afterDisc = 0.00;
	}

    public void removeItem(String id)
    {
        //System.out.print(product.size());
        for(int i=0;i<product.size();i++)
        {
            System.out.println(i);
            if(id.equals(product.get(i).getPId()))
			{
				totalPrice -= price.get(i)*quantity.get(i);
			      product.remove(i);
			      quantity.remove(i);
			      price.remove(i);
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
            writer.println("\nThis receipt is automated");
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
            System.out.println("\nThis receipt is automated");
            System.out.println("-------------------------------------");
            System.out.printf("%22s\n" , "THANK YOU");
            System.out.println("-------------------------------------");

        }
    }

    public void checkout(String name , String custId,String address)
    {
        discount();
        payment = new Payment(cart_id, afterDisc);
        payment.pay();

        printReceipt(name);
        writeReceipt(name);

        ArrayList<Shipment>shipment=new ArrayList<>();
        if(payment.getStatus()){
            ReadShipping(shipment);
            String sub = shipment.get(shipment.size()-1).getShippingId();
            sub = sub.substring(1, sub.length());
            int id1 = Integer.parseInt(sub);
            String id = "B" + (id1+1);
            for(int i=0; i<product.size();i++){
                String pid = product.get(i).getPId();
                if(pid.charAt(0) == 'P')
                    shipment.add(new Shipment(id,address,custId,product.get(i).getPId(),product.get(i).getPName(),quantity.get(i)));
                else
                    continue;
            }
        try (PrintWriter writer = new PrintWriter(new FileWriter("shipping.csv"))) {
            for (Shipment s: shipment) {
               
                    writer.print(s.getShippingId() + ",");
                    writer.print(s.getCustId() + ",");
                    writer.print(s.getProductId() + ",");
                    writer.print(s.getProducName()+ ",");
                    writer.print(s.getQuantity() + ",");
                    writer.print(s.getAddress());
                    writer.println();
                
            }
        } catch (IOException e) {
            System.out.println("Error : File not found");
        }
    }
    }

    public void ReadShipping(ArrayList<Shipment>shipment)
    {
        try{
            Scanner in2 = new Scanner(new File("Shipping.csv"));
        in2.useDelimiter(",|\\n");
        String temp;
        String cid="" , pid="" , pname="" , sId="" , address = "";
        int qty = 0;
        while(in2.hasNext())
        {
            sId = in2.next();
            cid = in2.next();
            pid = in2.next();
            pname = in2.next();
            temp = in2.next();
            qty = Integer.parseInt(temp);
            address = in2.next();
            
            //System.out.println(sid + " " + pid + " " + pname + " " + price + " " + quantity + " " + date + " " + brand + " " + weight);
            shipment.add(new Shipment(sId , address,cid,pid,pname,qty));
        }
            in2.close();
    }catch(IOException e){
        System.out.println("File not found");
    }
}

}
