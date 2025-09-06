package tasks.store;

public class OrderItem {
    private String productName;
    private int quantity;
    private double price;
    private Category category;

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }
}
