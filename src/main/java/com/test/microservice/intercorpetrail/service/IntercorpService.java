package com.test.microservice.intercorpetrail.service;

import com.test.microservice.intercorpetrail.exceptions.BussinesLogicException;
import com.test.microservice.intercorpetrail.model.Person;
import com.test.microservice.intercorpetrail.model.PersonWrapper;
import com.test.microservice.intercorpetrail.repository.PersonRepository;
import com.test.microservice.intercorpetrail.util.RandomDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service
public class IntercorpService {

    private final PersonRepository personRepository;
    private RandomDates randomDates;

    @Autowired
    public IntercorpService(PersonRepository personRepository) {
        this.personRepository = personRepository;
        randomDates = new RandomDates();
    }

    public void registerNewPerson(PersonWrapper person) throws BussinesLogicException {
        Person newperson = buildPerson(person);

        personRepository.save(newperson);
    }
    public List<Person> getPersons() {

        return personRepository.getPersons();
    }

    public Double getAverageAge() {
        List<Integer> ageOfPersons = personRepository.getAverageAge();

        return ageOfPersons.stream().mapToInt((x) -> x).summaryStatistics().getAverage();
    }


    private Person buildPerson(PersonWrapper person) throws BussinesLogicException {
        Person newPerson = new Person();

        if (person.getDateOfBorn() != null && person.getAge() > 0) {
            newPerson.setAge(person.getAge());
            newPerson.setName(person.getName());
            newPerson.setLastname(person.getLastname());
            newPerson.setBirthday(getDateOfBirthday(person));
             Calendar dateOfDead = randomDates.getRandomDeathDate(person.getDateOfBorn().getYear());
            newPerson.setDateOfDeath(dateOfDead);
        } else {
            throw new BussinesLogicException("invalid fields for person");
        }

        return newPerson;
    }

    private Calendar getDateOfBirthday(PersonWrapper person) {
        Calendar birthday =  Calendar.getInstance();
        LocalDate dateOfBorn = person.getDateOfBorn();
        birthday.set(dateOfBorn.getYear(),dateOfBorn.getMonth().getValue(),dateOfBorn.getDayOfMonth());

        return birthday;
    }

}
