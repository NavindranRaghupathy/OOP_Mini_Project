import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Payment {
    private String payment_id;
    private double amount;
    private boolean status;
    private LocalDate transaction_date;
    private String credit_card_no , cvv , expiry;


    // Constructor to initialize Payment object with payment_id and amount
    public Payment(String payment_id, double amount) {
        this.payment_id = payment_id;
        this.amount = amount;
        status = false;
        transaction_date = LocalDate.now();
    }

    // Setter method for payment_id
    public void setPaymentId(String payment_id) {
        this.payment_id = payment_id;
    }

    // Setter method for amount
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Setter method for status
    public void setStatus(boolean status) {
        this.status = status;
    }


    // Getter method for payment_id
    public String getPaymentId() {
        return payment_id;
    }

    // Getter method for amount
    public double getAmount() {
        return amount;
    }

    // Getter method for status
    public boolean getStatus() {
        return status;
    }

    // Method to get the formatted transaction date
    public String getDate()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = transaction_date.format(formatter);
        return date;
    }

    // Method to display the total amount in a dialog
    public void displayAmount()
    {
        JOptionPane.showMessageDialog(null, "Total Amount: RM " + String.format("%.2f", amount));
    }


    // Method to handle the payment process
    public void pay() {
        // Implement payment logic

        displayAmount();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create text fields for card number, expiry date, and CVV
        JTextField cardNumberField = new JTextField(16);
        JTextField expiryField = new JTextField(5);
        JTextField cvvField = new JTextField(3);

        // Add the text fields to the panel with labels
        panel.add(new JLabel("Card Number:"));
        panel.add(cardNumberField);
        panel.add(Box.createVerticalStrut(10)); // Space between fields
        panel.add(new JLabel("Expiry date (mm/yy):"));
        panel.add(expiryField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("CVV:"));
        panel.add(cvvField);

        // Show the dialog with the panel
        int result = JOptionPane.showConfirmDialog(null, panel, "Enter Credit Card Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Check if the user clicked OK
        if (result == JOptionPane.OK_OPTION) {
            credit_card_no = cardNumberField.getText();
            expiry = expiryField.getText();
            cvv = cvvField.getText();

            // System.out.println("Card Number: " + credit_card_no);
            // System.out.println("Expiry Date: " + expiry);
            // System.out.println("CVV: " + cvv);
            status = true;
        } else {
            // Set payment status to false and display cancellation message
            status = false;
            JOptionPane.showMessageDialog(null, "You canceled the payment");
        }
    }

    // Method to cancel the payment
    public void cancel_payment() {
        // Implement cancel payment logic
        this.status = false;
    }
}
