package com.gumtree.coding.api;

import com.gumtree.coding.data.Person;

/**
 * Address book API that provides some interfaces to gather data about the address book.
 */
public interface AddressBookApi {

    /**
     * Number of males in the address book.
     * @return number of males
     */
    int getNumberOfMales();

    /**
     * Gets the oldest person contained in the address book.
     * @return the oldest person
     */
    Person getOldestPerson();

    /**
     * Get difference of age in days
     * @param currentPersonName
     * @param otherPersonName
     * @return
     */
    int getDaysOlder(String currentPersonName, String otherPersonName);
}
