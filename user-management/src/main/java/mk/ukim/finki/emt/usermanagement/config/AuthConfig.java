package mk.ukim.finki.emt.usermanagement.config;

import mk.ukim.finki.emt.usermanagement.domain.exceptions.AuthError;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class AuthConfig {


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
    @Bean
    public AuthError authError() {
        return new AuthError();
    }

}
