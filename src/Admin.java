import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {

    private ArrayList<Customer> customers;
    private ArrayList<Seller> sellers;
    private ArrayList<Shipping_Agent> agents;
    private static boolean status = false;

    public Admin(String id, String name, String email, String username, String pass) {
        super(id, name, email, username, pass);
        customers = new ArrayList<>();
        sellers = new ArrayList<>();
        agents = new ArrayList<>();
        // loadUsers();
    }

    public void viewProfile()
    {
        System.out.println("\nID: " + getID());
        System.out.println("Name : " + getName());
        System.out.println("Email : " + getEmail());
        System.out.println("Username : " + getUsername());
        System.out.println("Password : " + getPhoneNo());
    }

    public void updateProfile(Scanner sc , ArrayList<User> user)
    {
        viewProfile();
        String ID, name , email , uname , pass ;
        int opt;
        do{
            System.out.println("\n1) ID \n2) Name \n3) Email \n4) Username \n5) Password");
                System.out.print("Please select an option : ");
                opt = sc.nextInt();
                sc.nextLine();


            switch(opt)
            {
                case 1 : System.out.print("\nID : ");
                        ID = sc.nextLine();
                        setID(ID);
                        break;
                case 2 : System.out.print("\nName : ");
                        name = sc.nextLine();
                        setName(name);
                        break;
                case 3 : System.out.print("\nEmail : ");
                        email = sc.nextLine();
                        setEmail(email);
                        break;
                case 4 : System.out.print("\nUsername : ");
                        uname = sc.nextLine();
                        setUsername(uname);
                        break;
                case 5 : System.out.print("\nPassword : ");
                        pass = sc.nextLine();
                        setPass(pass);
                        break;
                default : System.out.println("\nInvalid option selected!");
                        break;
            }
        
        }while(opt<1 | opt>5);

        for(int i=0;i<user.size();i++)
        {
            if(getID().equals(user.get(i).getID()))
            {
                user.set(i, this);
                break;
            }
                
        }
    }

    public void loadUsers() {
        customers = readCustomer("/workspaces/OOP_Mini_Project/src/customer.csv");
        sellers = readSeller("/workspaces/OOP_Mini_Project/src/Seller.csv");
        agents = readShippingAgent("/workspaces/OOP_Mini_Project/src/Shipping_Agent.csv");
    }

    public void saveUsers() {
        writeCustomer("customer.csv", customers);
        writeSeller("Seller.csv", sellers);
        writeShippingAgent("Shipping_Agent.csv", agents);
    }

    public void Logout(ArrayList<User> user) {

        System.out.println("\nLogged out successfully.");
        try (PrintWriter writer = new PrintWriter(new FileWriter("Admin.csv"))) {
            for (User u : user) {
                writer.print(u.getID() + ",");
                writer.print(u.getName() + ",");
                writer.print(u.getEmail() + ",");
                writer.print(u.getUsername() + ",");
                writer.print(u.getPass());
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void manageUsers(Scanner sc) {
        if(!status){
            loadUsers();
            status = true;
        }
        while (true) {
            System.out.println("\n1) Customer\n2) Seller\n3) Shipping Agent\n4) Exit");
            System.out.print("Select user: ");
            int opt = sc.nextInt();
            sc.nextLine();

            if (opt == 4) {
                return;
            }

            int opt2;

            switch (opt) {
                case 1:
                    System.out.println("\n1) Add\n2) Delete");
                    System.out.print("Please select an option: ");
                    opt2 = sc.nextInt();
                    sc.nextLine();
                    if (opt2 == 1) {
                        System.out.print("\nCustomer name: ");
                        String custName = sc.nextLine();
                        // System.out.print("Customer ID: ");
                        // String custId = sc.nextLine();
                        System.out.print("Customer email: ");
                        String custEmail = sc.nextLine();
                        System.out.print("Customer username: ");
                        String custUname = sc.nextLine();
                        System.out.print("Customer password: ");
                        String custPass = sc.nextLine();
                        System.out.print("Customer address: ");
                        String custAddress = sc.nextLine();
                        System.out.print("Customer phone: ");
                        String custPhone = sc.nextLine();

                        String sub = customers.get(customers.size()-1).getID();
                        sub = sub.substring(1, sub.length());
                        int id1 = Integer.parseInt(sub);
                        String custId = "A" + (id1+1);

                        customers.add(new Customer(custId, custName, custEmail, custUname, custPass, custAddress, custPhone));
                        System.out.println("Customer added successfully.");

                    } else if (opt2 == 2) {
                        displayCust();
                        System.out.print("\nEnter id of customer you want to delete: ");
                        String id = sc.nextLine();
                        customers.removeIf(customer -> customer.getID().equals(id));
                        System.out.println("\nCustomer removed successfully.");
                    }
                    saveUsers();
                    break;

                case 2:
                    System.out.println("\n1) Add\n2) Delete");
                    System.out.print("Please select an option: ");
                    opt2 = sc.nextInt();
                    sc.nextLine();
                    if (opt2 == 1) {
                        System.out.print("\nSeller name: ");
                        String sellerName = sc.nextLine();
                        // System.out.print("Seller ID: ");
                        // String sellerId = sc.nextLine();
                        System.out.print("Seller email: ");
                        String sellerEmail = sc.nextLine();
                        System.out.print("Seller username: ");
                        String sellerUname = sc.nextLine();
                        System.out.print("Seller password: ");
                        String sellerPass = sc.nextLine();
                        System.out.print("Store name: ");
                        String storeName = sc.nextLine();

                        String sub = sellers.get(sellers.size()-1).getID();
                        sub = sub.substring(1, sub.length());
                        int id1 = Integer.parseInt(sub);
                        String sellerId = "S" + (id1+1);

                        sellers.add(new Seller(sellerId, sellerName, sellerEmail, sellerUname, sellerPass, storeName));
                        System.out.println("Seller added successfully.");

                    } else if (opt2 == 2) {
                        displaySeller();
                        System.out.print("\nPlease enter id to remove the seller: ");
                        String id = sc.nextLine();
                        sellers.removeIf(seller -> seller.getID().equals(id));
                        System.out.println("\nSeller removed successfully.");
                    }
                    saveUsers();
                    break;

                case 3:
                    System.out.println("\n1) Add\n2) Delete");
                    System.out.print("Please select an option: ");
                    opt2 = sc.nextInt();
                    sc.nextLine();
                    if (opt2 == 1) {
                        // System.out.print("\nShipping agent ID: ");
                        // String agentID = sc.nextLine();
                        System.out.print("Shipping agent name: ");
                        String agentName = sc.nextLine();
                        System.out.print("Shipping agent email: ");
                        String agentEmail = sc.nextLine();
                        System.out.print("Shipping agent username: ");
                        String agentUname = sc.nextLine();
                        System.out.print("Shipping agent password: ");
                        String agentPass = sc.nextLine();
                        // System.out.println("Is it available? [true/false]: ");
                        // String userBool = sc.nextLine();
                        // boolean agentAvailable = Boolean.parseBoolean(userBool);

                        String sub = agents.get(agents.size()-1).getID();
                        int id1 = Integer.parseInt(sub) + 1;
                        String agentID = Integer.toString(id1);

                        agents.add(new Shipping_Agent(agentID, agentName, agentEmail, agentUname, agentPass, false));
                        System.out.println("Shipping agent added successfully.");

                    } else if (opt2 == 2) {
                        displayAgent();
                        System.out.print("\nPlease enter id to remove the shipping agent: ");
                        String id = sc.nextLine();
                        agents.removeIf(agent -> agent.getID().equals(id));
                        System.out.println("\nShipping agent removed successfully.");
                    }
                    saveUsers();
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private ArrayList<Customer> readCustomer(String fileName) {
        ArrayList<Customer> customers = new ArrayList<>();
        try (Scanner in = new Scanner(new File(fileName))) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] data = line.split(",");
                if (data.length == 7) {
                    String id = data[1];
                    String name = data[0];
                    String email = data[2];
                    String username = data[3];
                    String password = data[4];
                    String address = data[5];
                    String phone = data[6];
                    customers.add(new Customer(id, name, email, username, password, address, phone));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private void writeCustomer(String fileName, ArrayList<Customer> customers) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            for (Customer customer : customers) {
                writer.printf("%s,%s,%s,%s,%s,%s,%s%n",
                        customer.getName(),
                        customer.getID(),
                        customer.getEmail(),
                        customer.getUsername(),
                        customer.getPass(),
                        customer.getAddress(),
                        customer.getPhoneNo());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Seller> readSeller(String fileName) {
        ArrayList<Seller> sellers = new ArrayList<>();
        try (Scanner in = new Scanner(new File(fileName))) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] data = line.split(",");
                if (data.length == 6) {
                    String id = data[0];
                    String name = data[1];
                    String email = data[2];
                    String username = data[3];
                    String password = data[4];
                    String storeName = data[5];
                    sellers.add(new Seller(id, name, email, username, password, storeName));
                    //System.out.println(id + "   " + name + "    " + email + "   " + username + "    " + password + "    " + storeName);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sellers;
    }

    private void writeSeller(String fileName, ArrayList<Seller> sellers) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            for (Seller seller : sellers) {
                writer.printf("%s,%s,%s,%s,%s,%s%n",
                        seller.getID(),
                        seller.getName(),
                        seller.getEmail(),
                        seller.getUsername(),
                        seller.getPass(),
                        seller.getStoreName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Shipping_Agent> readShippingAgent(String fileName) {
        ArrayList<Shipping_Agent> agents = new ArrayList<>();
        try (Scanner in = new Scanner(new File(fileName))) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] data = line.split(",");
                if (data.length == 6) {
                    String id = data[0];
                    String name = data[1];
                    String email = data[2];
                    String username = data[3];
                    String password = data[4];
                    String a1 = data[5];
                    boolean available = Boolean.parseBoolean(a1);
                    agents.add(new Shipping_Agent(id, name, email, username, password, available));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return agents;
    }

    private void writeShippingAgent(String fileName, ArrayList<Shipping_Agent> agents) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            for (Shipping_Agent agent : agents) {
                writer.printf("%s,%s,%s,%s,%s,%s%n",
                        agent.getID(),
                        agent.getName(),
                        agent.getEmail(),
                        agent.getUsername(),
                        agent.getPass(),
                        agent.isAvailability());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void displayCust()
    {
        System.out.printf("\n%-4s %-15s %-25s %-20s\n" , "ID" , "Name" , "Email" , "Address");
        for(Customer c : customers)
        {
            System.out.printf("%-4s %-15s %-25s %-20s\n" , c.getID() , c.getName() , c.getEmail() , c.getAddress());
        }
    }

    public void displaySeller()
    {
        System.out.printf("\n%-4s %-15s %-25s %-20s\n" , "ID" , "Name" , "Email" , "Store");
        for(Seller s : sellers)
        {
            System.out.printf("%-4s %-15s %-25s %-20s\n" , s.getID() , s.getName() , s.getEmail() , s.getStoreName());
        }
    }

    public void displayAgent()
    {
        System.out.printf("\n%-4s %-15s %-25s %-10s\n" , "ID" , "Name" , "Email" , "Availability");
        for(Shipping_Agent s : agents)
        {
            System.out.printf("%-4s %-15s %-25s %-20s\n" ,s.getID() , s.getName() , s.getEmail() , s.isAvailability());
        }
    }
}
