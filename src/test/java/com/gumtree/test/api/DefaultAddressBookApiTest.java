package com.gumtree.test.api;


import com.gumtree.test.api.exception.PersonNotFoundException;
import com.gumtree.test.data.provider.Gender;
import com.gumtree.test.data.provider.Person;
import com.gumtree.test.data.provider.provider.AddressBookProvider;
import com.gumtree.test.data.provider.provider.FilesystemAddressBookProvider;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DefaultAddressBookApiTest {

    private AddressBookApi addressBookApi;
    private AddressBookProvider addressBookProvider;


    @Rule
    public ExpectedException expectedException = ExpectedException.none();


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

    @Test
    public void getNumberOfMales_shouldIgnorePeopleWithNoNameAndReturnHowManyMalesAreInTheAddressBook() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/MissingNameAddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        int numberOfMales = addressBookApi.getNumberOfMales();

        assertThat(numberOfMales, is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void getOldestPerson_shouldRaiseNoSuchElementExceptionIfAddressBookIsEmpty() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/EmptyAddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        addressBookApi.getOldestPerson();
    }

    @Test
    public void getOldestPerson_shouldReturnTheOnlyExistingPersonIfThereIsOnlyOnePersonInTheAddressBook() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/OnePersonAddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        Person oldestPerson = addressBookApi.getOldestPerson();

        assertThat(oldestPerson, is(new Person("Gemma Lane", Gender.FEMALE, new DateTime(1991, 11, 20, 0, 0))));
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
    public void getDaysOlder_shouldReturnZeroIfWeAreComparingTheSamePerson() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/AddressBookWithTwins"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        int daysOlder = addressBookApi.getDaysOlder("Bill", "Bill");

        assertThat(daysOlder, is(0));
    }

    @Test
    public void getDaysOlder_shouldReturnHowManyDaysOlderIsBillThanPaul() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/AddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        int daysOlder = addressBookApi.getDaysOlder("Bill", "Paul");

        assertThat(daysOlder, is(2862));
    }

    @Test
    public void getDaysOlder_shouldReturnHowManyDaysOlderIsPaulThanBillAsANegativeNumberIfBillIsOlderThanPaul() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/AddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        int daysOlder = addressBookApi.getDaysOlder("Paul", "Bill");

        assertThat(daysOlder, is(-2862));
    }

    @Test
    public void getDaysOlder_shouldRaisePersonNotFoundExceptionIfPersonDoesNotExist() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/AddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        expectedException.expect(PersonNotFoundException.class);
        expectedException.expectMessage("No person with name 'Carl' was found!");

        addressBookApi.getDaysOlder("Carl", "Paul");
    }

    @Test
    public void getDaysOlder_shouldRaisePersonNotFoundExceptionIfNameDoesNotMatchAtLeastTheFirstName() throws IOException {

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/AddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);

        expectedException.expect(PersonNotFoundException.class);
        expectedException.expectMessage("No person with name 'Bi' was found!");

        addressBookApi.getDaysOlder("Bi", "Paul");
    }

    @Test
    public void shouldRaiseIllegalStateExceptionIfGenderIsCorruptedForSomeElement() throws IOException {

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Data seems to be wrong in filesystem. Please check the status of your file!");

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/MissingGenderAddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);
    }

    @Test
    public void shouldRaiseIllegalStateExceptionIfDateOfBirthIsCorruptedForSomeElement() throws IOException {

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Data seems to be wrong in filesystem. Please check the status of your file!");

        addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/MissingDateAddressBook"));
        addressBookApi = new DefaultAddressBookApi(addressBookProvider);
    }
}
