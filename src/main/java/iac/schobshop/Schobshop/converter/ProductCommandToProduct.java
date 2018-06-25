package iac.schobshop.Schobshop.converter;

import iac.schobshop.Schobshop.command.ProductCommand;
import iac.schobshop.Schobshop.model.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductCommandToProduct implements Converter<ProductCommand, Product>{
    @Override
    public Product convert(ProductCommand command) {
        Product product = new Product();
        product.setId(command.getId());
        product.setName(command.getName());
        product.setDescription(command.getDescription());
        product.setPrice(command.getPrice());
        product.setSellable(command.isSellable());
        return product;
    }
}
