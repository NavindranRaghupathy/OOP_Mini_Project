import java.util.*;

class Cart{
    private String cart_id;
    private ArrayList<Product> product;
    private ArrayList<Integer> quantity;
    private ArrayList<Float> price;
    private double totalPrice=0 , afterDisc;
    private String date_created;

    public Cart(String cart_id ,float totalPrice , String date_created)
    {
        this.cart_id = cart_id;
        product = new ArrayList<>();
        this.totalPrice = totalPrice;
        this.date_created = date_created;
        quantity = new ArrayList<>();
        price = new ArrayList<>();
    }

    public void addItem(Product p , int quantity , float price)
    {
        product.add(p);
        this.quantity.add(quantity);
        this.price.add(price);
        totalPrice += price*quantity;
    }

    public void removeItem(Product p)
    {
        for(int i=0;i<product.size();i++)
        {
            if(product.get(i).equals(p))
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
            afterDisc = totalPrice - (totalPrice * 0.1);
        else if(totalPrice>200)
            afterDisc = totalPrice - (totalPrice * 0.2);
        else if(totalPrice>500)
            afterDisc = totalPrice - (totalPrice * 0.3);
    }



    public void printCart()
    {
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("Cart ID : " + cart_id);
        System.out.println("Date created : " + date_created);
        System.out.printf("%-10s %-10s %-10s %-10s %-10s \n" , "Items" , "Brand" , "Quantity" , "Price" , "Amount");
        for(int i=0;i<product.size();i++)
        {
            // System.out.printf("%-10s %-10s %-10d %-10.2f %-10.2f \n" , product.get(i).getPName() , product.get(i).getBrand() , quantity.get(i) , price.get(i) , ((quantity.get(i))*(price.get(i))));
        }
        discount();
        System.out.printf("%30s RM%2.2f \n" , "Total Price" , afterDisc);
        System.out.println("---------------------------------------------------------------------------------------------------");
    }

    public void checkout()
    {

    }
}