package com.gumtree.test.api;

import com.gumtree.test.data.provider.Person;

/**
 * Address book API that provides some interfaces to gather data about the address book.
 */
public interface AddressBookApi {

    int getNumberOfMales();

    Person getOldestPerson();
}
