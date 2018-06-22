package iac.schobshop.Schobshop.service;

import iac.schobshop.Schobshop.command.AccountCommand;
import iac.schobshop.Schobshop.command.AddressCommand;
import iac.schobshop.Schobshop.command.CustomerCommand;
import iac.schobshop.Schobshop.command.RegistrationCommand;
import iac.schobshop.Schobshop.converter.AccountCommandToAccount;
import iac.schobshop.Schobshop.converter.AddressCommandToAddress;
import iac.schobshop.Schobshop.converter.CustomerCommandToCustomer;
import iac.schobshop.Schobshop.model.Account;
import iac.schobshop.Schobshop.model.Address;
import iac.schobshop.Schobshop.model.Customer;
import iac.schobshop.Schobshop.model.Role;
import iac.schobshop.Schobshop.repository.AccountRepository;
import iac.schobshop.Schobshop.repository.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountserviceImplTest {

    private AccountserviceImpl accountservice;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private AccountCommandToAccount accountCommandToAccount;
    @Mock
    private CustomerCommandToCustomer customerCommandToCustomer;
    @Mock
    private AddressCommandToAddress addressCommandToAddress;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        accountservice = new AccountserviceImpl(accountRepository,roleRepository, bCryptPasswordEncoder, accountCommandToAccount,customerCommandToCustomer, addressCommandToAddress);
    }

    @Test
    public void findUserByEmail() {
        Long id = 1L;
        String email = "test@mail.com";
        Account account = new Account();
        account.setId(id);
        account.setEmail(email);
        when(accountRepository.findByEmail(email)).thenReturn(account);
        Account accountResult = accountservice.findUserByEmail(email);
        assertEquals(accountResult,account);
        verify(accountRepository, times(1)).findByEmail(email);
    }

    @Test
    public void findUserByEmailNull(){
        String email = "test@mail.com";
        when(accountRepository.findByEmail(email)).thenReturn(null);
        Account accountResult = accountRepository.findByEmail(email);
        assertNull(accountResult);
        verify(accountRepository, times(1)).findByEmail(email);
    }

    @Test
    public void registerAccount() {
        String password = "test";
        String email = "test@mail.com";
        String firstName = "Test";
        String lastName = "Persoon";
        String postalCode = "1234ab";
        String number = "1a";
        String randomPassword = "qwertyuiop";
        String roleName = "shopper";

        AccountCommand accountCommand = new AccountCommand();
        accountCommand.setPassword(password);
        accountCommand.setPasswordConfirm(password);
        accountCommand.setEmail(email);

        CustomerCommand customerCommand = new CustomerCommand();
        customerCommand.setFirstName(firstName);
        customerCommand.setLastName(lastName);

        AddressCommand addressCommand = new AddressCommand();
        addressCommand.setNumber(number);
        addressCommand.setPostalCode(postalCode);

        RegistrationCommand registrationCommand = new RegistrationCommand();
        registrationCommand.setAccount(accountCommand);
        registrationCommand.setAddress(addressCommand);
        registrationCommand.setCustomer(customerCommand);

        Address address = new Address();
        address.setNumber(number);
        address.setPostalCode(postalCode);

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        Account account = new Account();
        account.setPassword(password);
        account.setEmail(email);

        Role role = new Role();
        role.setRole(roleName);
        role.setId(1L);

        when(bCryptPasswordEncoder.encode(registrationCommand.getAccount().getPassword())).thenReturn(randomPassword);
        when(roleRepository.findByRole(roleName)).thenReturn(role);
        when(accountRepository.save(account)).thenReturn(account);

        when(accountCommandToAccount.convert(accountCommand)).thenReturn(account);
        when(customerCommandToCustomer.convert(customerCommand)).thenReturn(customer);
        when(addressCommandToAddress.convert(addressCommand)).thenReturn(address);

        accountservice.registerAccount(registrationCommand);

        verify(bCryptPasswordEncoder,times(1)).encode(registrationCommand.getAccount().getPassword());
        verify(roleRepository, times(1)).findByRole(roleName);
        verify(accountRepository, times(1)).save(account);

        verify(accountCommandToAccount,times(1)).convert(accountCommand);
        verify(addressCommandToAddress, times(1)).convert(addressCommand);
        verify(customerCommandToCustomer, times(1)).convert(customerCommand);
    }
}