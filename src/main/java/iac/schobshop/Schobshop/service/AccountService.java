package iac.schobshop.Schobshop.service;

import iac.schobshop.Schobshop.command.RegistrationCommand;
import iac.schobshop.Schobshop.model.Account;

public interface AccountService {
    Account findUserByEmail(String email);
    void registerAccount(RegistrationCommand registrationCommand);

    Account findAccount(Long id);
}
