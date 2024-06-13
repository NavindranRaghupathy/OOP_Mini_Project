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

    public void viewProduct() {}

    public void Login() {}

    public void Logout() {}

    public void updateProfile() {}
}

class Customer extends User {
    private String address;
    private int phone_no;

    public void Customer(String address, int phone_no) {
        this.address = address;
        this.phone_no = phone_no;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNo() {
        return phone_no;
    }

    public void viewOrder() {}

    public void buyProducts() {}

    public void checkout() {}
}

class Seller extends User {
    private String Store_name;

    public Seller(String Store_name) {
        this.Store_name = Store_name;
    }

    public String getStoreName() {
        return Store_name;
    }

    public void addProduct() {}

    public void updateProduct() {}
}

class Admin extends User {
    private Seller seller;

    public Admin(Seller seller) {
        this.seller = seller;
    }

    public void manageOrder() {}

    public void manageUsers() {}
}