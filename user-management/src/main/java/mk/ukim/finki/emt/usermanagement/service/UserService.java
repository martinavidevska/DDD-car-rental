package mk.ukim.finki.emt.usermanagement.service;

import mk.ukim.finki.emt.usermanagement.domain.models.Role;
import mk.ukim.finki.emt.usermanagement.domain.models.User;
import mk.ukim.finki.emt.usermanagement.domain.models.UserId;
import mk.ukim.finki.emt.usermanagement.service.forms.UserForm;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User registerUser(UserForm form);
    User findUserById(UserId userId);
    User findUserByUsername(String username);
    List<User> getAllUsers();
    void deleteUser(UserId userId);
}
