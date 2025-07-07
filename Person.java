// This class contains information about the people along with some methods.
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private byte gender;
    private java.util.Calendar birthDate;
    private byte maritalStatus;
    private boolean hasDriverLicence;

    public Person(int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence) throws Exception {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setBirthDate(birthDate);
        setMaritalStatus(maritalStatus);
        setHasDriverLicence(hasDriverLicence);
    }

    public int getId() { return id; }
    public void setId(int id) throws Exception { if (id <= 0) throw new Exception("ID must be positive."); this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) throws Exception { if (firstName == null || firstName.trim().length() < 3) throw new Exception("First name must be at least 3 characters."); this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) throws Exception { if (lastName == null || lastName.trim().length() < 3) throw new Exception("Last name must be at least 3 characters."); this.lastName = lastName; }
    public String getGender() { if (gender == 1) return "Woman"; else if (gender == 2) return "Man"; else return "Unknown"; }
    public void setGender(String gender) throws Exception { if (gender == null) throw new Exception("Gender cannot be null."); if (gender.equalsIgnoreCase("Woman")) { this.gender = 1; } else if (gender.equalsIgnoreCase("Man")) { this.gender = 2; } else { throw new Exception("Gender must be 'Man' or 'Woman'."); } }
    public java.util.Calendar getBirthDate() { return birthDate; }
    public void setBirthDate(java.util.Calendar birthDate) throws Exception { if (birthDate == null) throw new Exception("Birth date cannot be null."); this.birthDate = birthDate; }
    public String getMaritalStatus() { if (maritalStatus == 1) return "Single"; else if (maritalStatus == 2) return "Married"; else return "Unknown"; }
    public void setMaritalStatus(String maritalStatus) throws Exception { if (maritalStatus == null) throw new Exception("Marital status cannot be null."); if (maritalStatus.equalsIgnoreCase("Single")) { this.maritalStatus = 1; } else if (maritalStatus.equalsIgnoreCase("Married")) { this.maritalStatus = 2; } else { throw new Exception("Marital status must be 'Single' or 'Married'."); } }
    public String getHasDriverLicence() { return (hasDriverLicence) ? "Yes" : "No"; }
    public void setHasDriverLicence(String hasDriverLicence) throws Exception { if (hasDriverLicence == null) throw new Exception("Driver licence info cannot be null."); if (hasDriverLicence.equalsIgnoreCase("Yes")) { this.hasDriverLicence = true; } else if (hasDriverLicence.equalsIgnoreCase("No")) { this.hasDriverLicence = false; } else { throw new Exception("Driver licence info must be 'Yes' or 'No'."); } }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + getGender() + ", birthDate=" + sdf.format(birthDate.getTime()) + ", maritalStatus=" + getMaritalStatus() + ", hasDriverLicence=" + getHasDriverLicence() + "]";
    }
}