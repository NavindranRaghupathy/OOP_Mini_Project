class Payment {
    private String payment_id;
    private float amount;
    private boolean status;
    private String transaction_date;


    public Payment(String payment_id, float amount, boolean status, String transaction_date) {
        this.payment_id = payment_id;
        this.amount = amount;
        this.status = status;
        this.transaction_date = transaction_date;
    }

    // Setters
    public void setPaymentId(String payment_id) {
        this.payment_id = payment_id;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setDate(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    // Getters
    public String getPaymentId() {
        return payment_id;
    }

    public float getAmount() {
        return amount;
    }

    public boolean getStatus() {
        return status;
    }

    public String getDate() {
        return transaction_date;
    }

    // Methods
    public void pay() {
        // Implement payment logic
        this.status = true;
    }

    public void cancel_payment() {
        // Implement cancel payment logic
        this.status = false;
    }
}