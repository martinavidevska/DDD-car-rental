package mk.ukim.finki.emt.usermanagement.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.usermanagement.config.filter.JWTAuthorizationFilter;
import mk.ukim.finki.emt.usermanagement.config.filter.JwtAuthenticationFilter;
import mk.ukim.finki.emt.usermanagement.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("jwt")
@Order(200)
@AllArgsConstructor
public class JWTWebSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();

        http
                .authorizeRequests()
                .requestMatchers("/", "/home", "/assets/**", "/register", "/products", "/api/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager, userService, passwordEncoder))
                .addFilter(new JWTAuthorizationFilter(authenticationManager, userService))
                .csrf().disable()
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
