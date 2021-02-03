package security.configs;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        SecurityContext context = SecurityContextHolder.getContext();
        if ( context == null ) {
            return Optional.empty();
        } else {
            Authentication authentication = context.getAuthentication();
            if ( authentication == null ) {
                return Optional.empty();
            } else {
                User principal = (User) authentication.getPrincipal();
                if ( principal == null ) {
                    return Optional.empty();
                } else {
                    String username = principal.getUsername();
                    if ( username == null || username.isEmpty() ) {
                        return Optional.empty();
                    } else {
                        return Optional.of(username);
                    }
                }
            }
        }

    }
}
