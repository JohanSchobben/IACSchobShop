package iac.schobshop.Schobshop.converter;

import iac.schobshop.Schobshop.data.ProductData;
import iac.schobshop.Schobshop.model.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductData implements Converter<Product, ProductData> {
    private final String productPicture = "/product/image/";
    @Override
    public ProductData convert(Product product) {
        ProductData productData = new ProductData();
        productData.setId(product.getId());
        productData.setName(product.getName());
        productData.setPrice(product.getPrice());
        productData.setSellable(product.isSellable());
        productData.setDescription(product.getDescription());
        productData.setProductPicture(productPicture + product.getId().toString());
        return productData;
    }
}
