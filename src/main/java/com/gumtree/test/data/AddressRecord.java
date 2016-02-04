package com.gumtree.test.data;


import org.joda.time.DateTime;

import java.util.Objects;

/**
 * Immutable address record that holds the information about an address.
 * NOTE : Joda API DateTime class is immutable, so there's no need of defensive copies in the
 * way that was required for Date class.
 */
public final class AddressRecord {

    private final String name;
    private final Gender gender;
    private final DateTime dateOfBirth;


    public AddressRecord(String name, Gender gender, DateTime dateOfBirth) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }


    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressRecord that = (AddressRecord) o;
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
        return "AddressRecord{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
