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
                .filter(Person::isMale)
                .count();
    }

    public Person getOldestPerson() {

        return people.stream()
                .min((o1, o2) -> {
                    if (o1.getDateOfBirth().isAfter(o2.getDateOfBirth())) return 1;
                    return -1;
                }).get();
    }

    public Person getPersonByName(String name) {

        Optional<Person> personByNameOptional = people.stream()
                .filter(person -> person.getName().startsWith(name) && matchesAtLeastFirstName(person, name))
                .findFirst();
        return personByNameOptional.get();
    }

    public boolean isEmpty() {
        return people.isEmpty();
    }


    private boolean matchesAtLeastFirstName(Person person, String name) {
        return (person.getName().split(" ")[0].length() <= name.length());
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
                "people=" + people +
                '}';
    }
}
