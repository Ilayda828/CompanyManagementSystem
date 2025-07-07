// This class contains information about the managers along with some methods.
import java.util.ArrayList;
import java.util.Calendar;

public class Manager extends Employee {
    private ArrayList<RegularEmployee> regularEmployees;
    private double bonusBudget;

    public Manager(int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, Calendar hireDate, Department department, double bonusBudget) throws Exception {
        super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hireDate, department);
        setRegularEmployees(new ArrayList<>());
        setBonusBudget(bonusBudget);
    }

    public Manager(Employee employee, double bonusBudget) throws Exception {
        super(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getGender(), employee.getBirthDate(), employee.getMaritalStatus(), employee.getHasDriverLicence(), employee.getSalary(), employee.getHireDate(), employee.getDepartment());
        setRegularEmployees(new ArrayList<>());
        setBonusBudget(bonusBudget);
    }

    public void addEmployee(RegularEmployee e) { regularEmployees.add(e); }
    public void removeEmployee(RegularEmployee e) { regularEmployees.remove(e); }
    public void distributeBonusBudget() {
        double total = 0;
        for (RegularEmployee e : regularEmployees) total += e.getSalary() * e.getPerformanceScore();
        if (total == 0) return;
        double unit = bonusBudget / total;
        for (RegularEmployee e : regularEmployees) {
            double bonus = unit * e.getSalary() * e.getPerformanceScore();
            try { e.setBonus(Math.round(bonus * 100.0) / 100.0); } catch (Exception ex) {}
        }
    }
    public ArrayList<RegularEmployee> getRegularEmployees() { return regularEmployees; }
    public void setRegularEmployees(ArrayList<RegularEmployee> regularEmployees) throws Exception { if (regularEmployees == null) throw new Exception("Regular employees list cannot be null."); this.regularEmployees = regularEmployees; }
    public double getBonusBudget() { return bonusBudget; }
    public void setBonusBudget(double bonusBudget) throws Exception { if (bonusBudget < 0) throw new Exception("Bonus budget must be non-negative."); this.bonusBudget = bonusBudget; }

    @Override
    public String toString() {
        return "Manager [id: " + getId() + ", " + getFirstName() + " " + getLastName() + "\n\t\t# of Employees: " + regularEmployees.size() + "]";
    }
}