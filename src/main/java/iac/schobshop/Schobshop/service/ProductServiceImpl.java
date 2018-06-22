package iac.schobshop.Schobshop.service;

import com.google.common.collect.Sets;
import iac.schobshop.Schobshop.exceptions.ObjectNotFoundException;
import iac.schobshop.Schobshop.model.Product;
import iac.schobshop.Schobshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public Set<Product> findAllProducts() {
        Iterable<Product> products = productRepository.findAll();
        return Sets.newHashSet(products);
    }

    @Override
    public Product findProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(!productOptional.isPresent()){
            throw new ObjectNotFoundException("Product", "id", id.toString());
        }
        return productOptional.get();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
