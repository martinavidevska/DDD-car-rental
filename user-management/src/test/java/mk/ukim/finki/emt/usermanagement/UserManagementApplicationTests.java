package mk.ukim.finki.emt.usermanagement;

import mk.ukim.finki.emt.usermanagement.domain.models.Role;
import mk.ukim.finki.emt.usermanagement.domain.models.User;
import mk.ukim.finki.emt.usermanagement.service.UserService;
import mk.ukim.finki.emt.usermanagement.service.forms.UserForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserManagementApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserService userService;

    List<User> initialUserList = new ArrayList<>();

    @Test
    public void testAddAndFetchUsers() {
        UserForm user1Form = new UserForm();
        user1Form.setUsername("johndoe");
        user1Form.setPassword("password123");
        user1Form.setName("John Doe");
        user1Form.setEmail("john.doe@example.com");
        user1Form.setPhoneNumber("1234567890");
        user1Form.setDriverLicenseNumber("D1234567");

        UserForm user2Form = new UserForm();
        user2Form.setUsername("janedoe");
        user2Form.setPassword("password456");
        user2Form.setName("Jane Doe");
        user2Form.setEmail("jane.doe@example.com");
        user2Form.setPhoneNumber("0987654321");
        user2Form.setDriverLicenseNumber("D7654321");

        // Add the users using the service
        userService.registerUser(user1Form);
        userService.registerUser(user2Form);

        // Re-fetch the user list
        initialUserList = userService.getAllUsers();

        // Ensure users were added
        Assertions.assertNotNull(initialUserList);
        Assertions.assertTrue(initialUserList.size() >= 2);

        // Validate that the users have the expected attributes
        User user1 = initialUserList.get(0);
        User user2 = initialUserList.get(1);

        Assertions.assertEquals("johndoe", user1.getUsername());
//        Assertions.assertEquals("John Doe", user1.getName());
//        Assertions.assertEquals("john.doe@example.com", user1.getEmail());
//
//        Assertions.assertEquals("janedoe", user2.getUsername());
//        Assertions.assertEquals("Jane Doe", user2.getName());
//        Assertions.assertEquals("jane.doe@example.com", user2.getEmail());
    }
}
