package mk.ukim.finki.emt.usermanagement.domain.dto;

import lombok.Data;
import mk.ukim.finki.emt.usermanagement.domain.models.Role;
import mk.ukim.finki.emt.usermanagement.domain.models.User;

@Data
public class UserDetailsDto {
    private String username;
    private Role role;

    public static UserDetailsDto of(User user) {
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.role = user.getRole();
        return details;
    }
}
