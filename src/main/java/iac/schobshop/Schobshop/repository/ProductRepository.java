package iac.schobshop.Schobshop.repository;

import iac.schobshop.Schobshop.model.Category;
import iac.schobshop.Schobshop.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Iterable<Product> findProductsByCategoriesIsAndSellable(Category category, boolean sellable);

}
