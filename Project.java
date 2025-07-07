// This class contains information about the projects along with some methods.
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Project {
    private String projectName;
    private java.util.Calendar startDate;
    private boolean state;

    public Project(String projectName, Calendar startDate, String state) throws Exception {
        setProjectName(projectName);
        setStartDate(startDate);
        setState(state);
    }

    public void setState(String state) throws Exception {
        if (state == null) throw new Exception("State cannot be null.");
        if (state.equalsIgnoreCase("Open")) {
            this.state = true;
        } else if (state.equalsIgnoreCase("Close")) {
            this.state = false;
        } else {
            throw new Exception("State must be 'Open' or 'Close'.");
        }
    }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) throws Exception { if (projectName == null || projectName.trim().length() < 3) throw new Exception("Project name must be at least 3 characters."); this.projectName = projectName; }
    public java.util.Calendar getStartDate() { return startDate; }
    public void setStartDate(java.util.Calendar startDate) throws Exception { if (startDate == null) throw new Exception("Start date cannot be null."); this.startDate = startDate; }
    public boolean isState() { return state; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        return "Project [projectName=" + projectName + ", startDate=" + sdf.format(startDate.getTime()) + ", state=" + state + "]";
    }
}