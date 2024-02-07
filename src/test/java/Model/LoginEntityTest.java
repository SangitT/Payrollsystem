package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginEntityTest {
    private LoginEntity instance;

    @BeforeEach
    public void setUp() {
        instance = new LoginEntity();
    }

    @Test
    public void testGetUserId() {
        int expectedUserId = 1; 
        instance.setUserId(expectedUserId);
        int result = instance.getUserId();
        assertEquals(expectedUserId, result);
    }

    @Test
    public void testSetUserId() {
        int userId = 2; 
        instance.setUserId(userId);
        int result = instance.getUserId();
        assertEquals(userId, result);
    }

    @Test
    public void testGetEmail() {
        String expectedEmail = "sample@example.com"; 
        instance.setEmail(expectedEmail);
        String result = instance.getEmail();
        assertEquals(expectedEmail, result);
    }

    @Test
    public void testSetEmail() {
        String email = "another@example.com"; 
        instance.setEmail(email);
        String result = instance.getEmail();
        assertEquals(email, result);
    }

    @Test
    public void testGetPassword() {
        String expectedPassword = "password123"; 
        instance.setPassword(expectedPassword);
        String result = instance.getPassword();
        assertEquals(expectedPassword, result);
    }

    @Test
    public void testSetPassword() {
        String password = "newpassword456";
        instance.setPassword(password);
        String result = instance.getPassword();
        assertEquals(password, result);
    }
}
