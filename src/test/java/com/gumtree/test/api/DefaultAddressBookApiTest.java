package com.gumtree.test.api;


import com.gumtree.test.data.AddressBookProvider;
import com.gumtree.test.data.FilesystemAddressBookProvider;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DefaultAddressBookApiTest {

    private AddressBookApi addressBookApi;
    private AddressBookProvider addressBookProvider;


    @Test
    public void getNumberOfMales_shouldReturnZeroIfThereIsNoData() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/EmptyAddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        int numberOfMales = addressBookApi.getNumberOfMales();

        assertThat(numberOfMales, is(0));
    }

    @Test
    public void getNumberOfMales_shouldReturnThreeIfThereAreThreeMalesInAddressBook() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/AddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        int numberOfMales = addressBookApi.getNumberOfMales();

        assertThat(numberOfMales, is(3));
    }

}
