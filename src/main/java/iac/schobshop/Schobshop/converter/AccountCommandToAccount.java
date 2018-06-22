package iac.schobshop.Schobshop.converter;

import iac.schobshop.Schobshop.command.AccountCommand;
import iac.schobshop.Schobshop.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AccountCommandToAccount implements Converter<AccountCommand, Account> {

    private AddressCommandToAddress addressCommandToAddressConverter;
    private CustomerCommandToCustomer customerCommandToCustomerConverter;
    private FileToByteArray fileToByteArray;


    @Override
    public Account convert(AccountCommand command){
        Account account = new Account();
        account.setEmail(command.getEmail());
        if (command.getProfilePicture() != null){
            account.setProfilePicture(fileToByteArray.convert(command.getProfilePicture()));
        }
        if (command.getAddressCommand()!= null){
            account.setBillingAddress(addressCommandToAddressConverter.convert(command.getAddressCommand()));
        }
        if (command.getCustomerCommand() != null){
            account.setCustomer(customerCommandToCustomerConverter.convert(command.getCustomerCommand()));
        }
        return account;
    }

}