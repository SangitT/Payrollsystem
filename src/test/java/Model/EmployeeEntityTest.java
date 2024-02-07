package Model;

import java.sql.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeEntityTest {
    private EmployeeEntity instance;

    @BeforeEach
    public void setUp() {
        instance = new EmployeeEntity(null,null,null,null,null,null,null,null,null,null);
    }

    @Test
    public void testGetEmployeeid() {
        int expectedEmployeeId = 101; // Replace with a sample employee ID
        instance.setEmployeeid(expectedEmployeeId);
        int result = instance.getEmployeeid();
        assertEquals(expectedEmployeeId, result);
    }

    @Test
    public void testSetEmployeeid() {
        int employeeId = 102; // Replace with a sample employee ID
        instance.setEmployeeid(employeeId);
        int result = instance.getEmployeeid();
        assertEquals(employeeId, result);
    }

    @Test
    public void testGetFirstName() {
        String expectedFirstName = "John"; // Replace with a sample first name
        instance.setFirstName(expectedFirstName);
        String result = instance.getFirstName();
        assertEquals(expectedFirstName, result);
    }

    @Test
    public void testSetFirstName() {
        String firstName = "Alice"; // Replace with a sample first name
        instance.setFirstName(firstName);
        String result = instance.getFirstName();
        assertEquals(firstName, result);
    }

    @Test
    public void testGetLastName() {
        String expectedLastName = "Doe"; // Replace with a sample last name
        instance.setLastName(expectedLastName);
        String result = instance.getLastName();
        assertEquals(expectedLastName, result);
    }

    @Test
    public void testSetLastName() {
        String lastName = "Smith"; // Replace with a sample last name
        instance.setLastName(lastName);
        String result = instance.getLastName();
        assertEquals(lastName, result);
    }

    @Test
    public void testGetDateOfBirth() {
        Date expectedDateOfBirth = Date.valueOf("1990-01-15"); // Replace with a sample date
        instance.setDateOfBirth(expectedDateOfBirth);
        Date result = instance.getDateOfBirth();
        assertEquals(expectedDateOfBirth, result);
    }

    @Test
    public void testSetDateOfBirth() {
        Date dateOfBirth = Date.valueOf("1985-03-20"); // Replace with a sample date
        instance.setDateOfBirth(dateOfBirth);
        Date result = instance.getDateOfBirth();
        assertEquals(dateOfBirth, result);
    }

    
}
