package com.horizon.backend.controller;

import com.horizon.backend.entity.Person;
import com.horizon.backend.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    private final PersonService personService;
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //Get by id
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Person>> getPersonById(@PathVariable Long id) {
        logger.info("Calling get by Id from controller:");
        return ResponseEntity.ok(personService.getById(id));
    }

    //Get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<Person>> getPersonById(@PathVariable String email) {
        return ResponseEntity.ok(personService.findPersonByEmail(email));
    }

    //Get by country and domain
    @GetMapping("/search")
    public ResponseEntity<List<Person>> getPersonByDomainAndEmail(@RequestParam String country, @RequestParam String email){
        List<Person> personList = personService.getPersonByCountryAndDomain(country, email);
        personList.forEach(person -> logger.info("Person {} ", person));
        return ResponseEntity.ok(personList);
    }

    //Get All
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(personService.getAll());
    }

    //Create
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createdPerson = personService.savePerson(person);
        return ResponseEntity.status(201).body(createdPerson);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person){
        return personService.updateAPerson(id, person).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        boolean deleted = personService.deleteAPerson(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}










