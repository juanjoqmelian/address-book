package com.gumtree.test.data.provider;


import org.joda.time.DateTime;

import java.util.Objects;

/**
 * Immutable person object that holds person data.
 * NOTE : Joda API DateTime class is immutable, so there's no need of defensive copies in the
 * way that was required for Date class.
 */
public final class Person {

    private final String name;
    private final Gender gender;
    private final DateTime dateOfBirth;


    public Person(String name, Gender gender, DateTime dateOfBirth) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isMale() {
        return gender.equals(Gender.MALE);
    }

    public String getName() {
        return name;
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person that = (Person) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
