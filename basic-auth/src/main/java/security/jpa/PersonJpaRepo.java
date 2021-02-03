package security.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.entities.Person;

import java.util.Optional;

@Repository
public interface PersonJpaRepo extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);
}
