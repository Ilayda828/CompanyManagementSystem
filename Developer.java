// This class contains information about the developer along with some methods.
import java.util.ArrayList;

public class Developer extends RegularEmployee {
    private ArrayList<Project> projects;
    public static int numberOfDevelopers = 0;

    public Developer(int id, String firstName, String lastName, String gender, java.util.Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, java.util.Calendar hireDate, Department department, double performanceScore, ArrayList<Project> projects) throws Exception {
        super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hireDate, department, performanceScore);
        setProjects(projects);
        numberOfDevelopers++;
    }

    public Developer(RegularEmployee regularEmployee, ArrayList<Project> projects) throws Exception {
        super(regularEmployee, regularEmployee.getPerformanceScore());
        setProjects(projects);
        numberOfDevelopers++;
    }

    public ArrayList<Project> getProjects() { return projects; }
    public void setProjects(ArrayList<Project> projects) throws Exception { if (projects == null) throw new Exception("Projects list cannot be null."); this.projects = projects; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Developer \n");
        sb.append("\t\t\t").append(super.toString()).append("\n");
        sb.append("\t\t\t[");
        for (int i = 0; i < projects.size(); i++) {
            sb.append(projects.get(i).toString());
            if (i != projects.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}