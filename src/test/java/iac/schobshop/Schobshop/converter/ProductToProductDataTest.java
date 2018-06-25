package iac.schobshop.Schobshop.converter;

import iac.schobshop.Schobshop.data.ProductData;
import iac.schobshop.Schobshop.model.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductToProductDataTest {

    private ProductToProductData productToProductData;

    @Before
    public void setUp(){
        productToProductData = new ProductToProductData();
    }

    @Test
    public void convert(){
        String productName = "a product";
        String description = "a description";
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName(productName);
        product.setDescription(description);
        ProductData productData = productToProductData.convert(product);
        assertEquals(product.getName(),productData.getName());
        assertEquals(product.getDescription(), productData.getDescription());
        assertEquals(productData.getProductPicture(), "/product/image/1");
    }

}