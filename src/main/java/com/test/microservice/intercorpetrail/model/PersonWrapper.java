package com.test.microservice.intercorpetrail.model;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * Created by gabriellorenzatti on 28/07/2019.
 */
public class PersonWrapper {

    private String name;
    private String lastname;
    private LocalDate dateOfBorn;
    private int age;

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getDateOfBorn() {
        return dateOfBorn;
    }
    public int getAge() {
        return age;
    }
}
