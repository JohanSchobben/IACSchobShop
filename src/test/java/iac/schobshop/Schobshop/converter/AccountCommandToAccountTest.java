package iac.schobshop.Schobshop.converter;

import iac.schobshop.Schobshop.command.AccountCommand;
import iac.schobshop.Schobshop.command.AddressCommand;
import iac.schobshop.Schobshop.command.CustomerCommand;
import iac.schobshop.Schobshop.model.Account;
import iac.schobshop.Schobshop.model.Address;
import iac.schobshop.Schobshop.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountCommandToAccountTest {
    private AccountCommandToAccount accountCommandToAccount;
    @Mock
    private AddressCommandToAddress addressCommandToAddress;
    @Mock
    private CustomerCommandToCustomer customerCommandToCustomer;
    @Mock
    private FileToByteArray fileToByteArray;
    private AccountCommand accountCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        accountCommandToAccount = new AccountCommandToAccount(addressCommandToAddress,customerCommandToCustomer,fileToByteArray);

        String testmail = "Test@mail.com";
        String testPassword = "12345";
        accountCommand = new AccountCommand();
        accountCommand.setEmail(testmail);
        accountCommand.setPassword(testPassword);
        accountCommand.setPasswordConfirm(testPassword);
    }

    @Test
    public void convert() {
        Account account = accountCommandToAccount.convert(accountCommand);
        assertEquals(account.getEmail(), account.getEmail());
        assertNull(account.getPassword());
        verify(addressCommandToAddress, times(0)).convert(accountCommand.getAddressCommand());
        verify(customerCommandToCustomer, times(0)).convert(accountCommand.getCustomerCommand());

    }

    @Test
    public void convertWithAddress(){
        AddressCommand addressCommand = new AddressCommand();
        accountCommand.setAddressCommand(addressCommand);
        Account account = accountCommandToAccount.convert(accountCommand);
        when(addressCommandToAddress.convert(addressCommand)).thenReturn(new Address());
        verify(addressCommandToAddress, times(1)).convert(accountCommand.getAddressCommand());
        verify(customerCommandToCustomer, times(0)).convert(accountCommand.getCustomerCommand());
    }
    @Test
    public void convertWithAddressAndCustomer(){
        AddressCommand addressCommand = new AddressCommand();
        CustomerCommand customerCommand = new CustomerCommand();
        accountCommand.setAddressCommand(addressCommand);
        accountCommand.setCustomerCommand(customerCommand);
        Account account = accountCommandToAccount.convert(accountCommand);
        when(addressCommandToAddress.convert(addressCommand)).thenReturn(new Address());
        when(customerCommandToCustomer.convert(customerCommand)).thenReturn(new Customer());
        verify(addressCommandToAddress, times(1)).convert(accountCommand.getAddressCommand());
        verify(customerCommandToCustomer, times(1)).convert(accountCommand.getCustomerCommand());
    }
}