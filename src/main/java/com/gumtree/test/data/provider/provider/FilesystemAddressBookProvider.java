package com.gumtree.test.data.provider.provider;


import com.google.common.base.Strings;
import com.gumtree.test.data.provider.AddressBook;
import com.gumtree.test.data.provider.Gender;
import com.gumtree.test.data.provider.Person;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Populates an address book using filesystem source.
 */
public class FilesystemAddressBookProvider implements AddressBookProvider {

    private static final String DELIMITER = ",";

    private AddressBook addressBook;


    public FilesystemAddressBookProvider(final Path source) throws IOException {
        addressBook = readAddressRecordsFromPath(source);
    }


    @Override
    public AddressBook loadData() {
        return addressBook;
    }


    private AddressBook readAddressRecordsFromPath(Path source) throws IOException {

        Set<Person> people = new HashSet<>();

        try {

            DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/MM/yy")
                    .withPivotYear(1950);

            Files.lines(source)
                    .parallel()
                    .map(record -> record.split(DELIMITER))
                    .filter(fields -> isAValidName(fields[0]))
                    .forEach(fields -> people.add(
                            new Person(fields[0],
                                    Gender.valueOf(fields[1].trim().toUpperCase()),
                                    DateTime.parse(fields[2].trim(), dateFormatter))));

        } catch (RuntimeException e) {
            throw new IllegalStateException("Data seems to be wrong in filesystem. Please check the status of your file!");
        }

        return new AddressBook((Person[]) people.toArray(new Person[people.size()]));
    }


    private boolean isAValidName(String name) {
        if (name == null) return false;
        return !Strings.isNullOrEmpty(name.trim());
    }
}
