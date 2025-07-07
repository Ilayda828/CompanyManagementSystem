// This class contains information about the sales employees along with some methods.
import java.util.ArrayList;

public class SalesEmployee extends RegularEmployee {
    private ArrayList<Product> sales;
    public static int numberOfSalesEmployees = 0;

    public SalesEmployee(int id, String firstName, String lastName, String gender, java.util.Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, java.util.Calendar hireDate, Department department, double performanceScore, ArrayList<Product> sales) throws Exception {
        super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hireDate, department, performanceScore);
        setSales(sales);
        numberOfSalesEmployees++;
    }

    public SalesEmployee(RegularEmployee regularEmployee, ArrayList<Product> sales) throws Exception {
        super(regularEmployee, regularEmployee.getPerformanceScore());
        setSales(sales);
        numberOfSalesEmployees++;
    }

    public ArrayList<Product> getSales() { return sales; }
    public void setSales(ArrayList<Product> sales) throws Exception { if (sales == null) throw new Exception("Sales list cannot be null."); this.sales = sales; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SalesEmployee \n");
        sb.append("\t\t\t").append(super.toString()).append("\n");
        sb.append("\t\t\t[");
        for (int i = 0; i < sales.size(); i++) {
            sb.append(sales.get(i).toString());
            if (i != sales.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}