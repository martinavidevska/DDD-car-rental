package mk.ukim.finki.emt.usermanagement.domain.models;

import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="users")
@NoArgsConstructor
@Data
public class User extends AbstractEntity<UserId> implements UserDetails {

    @Column(name = "username", nullable = false, unique = true)
    private String username;


    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "name", nullable = false)
    private String name;



    @Column(name = "email", nullable = false, unique = true)
    private String email;


    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;


    @Column(name = "driver_license_number", nullable = false, unique = true)
    private String driverLicenseNumber;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;


    public User(String username, String password, String name, String email, String phoneNumber,
                String driverLicenseNumber, Role role) {
        super(UserId.randomId(UserId.class));
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.driverLicenseNumber = driverLicenseNumber;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
