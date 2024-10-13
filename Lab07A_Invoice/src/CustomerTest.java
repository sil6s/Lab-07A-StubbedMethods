import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void testSetAndGetCustomerName() {
        Customer customer = new Customer("John Doe", "123 Main St");
        customer.setName("Jane Doe");
        assertEquals("Jane Doe", customer.getName());
    }

    @Test
    public void testSetAndGetCustomerAddress() {
        Customer customer = new Customer("John Doe", "123 Main St");
        customer.setAddress("456 Elm St");
        assertEquals("456 Elm St", customer.getAddress());
    }
}
