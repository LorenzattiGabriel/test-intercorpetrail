package com.test.microservice.intercorpetrail.service;

import com.test.microservice.intercorpetrail.exceptions.BussinesLogicException;
import com.test.microservice.intercorpetrail.model.Person;
import com.test.microservice.intercorpetrail.repository.PersonRepository;
import com.test.microservice.intercorpetrail.util.RandomDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
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

    public void registerNewPerson(Person person) throws BussinesLogicException {
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

    private Date getDateOfDead(Date birthday) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthday);
        return randomDates.getRandomDeathDate(cal.get(Calendar.YEAR));
    }

    private Person buildPerson(Person person) throws BussinesLogicException {
        Person newPerson;

        if (person.getBirthday() != null) {
            newPerson = person;
            newPerson.setDateOfDeath(getDateOfDead(person.getBirthday()));
        } else {
            throw new BussinesLogicException("invalid date of birth");
        }

        return newPerson;
    }

}
