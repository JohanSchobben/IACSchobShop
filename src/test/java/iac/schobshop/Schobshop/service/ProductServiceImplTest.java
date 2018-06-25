package iac.schobshop.Schobshop.service;

import iac.schobshop.Schobshop.exceptions.DeleteException;
import iac.schobshop.Schobshop.exceptions.ObjectCreationException;
import iac.schobshop.Schobshop.exceptions.ObjectNotFoundException;
import iac.schobshop.Schobshop.model.Category;
import iac.schobshop.Schobshop.model.Product;
import iac.schobshop.Schobshop.repository.CategoryRepository;
import iac.schobshop.Schobshop.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository, categoryRepository);
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
        String categoryName = "new";
        Category category = new Category();
        category.setName(categoryName);
        Product product = new Product();
        product.setId(1L);
        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(category));
        when(productRepository.save(product)).thenReturn(product);
        Product productResult = productService.createProduct(product);
        assertEquals(product, productResult);
        verify(categoryRepository, times(1)).findByName(categoryName);
        verify(productRepository, times(1)).save(product);
        assertEquals(product.getCategories().size(),1);
    }
    @Test(expected = ObjectCreationException.class)
    public void createProductNoNew(){
        String categoryName = "new";
        Category category = new Category();
        category.setName(categoryName);
        Product product = new Product();
        product.setId(1L);
        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.empty());
        productService.createProduct(product);
    }

    @Test
    public void updateProduct() {
        Long id = 1L;
        String name = "test Product";
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        Product productResult = productService.updateProduct(product, id);
        assertEquals(product,productResult);
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).save(product);
    }

    @Test(expected = ObjectCreationException.class)
    public void updateProductNullIdProduct(){
        Long id = 1L;
        String name = "test Product";

        Product product = new Product();
        product.setName(name);


        productService.updateProduct(product, id);

        verify(productRepository, times(0)).findById(id);
        verify(productRepository, times(0)).save(product);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void updateProductNoProduct(){
        Long id = 1L;
        String name = "test Product";

        Product product = new Product();
        product.setId(id);
        product.setName(name);

        when(productRepository.findById(id)).thenReturn(Optional.empty());
        when(productRepository.save(product)).thenReturn(product);

        productService.updateProduct(product, id);

        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(0)).save(product);
    }

    @Test(expected = ObjectCreationException.class)
    public void updateProductIdNotEqual(){
        Long id = 1L;
        Long otherId = 2L;
        String name = "test Product";

        Product product = new Product();
        product.setId(id);
        product.setName(name);

        when(productRepository.findById(id)).thenReturn(Optional.empty());
        when(productRepository.save(product)).thenReturn(product);

        productService.updateProduct(product, otherId);

        verify(productRepository, times(0)).findById(id);
        verify(productRepository, times(0)).save(product);

    }


    @Test
    public void deleteProduct() {
        Long id = 1L;
        when(productService.checkIfExist(id)).thenReturn(true);
        productService.deleteProduct(id);
        verify(productRepository,times(1)).existsById(id);
        verify(productRepository, times(1)).deleteById(id);
        verify(productRepository, times(0)).findById(id);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void deleteProductNotExisting() {
        Long id = 1L;
        when(productService.checkIfExist(id)).thenReturn(false);
        productService.deleteProduct(id);
        verify(productRepository,times(1)).existsById(id);
        verify(productRepository, times(0)).deleteById(id);
        verify(productRepository, times(0)).findById(id);
    }

    @Test(expected = DeleteException.class)
    public void deleteProductAlreadySold() {
        Long id = 1L;
        String productName = "Test Product";
        Product product = new Product();
        product.setId(id);
        product.setName(productName);
        product.setSellable(true);

        when(productService.checkIfExist(id)).thenReturn(true);
        doThrow(new DataIntegrityViolationException("test message")).when(productRepository).deleteById(id);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        productService.deleteProduct(id);

        assertFalse(product.isSellable());
        verify(productRepository,times(1)).existsById(id);
        verify(productRepository, times(1)).deleteById(id);
        verify(productRepository, times(1)).findById(id);
    }
}