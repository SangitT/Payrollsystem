
package Model;



public class EmployeeEntity {
    
    private int employeeid;
    private String firstName;
    private String lastName;
    private java.sql.Date dateOfBirth;
    private String genderType;
    private String contactNumber;
    private String address;
    private java.sql.Date joiningDate;
    private String jobTitle;
    private String employeeType;
    private String emailAddress;

    public EmployeeEntity(String firstName, String lastName, java.sql.Date dob, String gender, String contactNumber, String address, java.sql.Date startDate, String jobTitle,String employeeType, String email) {
        this.employeeid = -1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dob;
        this.genderType = gender;
        this.contactNumber = contactNumber;
        this.address = address;
        this.joiningDate = startDate;
        this.jobTitle = jobTitle;
        this.employeeType = employeeType;
        this.emailAddress = email;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    

    public String getGenderType() {
        return genderType;
    }

    public void setGenderType(String genderType) {
        this.genderType = genderType;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.sql.Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(java.sql.Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public java.sql.Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(java.sql.Date joiningDate) {
        this.joiningDate = joiningDate;
    }

   

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" + "employeeid=" + employeeid + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", genderType=" + genderType + ", contactNumber=" + contactNumber + ", address=" + address + ", joiningDate=" + joiningDate + ", jobTitle=" + jobTitle + ", employeeType=" + employeeType + ", emailAddress=" + emailAddress + '}';
    }

    
    
}
