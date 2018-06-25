package iac.schobshop.Schobshop.converter;

import iac.schobshop.Schobshop.command.ProductCommand;
import iac.schobshop.Schobshop.model.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductCommandToProductTest {

    private ProductCommandToProduct productCommandToProduct;

    @Before
    public void setUp() throws Exception {
        productCommandToProduct = new ProductCommandToProduct();
    }

    @Test
    public void convert() {
        ProductCommand productCommand = new ProductCommand();
        productCommand.setDescription("a description");
        productCommand.setId(1L);
        Product productResult = productCommandToProduct.convert(productCommand);
        assertEquals(productCommand.getDescription(),productResult.getDescription());
        assertEquals(productCommand.getId(), productResult.getId());
        assertNull(productResult.getName());
    }
}