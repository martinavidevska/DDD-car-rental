package mk.ukim.finki.emt.usermanagement.domain.repository;

import mk.ukim.finki.emt.usermanagement.domain.enitiy.User;
import mk.ukim.finki.emt.usermanagement.domain.enitiy.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UserId> {

    public Optional<User> findByUsername(String username);

}
