package security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import security.entities.User;
import security.enums.EncryptEnum;
import security.jpa.UserJpaRepo;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserJpaRepo userJpaRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User retrieveUser(String username) {
        Optional<User> user = userJpaRepo.findByUsername(username);

        return user.orElse(null);
    }

    public User createUser() {
        User user = User.builder()
                .username("User"+System.currentTimeMillis())
                .password(encryptPassword("password"))
                .isEnabled(true)
                .isLocked(false)
                .expiredAt(null)
                .build();

        return userJpaRepo.save(user);
    }

    private String encryptPassword(String rawStringFormOfPassword) {
        return bCryptPasswordEncoder.encode(rawStringFormOfPassword);
    }
}
