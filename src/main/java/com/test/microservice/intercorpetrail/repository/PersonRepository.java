package com.test.microservice.intercorpetrail.repository;

import com.test.microservice.intercorpetrail.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {


    @Query("select p from Person as p" )
    List<Person> getPersons();

    @Query("select p.age from Person as p" )
    List<Integer> getAverageAge();
}
