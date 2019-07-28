package com.test.microservice.intercorpetrail.util;

import java.util.Calendar;

public class RandomDates {

    public static final int MAXAVERAGEOFAGE = 90;

    public Calendar getRandomDeathDate(int yearOfBorn) {

        Calendar dateOfDeath = Calendar.getInstance();
        int maxAgeOfdead = yearOfBorn + MAXAVERAGEOFAGE;

        int year = randBetween(yearOfBorn, maxAgeOfdead);

        dateOfDeath.set(dateOfDeath.YEAR, year);

        int dayOfYear = randBetween(1, dateOfDeath.getActualMaximum(dateOfDeath.DAY_OF_YEAR));

        dateOfDeath.set(dateOfDeath.DAY_OF_YEAR, dayOfYear);

        return dateOfDeath;
    }

    private int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
}