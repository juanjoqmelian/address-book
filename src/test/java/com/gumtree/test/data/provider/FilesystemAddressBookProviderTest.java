package com.gumtree.test.data.provider;


import com.gumtree.test.data.provider.provider.AddressBookProvider;
import com.gumtree.test.data.provider.provider.FilesystemAddressBookProvider;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FilesystemAddressBookProviderTest {


    @Test
    public void loadData_shouldLoadAnEmptyAddressBookIfFileIsEmpty() throws IOException {

        final AddressBookProvider addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/EmptyAddressBook"));

        AddressBook emptyAddressBook = addressBookProvider.loadData();

        assertThat(emptyAddressBook.isEmpty(), is(Boolean.TRUE));
    }

    @Test
    public void loadData_shouldLoadAValidAddressBookFromFilesystem() throws IOException {

        final AddressBook expectedAddressBook = new AddressBook(
                new Person("Bill McKnight", Gender.MALE, new DateTime(1977, 3, 16, 0, 0)),
                new Person("Paul Robinson", Gender.MALE, new DateTime(1985, 1, 15, 0, 0)),
                new Person("Gemma Lane", Gender.FEMALE, new DateTime(1991, 11, 20, 0, 0)),
                new Person("Sarah Stone", Gender.FEMALE, new DateTime(1980, 9, 20, 0, 0)),
                new Person("Wes Jackson", Gender.MALE, new DateTime(1974, 8, 14, 0, 0))
        );

        final AddressBookProvider addressBookProvider = new FilesystemAddressBookProvider(Paths.get("src/test/resources/AddressBook"));

        AddressBook addressbook = addressBookProvider.loadData();

        assertThat(addressbook, is(expectedAddressBook));
    }

    @Test(expected = NoSuchFileException.class)
    public void shouldRaiseExceptionIfPathDoesNotExist() throws IOException {

        new FilesystemAddressBookProvider(Paths.get("/FakePath"));
    }

}
