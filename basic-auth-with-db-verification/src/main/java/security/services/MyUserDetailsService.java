package security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.entities.User;
import security.jpa.UserJpaRepo;

import java.util.Optional;

@Service(value = "userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserJpaRepo userJpaRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userJpaRepo.findByUsername(s);
        if ( user.isPresent() ) {
            return MyUserDetails.builder().user(user.get()).build();
        } else {
            throw new UsernameNotFoundException(s);
        }
    }
}
