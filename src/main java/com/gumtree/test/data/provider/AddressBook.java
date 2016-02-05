package com.gumtree.test.data.provider;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * NOTE: Assuming that people with same data should be considered as duplicates, so there
 * was no need to store them twice.
 */
public class AddressBook {

    private final Set<Person> people;


    public AddressBook(Person... people) {
        this.people = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(people)));
    }


    public int getNumberOfMales() {
        return (int) people.parallelStream()
                .filter(e -> e.getGender().equals(Gender.MALE))
                .count();
    }

    public Person getOldestPerson() {
        return people.stream()
                .min((o1, o2) -> {
                    if (o1.getDateOfBirth().isBefore(o2.getDateOfBirth())) {
                        return -1;
                    } else if (o1.getDateOfBirth().isAfter(o2.getDateOfBirth())) {
                        return 1;
                    } else {
                        return 0;
                    }
                }).get();
    }

    public Person getPersonByName(String name) {

        Optional<Person> personByNameOptional = people.stream()
                .filter(person -> person.getName().startsWith(name))
                .findFirst();
        return personByNameOptional.get();
    }

    public boolean isEmpty() {
        return people.isEmpty();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBook that = (AddressBook) o;
        return Objects.equals(people, that.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(people);
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "addressRecords=" + people +
                '}';
    }
}
