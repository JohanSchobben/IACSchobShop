package iac.schobshop.Schobshop.service;

import com.google.common.collect.Sets;
import iac.schobshop.Schobshop.exceptions.DeleteException;
import iac.schobshop.Schobshop.exceptions.ObjectCreationException;
import iac.schobshop.Schobshop.exceptions.ObjectNotFoundException;
import iac.schobshop.Schobshop.model.Category;
import iac.schobshop.Schobshop.model.Product;
import iac.schobshop.Schobshop.repository.CategoryRepository;
import iac.schobshop.Schobshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Override
    public Set<Product> findProductsByCategory(Category category) {
        Iterable<Product> productIterable = productRepository.findProductsByCategoriesIsAndSellable(category,true);
        return Sets.newHashSet(productIterable);
    }

    @Override
    public Set<Product> findAllProducts() {
        Iterable<Product> products = productRepository.findAll();
        return Sets.newHashSet(products);
    }

    @Override
    public Product findProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            throw new ObjectNotFoundException("Product", "id", id.toString());
        }
        return productOptional.get();
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepository.findByName("new");
        if (!categoryOptional.isPresent()){
            throw new ObjectCreationException("can not create product, category 'new' is not present (this is an internal error)");
        }
        Set<Category> categories = new HashSet<>();
        categories.add(categoryOptional.get());
        product.setCategories(categories);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        if (product.getId() == null) {
            throw new ObjectCreationException("Object has no id, You probably want to create a product");
        }
        if (product.getId() != id){
           throw new ObjectCreationException("Target product and updated product do not have the same id");
        }
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()){
            throw new ObjectNotFoundException("Prdoduct", "id", id.toString());
        }
        product = productRepository.save(product);
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        if (!checkIfExist(id)) {
            throw new ObjectNotFoundException("Product", "id", id.toString());
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            Product product = productRepository.findById(id).get();
            product.setSellable(false);
            productRepository.save(product);
            throw new DeleteException("Can not delete Sold products, made product unsellable");

        }
    }

    public boolean checkIfExist(Long id) {
        return productRepository.existsById(id);
    }
}
