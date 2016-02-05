package com.gumtree.test.api;


import com.gumtree.test.api.exception.PersonNotFoundException;
import com.gumtree.test.data.AddressBook;
import com.gumtree.test.data.Person;
import com.gumtree.test.data.provider.AddressBookProvider;

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
