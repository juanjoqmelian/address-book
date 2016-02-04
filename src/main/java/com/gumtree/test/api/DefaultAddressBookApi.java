package com.gumtree.test.api;


import com.gumtree.test.data.provider.AddressBook;
import com.gumtree.test.data.provider.Person;
import com.gumtree.test.data.provider.provider.AddressBookProvider;

public class DefaultAddressBookApi implements AddressBookApi {

    private AddressBook addressBook;


    public DefaultAddressBookApi(final AddressBookProvider addressBookProvider) {
        addressBook = addressBookProvider.loadData();
    }


    @Override
    public int getNumberOfMales() {
        return addressBook.getNumberOfMales();
    }

    @Override
    public Person getOldestPerson() {
        return addressBook.getOldestPerson();
    }
}
