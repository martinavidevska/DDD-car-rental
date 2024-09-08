package mk.ukim.finki.emt.usermanagement.xport.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.emt.usermanagement.config.filter.JwtAuthenticationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/users/login")
public class LoginController {

    private final JwtAuthenticationFilter filter;

    public LoginController(JwtAuthenticationFilter filter) {
        this.filter = filter;
    }


    @PostMapping
    public String doLogin(HttpServletRequest request,
                          HttpServletResponse response) throws JsonProcessingException {
        Authentication auth = this.filter.attemptAuthentication(request, response);
        return this.filter.generateJwt(response, auth);

    }
}

