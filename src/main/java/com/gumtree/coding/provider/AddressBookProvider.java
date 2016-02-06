package com.gumtree.coding.provider;


import com.gumtree.coding.domain.AddressBook;

/**
 * Populates an address book
 */
public interface AddressBookProvider {

    /**
     * Populates an address book. Internally is up to the implementation to decide
     * how to retrieve this data (filesystem, database, memory, cache, etc).
     * @return populated address book
     */
    AddressBook loadData();
}
