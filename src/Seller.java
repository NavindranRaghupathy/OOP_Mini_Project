class Seller extends User {
    private String Store_name;

    public Seller(String id, String name, String email, String username, String pass, String Store_name) {
        super(id, Store_name, email, username, pass);
        this.Store_name = Store_name;
    }

    public String getStoreName() {
        return Store_name;
    }

    public void addProduct() {}

    public void updateProduct() {}
}