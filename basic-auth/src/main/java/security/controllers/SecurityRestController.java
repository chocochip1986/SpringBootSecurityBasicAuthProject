package security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import security.entities.Person;
import security.jpa.PersonJpaRepo;

import java.util.Optional;

@RestController
public class SecurityRestController {

    @Autowired
    private PersonJpaRepo personJpaRepo;

    @GetMapping(value = "/person/{name}")
    public ResponseEntity<String> person(@PathVariable("name") String name) {
        Person person = personJpaRepo.findByName(name).orElse(null);
        return new ResponseEntity<>(person == null ? "Nothing" : person.getName(), HttpStatus.OK);
    }

    @PostMapping(value = "/person")
    public ResponseEntity<String> person(){
        return new ResponseEntity<>(personJpaRepo.save(Person.builder().name("Name "+System.currentTimeMillis()).build()).getName(), HttpStatus.OK);
    }

    @GetMapping(value = "/person/list-all")
    public ResponseEntity<String> getPersons(Authentication authentication) {
        System.out.println("User: " + authentication.getName());
        return new ResponseEntity<String>(personJpaRepo.findAll().stream().map(Person::getName)
                .reduce("", (partialString, element)->
                    partialString + System.lineSeparator() + element
                ), HttpStatus.OK);
    }
}
