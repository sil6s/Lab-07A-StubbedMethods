import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private Customer customer;
    private List<LineItem> lineItems;

    public Invoice(Customer customer) {
        this.customer = customer;
        this.lineItems = new ArrayList<>();
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
    }

    // Stub method for calculating the total amount
    public double getTotalAmountDue() {
        return 0.0; // Placeholder for future implementation
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    // Stub methods to be filled later
    public void printInvoice() {
        // Placeholder for future implementation
    }
}
