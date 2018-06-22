package iac.schobshop.Schobshop.converter;

import iac.schobshop.Schobshop.command.AddressCommand;
import iac.schobshop.Schobshop.model.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressCommandToAddress implements Converter<AddressCommand, Address> {

    @Override
    public Address convert(AddressCommand command) {
        Address address = new Address();
        address.setStreet(command.getStreet());
        address.setNumber(command.getNumber());
        address.setPostalCode(command.getPostalCode());
        address.setCity(command.getCity());
        address.setCountry(command.getCountry());
        return address;
    }
}