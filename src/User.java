import java.util.ArrayList;
import java.util.Scanner;

class User {
    private String id;
    private String name;
    private String email;
    private String username;
    private String pass;

    public User(String id, String name, String email, String username, String pass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.pass = pass;
    }

    public String getName(){return name;}
    public String getID(){return id;}
    public String getEmail(){return email;}
    public String getUsername(){return username;}
    public String getPass(){return pass;}
    public String getAddress() {return null;}
    public String getPhoneNo() {return null;}
    public String getStoreName() {return null;}

    public void viewProduct() {}

    public void ReadServiceProduct(Scanner in){}
    public void ReadPhysicalProduct(Scanner in){}

    public void ReadCustPhysical(ArrayList<Physical_Goods> p){}
    public void ReadCustService(ArrayList<Services> s){}

    public void SignUp(ArrayList<User> user , Scanner sc) {}

    public void Logout() {}

    public void updateProfile() {}

    public void addProduct(Scanner sc){}
    public void updateProduct(Scanner sc){}
    public void deleteProduct(Scanner sc){}

    public void viewOrder(){}
    public void buyProducts(Scanner sc){}
    public void checkout(){}
}
