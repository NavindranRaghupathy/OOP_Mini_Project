import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {

    private ArrayList<Customer> customers;
    private ArrayList<Seller> sellers;
    private ArrayList<Shipping_Agent> agents;

    public Admin(String id, String name, String email, String username, String pass) {
        super(id, name, email, username, pass);
        customers = new ArrayList<>();
        sellers = new ArrayList<>();
        agents = new ArrayList<>();
        loadUsers();
    }

    public void loadUsers() {
        customers = readCustomer("src\\customer.csv");
        sellers = readSeller("src\\Seller.csv");
        //agents = readShippingAgent("shipping.csv");
    }

    public void saveUsers() {
        writeCustomer("src\\customer.csv", customers);
        writeSeller("src\\Seller.csv", sellers);
        //writeShippingAgent("shipping.csv", agents);
    }

    public void Login(Scanner sc) {
        System.out.print("Username: ");
        String uname = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        // Dummy credentials for Admin (should be replaced with actual validation logic)
        if (uname.equals("admin") && pass.equals("admin123")) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password!");
            System.exit(0);
        }
    }

    public void Logout() {
        System.out.println("Logged out successfully.");
        System.exit(0);
    }

    public void manageUsers(Scanner sc) {
        while (true) {
            System.out.println("1) Customer\n2) Seller\n3) Shipping Agent\n4) Logout");
            System.out.print("Select user: ");
            int opt = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (opt == 4) {
                Logout();
            }

            int opt2;

            switch (opt) {
                case 1:
                    System.out.println("1) Add\n2) Delete");
                    System.out.print("Please select an option: ");
                    opt2 = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    if (opt2 == 1) {
                        System.out.print("Customer name: ");
                        String custName = sc.nextLine();
                        System.out.print("Customer ID: ");
                        String custId = sc.nextLine();
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

                        customers.add(new Customer(custId, custName, custEmail, custUname, custPass, custAddress, custPhone));
                        System.out.println("Customer added successfully.");
                    } else if (opt2 == 2) {
                        System.out.println("Enter id of customer you want to delete: ");
                        String id = sc.nextLine();
                        customers.removeIf(customer -> customer.getID().equals(id));
                        System.out.println("Customer removed successfully.");
                    }
                    saveUsers();
                    break;

                case 2:
                    System.out.println("1) Add\n2) Delete");
                    System.out.print("Please select an option: ");
                    opt2 = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    if (opt2 == 1) {
                        System.out.print("Seller name: ");
                        String sellerName = sc.nextLine();
                        System.out.print("Seller ID: ");
                        String sellerId = sc.nextLine();
                        System.out.print("Seller email: ");
                        String sellerEmail = sc.nextLine();
                        System.out.print("Seller username: ");
                        String sellerUname = sc.nextLine();
                        System.out.print("Seller password: ");
                        String sellerPass = sc.nextLine();
                        System.out.print("Store name: ");
                        String storeName = sc.nextLine();

                        sellers.add(new Seller(sellerId, sellerName, sellerEmail, sellerUname, sellerPass, storeName));
                        System.out.println("Seller added successfully.");
                    } else if (opt2 == 2) {
                        System.out.print("Please enter id to remove the seller: ");
                        String id = sc.nextLine();
                        sellers.removeIf(seller -> seller.getID().equals(id));
                        System.out.println("Seller removed successfully.");
                    }
                    saveUsers();
                    break;

                case 3:
                    System.out.println("1) Add\n2) Delete");
                    System.out.print("Please select an option: ");
                    opt2 = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    if (opt2 == 1) {
                        System.out.print("Shipping agent name: ");
                        String agentName = sc.nextLine();
                        System.out.print("Shipping agent ID: ");
                        String agentId = sc.nextLine();
                        System.out.print("Shipping agent email: ");
                        String agentEmail = sc.nextLine();
                        System.out.print("Shipping agent username: ");
                        String agentUname = sc.nextLine();
                        System.out.print("Shipping agent password: ");
                        String agentPass = sc.nextLine();

                        agents.add(new Shipping_Agent(agentId, agentName, agentEmail, agentUname, agentPass, false));
                        System.out.println("Shipping agent added successfully.");
                    } else if (opt2 == 2) {
                        System.out.print("Please enter id to remove the shipping agent: ");
                        String id = sc.nextLine();
                        agents.removeIf(agent -> agent.getID().equals(id));
                        System.out.println("Shipping agent removed successfully.");
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
                        customer.getID(),
                        customer.getName(),
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
                    String id = data[1];
                    String name = data[0];
                    String email = data[2];
                    String username = data[3];
                    String password = data[4];
                    String storeName = data[5];
                    sellers.add(new Seller(id, name, email, username, password, storeName));
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
                if (data.length == 5) {
                    String id = data[1];
                    String name = data[0];
                    String email = data[2];
                    String username = data[3];
                    String password = data[4];
                    agents.add(new Shipping_Agent(id, name, email, username, password, false));
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
                writer.printf("%s,%s,%s,%s,%s%n",
                        agent.getID(),
                        agent.getName(),
                        agent.getEmail(),
                        agent.getUsername(),
                        agent.getPass());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
