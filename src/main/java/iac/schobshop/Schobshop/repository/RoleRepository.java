package iac.schobshop.Schobshop.repository;

import iac.schobshop.Schobshop.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String Role);
}
