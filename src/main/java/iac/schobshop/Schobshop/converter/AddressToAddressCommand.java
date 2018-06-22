package iac.schobshop.Schobshop.converter;


import iac.schobshop.Schobshop.command.AddressCommand;
import iac.schobshop.Schobshop.model.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressCommand implements Converter<Address, AddressCommand> {
    @Override
    public AddressCommand convert(Address address) {
        AddressCommand addressCommand = new AddressCommand();
        addressCommand.setCity(address.getCity());
        addressCommand.setCountry(address.getCountry());
        addressCommand.setPostalCode(address.getPostalCode());
        addressCommand.setNumber(address.getNumber());
        addressCommand.setStreet(address.getStreet());
        return addressCommand;
    }
}

