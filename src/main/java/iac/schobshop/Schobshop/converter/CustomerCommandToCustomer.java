package iac.schobshop.Schobshop.converter;

import iac.schobshop.Schobshop.command.CustomerCommand;
import iac.schobshop.Schobshop.model.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerCommandToCustomer implements Converter<CustomerCommand, Customer> {
    @Override
    public Customer convert(CustomerCommand command) {
        Customer customer = new Customer();
        customer.setFirstName(command.getFirstName());
        customer.setLastName(command.getLastName());
        return customer;
    }
}
