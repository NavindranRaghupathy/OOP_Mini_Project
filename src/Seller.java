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