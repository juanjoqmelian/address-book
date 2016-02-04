package com.gumtree.test.api;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AddressBookApiTest {

    private AddressBookApi addressBookApi = new DefaultAddressBookApi();


    @Test
    public void getNumberOfMales_shouldReturnZeroIfThereIsNoData() {

        int numberOfMales = addressBookApi.getNumberOfMales();

        assertThat(numberOfMales, is(0));
    }

}
