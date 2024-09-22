package mk.ukim.finki.emt.usermanagement.domain.enitiy;


import jakarta.persistence.*;
import lombok.*;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Data
@Table (name = "users")
public class User extends AbstractEntity<UserId> implements UserDetails {

        private String name;
        private String email;
        private String username;
        private String password;
        private String driverLicenceNumber;
        private String phoneNumber;
        private String address;

        public User(String name, String email,String username, String password, String driverLicenceNumber, String phoneNumber, String address ) {
                super(UserId.randomId(UserId.class));
                this.name = name;
                this.username = username;
                this.email = email;
                this.password = password;
                this.driverLicenceNumber = driverLicenceNumber;
                this.phoneNumber = phoneNumber;
                this.address = address;
        }
        public User(){

        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
//                return Collections.singletonList(role);
                return null;

        }

        @Override
        public String getUsername() {
                return this.username;
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
