package iac.schobshop.Schobshop.converter;

import iac.schobshop.Schobshop.command.AddressCommand;
import iac.schobshop.Schobshop.model.Address;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressToAddressCommandTest {

    private AddressCommandToAddress addressCommandToAddress;


    @Before
    public void setUp() throws Exception {
        addressCommandToAddress = new AddressCommandToAddress();
    }

    @Test
    public void convert() {
        String street = "teststreet";
        String number = "1a";
        String postalCode = "1234ab";
        AddressCommand addressCommand = new AddressCommand();
        addressCommand.setPostalCode(postalCode);
        addressCommand.setStreet(street);
        addressCommand.setNumber(number);
        Address address = addressCommandToAddress.convert(addressCommand);
        assertEquals(addressCommand.getStreet(), address.getStreet());
        assertEquals(addressCommand.getNumber(), address.getNumber());
        assertNull(addressCommand.getCountry());

    }
}