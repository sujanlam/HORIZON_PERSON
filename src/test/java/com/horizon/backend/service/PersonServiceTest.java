package com.horizon.backend.service;

import com.horizon.backend.entity.Person;
import com.horizon.backend.exception.PersonNotFoundException;
import com.horizon.backend.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    //GET BY ID
    @Test
    @DisplayName("Service Class: Get by Id Person Does Not Exist")
    void testGetPersonById_PersonDoesNotExist() {
        Long id = 1L;
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> personService.getById(id));

        verify(personRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Service Class: Get by Id Happy Path")
    void testGetPersonByIdHappyPath() {
        Person person = new Person(1L, "Moises", "Caicedo", "caicedo@chelsea.com", "Ecuador");
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        Person result = personService.getById(1L);

        verify(personRepository, times(1)).findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Moises", result.getFirstName());
        assertEquals("Caicedo", result.getLastName());
        assertEquals("caicedo@chelsea.com", result.getEmail());
        assertEquals("Ecuador", result.getCountry());
    }

    @Test
    @DisplayName("Service Class: Get by Id with No Email")
    void testGetPersonByIdHappyPath_NoEmail() {
        Person person = new Person(2L, "Moises", "Caicedo", null, "Ecuador");
        when(personRepository.findById(2L)).thenReturn(Optional.of(person));

        Person result = personService.getById(2L);

        verify(personRepository, times(1)).findById(2L);
        assertNotNull(result);
        assertNull(result.getEmail());
        assertEquals("Ecuador", result.getCountry());
    }

    //GET BY EMAIL
    @Test
    @DisplayName("Service Class: Get by Email Person Does Not Exist")
    void testGetPersonByEmail_PersonDoesNotExist() {
        Long id = 1L;
        String email = "aa.gmail.com";
        when(personRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> personService.findPersonByEmail(email));

        verify(personRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Service Class: Get by Id Happy Path")
    void testGetPersonByEmail_HappyPath() {
        String email = "paez@chelsea.com";
        Person person = new Person(1L, "Kendry", "Paez", email, "Ecuador");
        when(personRepository.findByEmail(email)).thenReturn(Optional.of(person));

        Person result = personService.findPersonByEmail(email);

        verify(personRepository, times(1)).findByEmail(email);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Kendry", result.getFirstName());
        assertEquals("Paez", result.getLastName());
        assertEquals("paez@chelsea.com", result.getEmail());
        assertEquals("Ecuador", result.getCountry());
    }

    @Test
    @DisplayName("Service Class: Get by Id with No Email")
    void testGetPersonByEmailBut_NoEmail() {
        Person person = new Person(2L, "Kendry", "Paez", null, "Bolivia");
        when(personRepository.findById(2L)).thenReturn(Optional.of(person));

        Person result = personService.getById(2L);

        verify(personRepository, times(1)).findById(2L);
        assertNotNull(result);
        assertNull(result.getEmail());
        assertEquals("Bolivia", result.getCountry());
    }
}