// This class contains information about the employees along with some methods.
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Employee extends Person {
    private double salary;
    private java.util.Calendar hireDate;
    private Department department;
    public static int numberOfEmployees = 0;

    public Employee(int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, Calendar hireDate, Department department) throws Exception {
        super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence);
        setSalary(salary);
        setHireDate(hireDate);
        setDepartment(department);
        numberOfEmployees++;
    }

    public Employee(Person person, double salary, Calendar hireDate, Department department) throws Exception {
        super(person.getId(), person.getFirstName(), person.getLastName(), person.getGender(), person.getBirthDate(), person.getMaritalStatus(), person.getHasDriverLicence());
        setSalary(salary);
        setHireDate(hireDate);
        setDepartment(department);
        numberOfEmployees++;
    }

    public double getSalary() { return salary; }
    public void setSalary(double salary) throws Exception { if (salary <= 0) throw new Exception("Salary must be positive."); this.salary = salary; }
    public java.util.Calendar getHireDate() { return hireDate; }
    public void setHireDate(java.util.Calendar hireDate) throws Exception { if (hireDate == null) throw new Exception("Hire date cannot be null."); this.hireDate = hireDate; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) throws Exception { if (department == null) throw new Exception("Department cannot be null."); this.department = department; }

    public double raiseSalary(double percent) { salary += salary * percent; return salary; }
    public double raiseSalary(int amount) { salary += amount; return salary; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        return "Employee Info [salary=" + String.format("%.1f", salary) + ", hireDate=" + sdf.format(hireDate.getTime()) + "]";
    }
}