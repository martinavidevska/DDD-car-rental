package mk.ukim.finki.emt.usermanagement.xport.rest;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.emt.usermanagement.domain.exceptions.InvalidArgumentsException;
import mk.ukim.finki.emt.usermanagement.domain.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.emt.usermanagement.domain.models.User;
import mk.ukim.finki.emt.usermanagement.domain.models.UserId;
import mk.ukim.finki.emt.usermanagement.service.AuthService;
import mk.ukim.finki.emt.usermanagement.service.UserService;
import mk.ukim.finki.emt.usermanagement.service.forms.UserForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class UserResource {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody UserForm userForm) {
        try {
           this.userService.registerUser(userForm);
           return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable UserId id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{username}")
    public User findUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @GetMapping()
    public List<User> getAllUsers() {
      return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UserId id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
