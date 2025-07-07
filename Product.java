// This class contains information about the products along with some methods.
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Product {
    private String productName;
    private java.util.Calendar saleDate;
    private double price;

    public Product(String productName, Calendar saleDate, double price) throws Exception {
        setProductName(productName);
        setSaleDate(saleDate);
        setPrice(price);
    }

    public String getProductName() { return productName; }
    public void setProductName(String productName) throws Exception { if (productName == null || productName.trim().length() < 3) throw new Exception("Product name must be at least 3 characters."); this.productName = productName; }
    public java.util.Calendar getSaleDate() { return saleDate; }
    public void setSaleDate(java.util.Calendar saleDate) throws Exception { if (saleDate == null) throw new Exception("Sale date cannot be null."); this.saleDate = saleDate; }
    public double getPrice() { return price; }
    public void setPrice(double price) throws Exception { if (price < 0) throw new Exception("Price must be non-negative."); this.price = price; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        return "Product [productName=" + productName + ", transactionDate=" + sdf.format(saleDate.getTime()) + ", price=" + String.format("%.1f", price) + "]";
    }
}