import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("John Doe", "12345", "john.doe@example.com");
    }

    @Test
    public void testGetUserName() {
        assertEquals("John Doe", user.getUserName());
    }

    @Test
    public void testGetUserID() {
        assertEquals("12345", user.getUserID());
    }

    @Test
    public void testGetUserEmail() {
        assertEquals("john.doe@example.com", user.getUserEmail());
    }

    @Test
    public void testSetUserName() {
        user.setUserName("Jane Doe");
        assertEquals("Jane Doe", user.getUserName());
    }

    @Test
    public void testSetUserID() {
        user.setUserID("54321");
        assertEquals("54321", user.getUserID());
    }

    @Test
    public void testSetUserEmail() {
        user.setUserEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", user.getUserEmail());
    }
}