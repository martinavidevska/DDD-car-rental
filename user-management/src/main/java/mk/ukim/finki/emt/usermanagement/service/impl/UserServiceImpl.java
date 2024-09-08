package mk.ukim.finki.emt.usermanagement.service.impl;

import mk.ukim.finki.emt.usermanagement.domain.exceptions.UserNotFoundException;
import mk.ukim.finki.emt.usermanagement.domain.models.Role;
import mk.ukim.finki.emt.usermanagement.domain.models.User;
import mk.ukim.finki.emt.usermanagement.domain.models.UserId;
import mk.ukim.finki.emt.usermanagement.domain.repository.UserRepository;
import mk.ukim.finki.emt.usermanagement.service.UserService;
import mk.ukim.finki.emt.usermanagement.service.forms.UserForm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(UserForm form) {
       User user =new User(form.getUsername(), form.getPassword(), form.getName(),form.getEmail(), form.getPhoneNumber(), form.getDriverLicenseNumber(), Role.USER);
        return userRepository.save(user);
    }

    @Override
    public User findUserById(UserId userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("id"));
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(UserId userId) {
        User user = this.findUserById(userId);
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username) );
    }
}
