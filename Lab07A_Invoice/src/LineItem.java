public class LineItem {
    private Product product;
    private int quantity;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Stub method for calculating the total price of the line item
    public double getTotal() {
        return 0.0; // Placeholder
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // Stub method for printing line item details
    public void printDetails() {
        // Placeholder for future implementation
    }
}
