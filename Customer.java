// This class contains information about the customer.
import java.util.ArrayList;
import java.util.Calendar;

public class Customer extends Person {
    private ArrayList<Product> products;

    public Customer(int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence, ArrayList<Product> products) throws Exception {
        super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence);
        setProducts(products);
    }

    public Customer(Person person, ArrayList<Product> products) throws Exception {
        super(person.getId(), person.getFirstName(), person.getLastName(), person.getGender(), person.getBirthDate(), person.getMaritalStatus(), person.getHasDriverLicence());
        setProducts(products);
    }

    public void setProducts(ArrayList<Product> products) throws Exception {
        if (products == null) throw new Exception("Products list cannot be null.");
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer [id: ").append(getId()).append(" products=[");
        for (int i = 0; i < products.size(); i++) {
            sb.append(products.get(i).toString());
            if (i != products.size() - 1) sb.append(", ");
        }
        sb.append("]]");
        return sb.toString();
    }
}