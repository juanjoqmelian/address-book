package com.gumtree.test.data;


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

    private Set<AddressRecord> addressRecords = new HashSet<>();


    public FilesystemAddressBookProvider(final Path source) throws IOException {
        addressRecords = readAddressRecordsFromPath(source);
    }


    @Override
    public AddressBook loadData() {
        return new AddressBook((AddressRecord[]) addressRecords.toArray(new AddressRecord[addressRecords.size()]));
    }


    private Set<AddressRecord> readAddressRecordsFromPath(Path source) throws IOException {

        Set<AddressRecord> addressRecords = new HashSet<>();

        Files.lines(source)
                .parallel()
                .map(record -> record.split(","))
                .forEach(fields -> {

                    DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/MM/yy")
                            .withPivotYear(1950);

                    addressRecords.add(
                            new AddressRecord(fields[0],
                                    Gender.valueOf(fields[1].trim().toUpperCase()),
                                    DateTime.parse(fields[2].trim(), dateFormatter)));
                });
        return addressRecords;
    }
}
