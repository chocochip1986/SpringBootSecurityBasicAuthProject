package security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import security.entities.Person;
import security.jpa.PersonJpaRepo;

@RestController
public class SecurityRestController {

    @Autowired
    private PersonJpaRepo personJpaRepo;

    @GetMapping(value = "/person")
    public ResponseEntity<String> createPerson() {
        return new ResponseEntity<>(personJpaRepo.save(Person.builder().name("Name "+System.currentTimeMillis()).build()).getName(), HttpStatus.OK);
    }

    @GetMapping(value = "/list/all")
    public ResponseEntity<String> getPersons(Authentication authentication) {
        System.out.println("User: " + authentication.getName());
        return new ResponseEntity<String>(personJpaRepo.findAll().stream().map(Person::getName)
                .reduce("", (partialString, element)->
                    partialString + System.lineSeparator() + element
                ), HttpStatus.OK);
    }
}
