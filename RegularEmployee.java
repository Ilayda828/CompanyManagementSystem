// This class contains information about the regular employees along with some methods.
import java.util.Calendar;

public class RegularEmployee extends Employee {
    private double performanceScore;
    private double bonus;

    public RegularEmployee(int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, Calendar hireDate, Department department, double performanceScore) throws Exception {
        super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hireDate, department);
        setPerformanceScore(performanceScore);
        setBonus(0);
    }

    public RegularEmployee(Employee employee, double performanceScore) throws Exception {
        super(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getGender(), employee.getBirthDate(), employee.getMaritalStatus(), employee.getHasDriverLicence(), employee.getSalary(), employee.getHireDate(), employee.getDepartment());
        setPerformanceScore(performanceScore);
        setBonus(0);
    }

    public double getPerformanceScore() { return performanceScore; }
    public void setPerformanceScore(double performanceScore) throws Exception { if (performanceScore < 0) throw new Exception("Performance score must be non-negative."); this.performanceScore = performanceScore; }
    public double getBonus() { return bonus; }
    public void setBonus(double bonus) throws Exception { if (bonus < 0) throw new Exception("Bonus must be non-negative."); this.bonus = bonus; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(" \n");
        sb.append("\t\t\t").append(super.toString()).append("\n");
        sb.append("\t\t\tRegularEmployee Info [performanceScore=").append(String.format("%.1f", performanceScore)).append(", bonus=").append(String.format("%.2f", bonus)).append("]");
        return sb.toString();
    }
}