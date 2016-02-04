package com.gumtree.test.data;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * NOTE: Assuming that records with same data should be considered as duplicates, so there
 * was no need to store them twice.
 */
public class AddressBook {

    private final Set<AddressRecord> addressRecords;


    public AddressBook(AddressRecord... addressRecords) {
        this.addressRecords = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(addressRecords)));
    }


    public boolean isEmpty() {
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBook that = (AddressBook) o;
        return Objects.equals(addressRecords, that.addressRecords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressRecords);
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "addressRecords=" + addressRecords +
                '}';
    }

    public int getNumberOfMales() {
        return (int) addressRecords.parallelStream()
                .filter(e -> e.getGender().equals(Gender.MALE))
                .count();
    }
}
