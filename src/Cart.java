import java.util.*;

class Cart{
    private String cart_id;
    private ArrayList<Product> product;
    private ArrayList<Integer> quantity;
    private ArrayList<Float> price;
    private double totalPrice , afterDisc;
    private String date_created;

    public Cart(String cart_id ,float totalPrice , String date_created)
    {
        this.cart_id = cart_id;
        product = new ArrayList<>();
        this.totalPrice = totalPrice;
        this.date_created = date_created;
    }

    public void addItem(Product p , int quantity , float price)
    {
        product.add(p);
        this.quantity.add(quantity);
        this.price.add(price);
        totalPrice += price;
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
                totalPrice -= price.get(i);
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

    }

    public void checkout()
    {

    }
}