package com.gumtree.coding.api;


import com.gumtree.coding.api.exception.PersonNotFoundException;
import com.gumtree.coding.data.AddressBook;
import com.gumtree.coding.data.Person;
import com.gumtree.coding.data.provider.AddressBookProvider;

import java.util.NoSuchElementException;


public class DefaultAddressBookApi implements AddressBookApi {

    private AddressBook addressBook;


    public DefaultAddressBookApi(final AddressBookProvider addressBookProvider) {
        addressBook = addressBookProvider.loadData();
    }


    @Override
    public int getNumberOfMales() {

        return addressBook.numberOfMales();
    }

    @Override
    public Person getOldestPerson() {

        return addressBook.oldestPerson();
    }

    @Override
    public int getDaysOlder(String firstPersonName, String secondPersonName) {

        Person firstPerson = getPersonByName(firstPersonName);
        Person secondPerson = getPersonByName(secondPersonName);
        return firstPerson.daysOlderThan(secondPerson);
    }


    private Person getPersonByName(String personName) {

        Person person;
        try {
            person = addressBook.getPersonByName(personName);
        } catch (NoSuchElementException e) {
            throw new PersonNotFoundException(String.format("No person with name '%s' was found!", personName));
        }
        return person;
    }
}
