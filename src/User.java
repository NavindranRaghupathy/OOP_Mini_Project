import java.util.ArrayList;
import java.util.Scanner;

abstract class User {
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

    public User() {

    }

    public String getName(){return name;}
    public String getID(){return id;}
    public String getEmail(){return email;}
    public String getUsername(){return username;}
    public String getPass(){return pass;}
    public String getAddress() {return null;}
    public String getPhoneNo() {return null;}
    public String getStoreName() {return null;}
    public boolean isAvailability(){return false;} //Kuganes
    public String getShippingStatus(){return null;} //Kuganes

    public void setName(String n){name=n;}
    public void setEmail(String e){email=e;}
    public void setUsername(String u){username=u;}
    public void setPass(String p){pass=p;}
    public void setAddress() {}
    public void setPhoneNo() {}
    public void setStoreName() {}
    public void setID(String id){this.id=id;}

    public void viewProduct(){}

    public void ReadServiceProduct(Scanner in){}
    public void ReadPhysicalProduct(Scanner in){}

    public void ReadCustPhysical(ArrayList<Physical_Goods> p){}
    public void ReadCustService(ArrayList<Services> s){}

    public void AgentFile(Scanner kv){}

    public void ReadShipping(){} //k
    public void AnotherFile(Scanner sc){}  //k
    public void ViewOrderForDelivery(){} //k
    public void ViewFile(){} //k
    public void UpdateOrder(Scanner kv){}//k

    public void SignUp(ArrayList<User> user , Scanner sc) {}

    public void Logout(ArrayList<User> user) {}

    public void updateProfile(Scanner sc , ArrayList<User> user){}

    public void addProduct(Scanner sc){}
    public void updateProduct(Scanner sc){}
    public void deleteProduct(Scanner sc){}

    public void viewOrder(){}
    public void checkout(Scanner in , Scanner sc){}

    public void updateProfile(Scanner sc , ArrayList<User> user,String fileName){}
}
