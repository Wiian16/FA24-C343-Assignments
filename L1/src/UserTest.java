import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @org.junit.jupiter.api.Test
    void getName() {
        User user = new User("User1", 1);
        assertEquals("User1", user.getName());
        user = new User("", null);
        assertEquals("", user.getName());
    }

    @org.junit.jupiter.api.Test
    void getAge() {
        User user = new User("User1", 1);
        assertEquals(1, user.getAge());
        user = new User("User1", null);
        assertNull(user.getAge());
    }
}