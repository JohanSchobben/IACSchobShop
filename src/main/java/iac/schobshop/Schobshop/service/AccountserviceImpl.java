package iac.schobshop.Schobshop.service;

import iac.schobshop.Schobshop.command.RegistrationCommand;
import iac.schobshop.Schobshop.converter.AccountCommandToAccount;
import iac.schobshop.Schobshop.converter.AddressCommandToAddress;
import iac.schobshop.Schobshop.converter.CustomerCommandToCustomer;
import iac.schobshop.Schobshop.exceptions.ObjectNotFoundException;
import iac.schobshop.Schobshop.model.Account;
import iac.schobshop.Schobshop.model.Address;
import iac.schobshop.Schobshop.model.Customer;
import iac.schobshop.Schobshop.model.Role;
import iac.schobshop.Schobshop.repository.AccountRepository;
import iac.schobshop.Schobshop.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class AccountserviceImpl implements AccountService {

    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AccountCommandToAccount accountCommandToAccount;
    private CustomerCommandToCustomer customerCommandToCustomer;
    private AddressCommandToAddress addressCommandToAddress;

    @Override
    public Account findUserByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public void registerAccount(RegistrationCommand registrationCommand) {
        Account account = accountCommandToAccount.convert(registrationCommand.getAccount());
        Customer customer = customerCommandToCustomer.convert(registrationCommand.getCustomer());
        Address address = addressCommandToAddress.convert(registrationCommand.getAddress());

        account.setCustomer(customer);
        account.getCustomer().setAddress(address);
        account.setBillingAddress(address);
        account.setActive(true);
        account.setPassword(bCryptPasswordEncoder.encode(registrationCommand.getAccount().getPassword()));
        Role role = roleRepository.findByRole("shopper");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setRoles(roles);
        accountRepository.save(account);
    }

    @Override
    public Account findAccount(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (!account.isPresent()){
            throw new ObjectNotFoundException("Account", "id", id.toString());
        }
        return account.get();
    }
}
