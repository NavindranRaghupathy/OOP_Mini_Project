class Customer extends User {
    private String address;
    private int phone_no;

    public Customer(String id, String name, String email, String username, String pass, String address, int phone_no) {
        super(id, name, email, username, pass);
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