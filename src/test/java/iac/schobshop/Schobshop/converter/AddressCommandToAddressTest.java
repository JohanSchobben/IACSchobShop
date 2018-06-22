package iac.schobshop.Schobshop.converter;

import iac.schobshop.Schobshop.command.AddressCommand;
import iac.schobshop.Schobshop.model.Address;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressCommandToAddressTest {

    private AddressCommandToAddress addressCommandToAddress;

    @Before
    public void setUp() throws Exception {
        addressCommandToAddress = new AddressCommandToAddress();
    }

    @Test
    public void convert(){
        String street = "teststreet";
        String number = "1a";
        String postalCode = "1234ab";
        AddressCommand addressCommand = new AddressCommand();
        addressCommand.setStreet(street);
        addressCommand.setNumber(number);
        addressCommand.setPostalCode(postalCode);
        Address address = addressCommandToAddress.convert(addressCommand);
        assertEquals(address.getStreet(), addressCommand.getStreet());
        assertEquals(address.getPostalCode(), addressCommand.getPostalCode());
        assertNull(address.getCity());
    }
}