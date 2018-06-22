package iac.schobshop.Schobshop.repository;

import iac.schobshop.Schobshop.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
