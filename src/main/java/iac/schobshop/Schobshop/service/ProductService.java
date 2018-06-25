package iac.schobshop.Schobshop.service;

import iac.schobshop.Schobshop.model.Category;
import iac.schobshop.Schobshop.model.Product;

import java.util.Set;

public interface ProductService {
    Set<Product> findProductsByCategory(Category category);
    Set<Product> findAllProducts();
    Product findProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Product product, Long id);
    void deleteProduct(Long id);
}
