package iac.schobshop.Schobshop.service;

import iac.schobshop.Schobshop.exceptions.ObjectNotFoundException;
import iac.schobshop.Schobshop.model.Product;
import iac.schobshop.Schobshop.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void findAllProducts() {
        Set<Product> products = new HashSet<>();
        Product product = new Product();
        product.setId(1L);

        Product product1 = new Product();
        product1.setId(2L);

        products.add(product);
        products.add(product1);

        when(productRepository.findAll()).thenReturn(products);
        Set<Product> productsResult = productService.findAllProducts();

        assertEquals(products, productsResult);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void findProductById() {
        Long id = 1L;
        Product product = new Product();
        product.setId(id);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        Product productResult = productService.findProductById(id);

        assertEquals(product, productResult);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void findProductByIdNull(){
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());
        Product product = productService.findProductById(id);
    }

    @Test
    public void createProduct() {

    }

    @Test
    public void updateProduct() {
    }

    @Test
    public void deleteProduct() {
    }
}