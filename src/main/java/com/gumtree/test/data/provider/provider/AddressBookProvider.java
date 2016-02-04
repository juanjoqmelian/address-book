package com.gumtree.test.data.provider.provider;


import com.gumtree.test.data.provider.AddressBook;

/**
 * Populates an address book
 */
public interface AddressBookProvider {

    AddressBook loadData();
}
