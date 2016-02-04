package com.gumtree.test.api;


import com.gumtree.test.data.AddressBook;
import com.gumtree.test.data.AddressBookProvider;

public class DefaultAddressBookApi implements AddressBookApi {

    private AddressBook addressBook;


    public DefaultAddressBookApi(final AddressBookProvider addressBookProvider) {
        addressBook = addressBookProvider.loadData();
    }


    @Override
    public int getNumberOfMales() {
        return addressBook.getNumberOfMales();
    }
}
