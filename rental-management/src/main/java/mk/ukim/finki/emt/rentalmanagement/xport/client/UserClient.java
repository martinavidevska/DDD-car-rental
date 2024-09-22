package mk.ukim.finki.emt.rentalmanagement.xport.client;

import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.User;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.UserId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserClient {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public UserClient(@Value("${app.user-management.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<User> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/users").build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<User>>() {}).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public User getUserById() {
        try {
            return restTemplate.exchange(uri().path("/api/auth/get-user").build().toUri(),
                    HttpMethod.GET, null, new ParameterizedTypeReference<User>() {}).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public UserId getUserByUsername(String username) {
        try {
            return Objects.requireNonNull(restTemplate.exchange(uri().path("/api/auth/{username}").buildAndExpand(username).toUri(),
                    HttpMethod.GET, null, new ParameterizedTypeReference<User>() {
                    }).getBody()).getUserId();
        } catch (Exception e) {
            return null;
        }
    }

    public User addUser(User user) {
        try {
            ResponseEntity<User> response = restTemplate.exchange(
                    uri().path("/api/users/register").build().toUri(),
                    HttpMethod.POST,
                    new HttpEntity<>(user),
                    User.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteUser(UserId userId) {
        try {
            restTemplate.exchange(uri().path("/api/users/{id}").buildAndExpand(userId.getId()).toUri(),
                    HttpMethod.DELETE, null, Void.class);
        } catch (Exception e) {
        }
    }
}
