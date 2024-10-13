import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceTest {

    @Test
    public void testAddLineItem() {
        Customer customer = new Customer("John Doe", "123 Main St");
        Invoice invoice = new Invoice(customer);

        Product product = new Product("Widget", 9.99);
        LineItem lineItem = new LineItem(product, 5);

        invoice.addLineItem(lineItem);

        assertEquals(1, invoice.getLineItems().size());
    }

    @Test
    public void testGetTotalAmountDue() {
        Customer customer = new Customer("John Doe", "123 Main St");
        Invoice invoice = new Invoice(customer);

        Product product1 = new Product("Widget", 9.99);
        LineItem lineItem1 = new LineItem(product1, 5);
        Product product2 = new Product("Gadget", 19.99);
        LineItem lineItem2 = new LineItem(product2, 2);

        invoice.addLineItem(lineItem1);
        invoice.addLineItem(lineItem2);

        // Update this after implementing the method
        assertEquals(0.0, invoice.getTotalAmountDue());
    }
}
