package security.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.entities.User;

import java.util.Optional;

@Repository
public interface UserJpaRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
