package iac.schobshop.Schobshop.repository;

import iac.schobshop.Schobshop.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByEmail(String email);
}
