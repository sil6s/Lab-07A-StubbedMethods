import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InvoiceApp {
    private JFrame frame;
    private JTextField customerNameField, customerAddressField, productNameField, quantityField, priceField;
    private JTextArea outputArea;
    private Invoice invoice;

    public InvoiceApp() {
        frame = new JFrame("Silas's Invoice System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 650);

        invoice = new Invoice(new Customer("", ""));

        createUI();

        frame.setVisible(true);
    }

    private void createUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Customer Info Panel
        JPanel customerPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        customerPanel.add(new JLabel("Customer Name:"));
        customerNameField = new JTextField(20);
        customerPanel.add(customerNameField);
        customerPanel.add(new JLabel("Customer Address:"));
        customerAddressField = new JTextField(20);
        customerPanel.add(customerAddressField);

        // Product Info Panel
        JPanel productPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        productPanel.add(new JLabel("Product Name:"));
        productNameField = new JTextField(20);
        productPanel.add(productNameField);
        productPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField(10);
        productPanel.add(quantityField);
        productPanel.add(new JLabel("Unit Price:"));
        priceField = new JTextField(10);
        productPanel.add(priceField);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("Add Line Item");
        JButton calculateButton = new JButton("Calculate Total");
        buttonPanel.add(addButton);
        buttonPanel.add(calculateButton);

        // Output Area with margins
        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);
        outputArea.setMargin(new Insets(10, 10, 10, 10)); // Add internal margins
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10), // Add external margins
                scrollPane.getBorder())); // Keep the default border

        // Add components to main panel
        mainPanel.add(customerPanel, BorderLayout.NORTH);
        mainPanel.add(productPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel and output area to frame
        frame.setLayout(new BorderLayout(10, 10));
        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Button Listeners
        addButton.addActionListener(e -> addLineItem());
        calculateButton.addActionListener(e -> calculateTotal());
    }

    private void addLineItem() {
        try {
            String productName = productNameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double unitPrice = Double.parseDouble(priceField.getText());

            if (productName.isEmpty() || quantity <= 0 || unitPrice <= 0) {
                JOptionPane.showMessageDialog(frame, "Please enter valid product details.");
                return;
            }

            Product product = new Product(productName, unitPrice);
            LineItem item = new LineItem(product, quantity);
            invoice.addLineItem(item);

            outputArea.append(String.format("Added: %s | Quantity: %d | Unit Price: $%.2f | Total: $%.2f%n",
                    productName, quantity, unitPrice, item.getTotal()));

            // Clear input fields
            productNameField.setText("");
            quantityField.setText("");
            priceField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers for Quantity and Unit Price.");
        }
    }

    private void calculateTotal() {
        String customerName = customerNameField.getText();
        String customerAddress = customerAddressField.getText();
        invoice.setCustomer(new Customer(customerName, customerAddress));

        StringBuilder sb = new StringBuilder();
        sb.append("INVOICE\n");
        sb.append(customerName).append("\n");
        sb.append(customerAddress).append("\n\n");
        sb.append("Item\t\tQty\tPrice\tTotal\n");
        sb.append("----------------------------------------\n");

        for (LineItem item : invoice.getLineItems()) {
            sb.append(String.format("%-20s %d\t$%.2f\t$%.2f%n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getProduct().getUnitPrice(),
                    item.getTotal()));
        }

        sb.append("----------------------------------------\n");
        sb.append(String.format("AMOUNT DUE: $%.2f%n", invoice.getTotalAmountDue()));

        outputArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InvoiceApp::new);
    }
}