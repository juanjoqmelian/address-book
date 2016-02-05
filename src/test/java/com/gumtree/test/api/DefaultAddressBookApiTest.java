package com.gumtree.test.api;


import com.gumtree.test.data.provider.Gender;
import com.gumtree.test.data.provider.Person;
import com.gumtree.test.data.provider.provider.AddressBookProvider;
import com.gumtree.test.data.provider.provider.FilesystemAddressBookProvider;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

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
    public void getNumberOfMales_shouldReturnHowManyMalesAreInTheAddressBook() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/AddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        int numberOfMales = addressBookApi.getNumberOfMales();

        assertThat(numberOfMales, is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void getOldestPerson_shouldRaiseNoSuchElementExceptionIfAddressBookIsEmpty() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/EmptyAddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        addressBookApi.getOldestPerson();
    }

    @Test
    public void getOldestPerson_shouldReturnWhoIsTheOldestPersonInTheAddressBook() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/AddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        Person oldestPerson = addressBookApi.getOldestPerson();

        assertThat(oldestPerson, is(new Person("Wes Jackson", Gender.MALE, new DateTime(1974, 8, 14, 0, 0))));
    }

    @Test
    public void getDaysOlder_shouldReturnZeroIfTheyAreTheSameAge() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/AddressBookWithTwins"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        int daysOlder = addressBookApi.getDaysOlder("Gemma", "Anne");

        assertThat(daysOlder, is(0));
    }

    @Test
    public void getDaysOlder_shouldReturnHowManyDaysOlderIsBillThanPaul() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/AddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        int daysOlder = addressBookApi.getDaysOlder("Bill", "Paul");

        assertThat(daysOlder, is(2862));
    }
}
