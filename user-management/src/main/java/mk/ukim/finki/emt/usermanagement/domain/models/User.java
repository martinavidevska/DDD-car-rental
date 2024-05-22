package mk.ukim.finki.emt.usermanagement.domain.models;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;

@Entity
@Table(name="users")
@NoArgsConstructor
public class User extends AbstractEntity<UserId> {

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
    private Role role;

    public User(String username, String password, String name, String email, String phoneNumber,
                String driverLicenseNumber, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.driverLicenseNumber = driverLicenseNumber;
        this.role = role;
    }

}
