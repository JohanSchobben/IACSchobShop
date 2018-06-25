package iac.schobshop.Schobshop.converter;

import iac.schobshop.Schobshop.model.PurchaseLine;
import iac.schobshop.Schobshop.model.ShoppingCartLine;
import iac.schobshop.Schobshop.service.ProductService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartLineToPurchaseLine implements Converter<ShoppingCartLine, PurchaseLine> {

    private ProductService productService;

    public ShoppingCartLineToPurchaseLine(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public PurchaseLine convert(ShoppingCartLine shoppingCartLine) {
        PurchaseLine purchaseLine = new PurchaseLine();
        purchaseLine.setProduct(productService.findProductById(shoppingCartLine.getProductId()));
        purchaseLine.setAmount(shoppingCartLine.getAmount());
        purchaseLine.setPrice();
        return purchaseLine;
    }
}
