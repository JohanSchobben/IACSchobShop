package iac.schobshop.Schobshop.converter;

import iac.schobshop.Schobshop.command.CustomerCommand;
import iac.schobshop.Schobshop.model.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerCommandToCustomerTest {

    private CustomerCommandToCustomer customerCommandToCustomer;

    @Before
    public void setUp() throws Exception {
        customerCommandToCustomer = new CustomerCommandToCustomer();
    }

    @Test
    public void convert() {
        String firstName =  "test";
        CustomerCommand customerCommand = new CustomerCommand();
        customerCommand.setFirstName(firstName);
        Customer customer = customerCommandToCustomer.convert(customerCommand);
        assertEquals(customer.getFirstName(), customerCommand.getFirstName());
        assertNull(customer.getLastName());
    }
}