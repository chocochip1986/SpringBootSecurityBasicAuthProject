package security.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import security.entities.User;
import security.exceptions.InvalidUserCreationException;
import security.exceptions.UserNotFoundException;
import security.services.UserService;

@RestController
public class SpringBootRestController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/api/v1/user/{username}")
    public ResponseEntity<User> user(@PathVariable("username") String username) {
        User user = userService.retrieveUser(username);

        if ( user == null ) {
            throw new UserNotFoundException("No such user as "+username);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/api/v1/user")
    public ResponseEntity<User> user() {
        User user = userService.createUser();

        if ( user == null ) {
            throw new InvalidUserCreationException("Cannot create la wah lao eh");
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
