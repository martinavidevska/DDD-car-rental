package mk.ukim.finki.emt.usermanagement.service;

import mk.ukim.finki.emt.usermanagement.domain.models.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    User login(String username, String password);
}
