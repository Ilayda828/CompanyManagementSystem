// This class contains information about the department.
public class Department {
    private int departmentId;
    private String departmentName;

    public Department(int departmentId, String departmentName) throws Exception {
        setDepartmentId(departmentId);
        setDepartmentName(departmentName);
    }

    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) throws Exception { if (departmentId <= 0) throw new Exception("Department id must be positive."); this.departmentId = departmentId; }
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) throws Exception { if (departmentName == null || departmentName.trim().length() < 3) throw new Exception("Department name must be at least 3 characters."); this.departmentName = departmentName; }

    @Override
    public String toString() {
        return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + "]";
    }
}