package com.horizon.backend.repository;

import com.horizon.backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);

    @Query("select p from Person p where p.country=:country and p.email LIKE %:email")
    List<Person> getPersonByCountryAndDomain(@Param("country") String country, @Param("email") String email);
}
