package com.gumtree.test.data.provider.provider;


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

    private Set<Person> people;


    public FilesystemAddressBookProvider(final Path source) throws IOException {
        people = readAddressRecordsFromPath(source);
    }


    @Override
    public AddressBook loadData() {
        return new AddressBook((Person[]) people.toArray(new Person[people.size()]));
    }


    private Set<Person> readAddressRecordsFromPath(Path source) throws IOException {

        Set<Person> people = new HashSet<>();

        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/MM/yy")
                .withPivotYear(1950);

        //TODO - Check that elements are not null before trim
        Files.lines(source)
                .parallel()
                .map(record -> record.split(DELIMITER))
                .forEach(fields -> people.add(
                        new Person(fields[0],
                                Gender.valueOf(fields[1].trim().toUpperCase()),
                                DateTime.parse(fields[2].trim(), dateFormatter))));
        return people;
    }
}
