package Classes;

public class Order {
    private int id;
    private String details;
    private double totalPrice;
    private String status;

    public Order(int id, String details, double totalPrice, String status) {
        this.id = id;
        this.details = details;
        this.totalPrice = totalPrice;
        this.status =   status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
