package com.gumtree.test.data.provider.provider;


import com.gumtree.test.data.provider.AddressBook;

/**
 * Populates an address book
 */
public interface AddressBookProvider {

    /**
     * Populates an address book. Internally is up to the implementation to decide
     * how to retrieve this data (filesystem, database, memory, cache, etc).
     * @return
     */
    AddressBook loadData();
}
