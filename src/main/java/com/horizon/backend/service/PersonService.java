package com.horizon.backend.service;

import com.horizon.backend.entity.Person;
import com.horizon.backend.exception.PersonNotFoundException;
import com.horizon.backend.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //Get by id
    public Person getById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person with id: " + id + "not found!"));
    }

    //Get by email
    public Person findPersonByEmail(String email) {
        return personRepository.findByEmail(email).orElseThrow(() -> new PersonNotFoundException("Person with email: " + email + "not found!"));
    }

    //Get by email and country
    public List<Person> getPersonByCountryAndDomain(String country, String email){
        String formattedDomain = email.startsWith("@") ? email : "@" + email;
        return personRepository.getPersonByCountryAndDomain(country, formattedDomain);
    }

    //Get All
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    //Create
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    //Update
    public Optional<Person> updateAPerson(Long id, Person newPerson) {
        return personRepository.findById(id).map(existing -> {
            existing.setCountry(newPerson.getCountry());
            existing.setEmail(newPerson.getEmail());
            existing.setFirstName(newPerson.getFirstName());
            existing.setLastName(newPerson.getLastName());

            return personRepository.save(existing);
        });
    }

    //Delete
    public boolean deleteAPerson(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
