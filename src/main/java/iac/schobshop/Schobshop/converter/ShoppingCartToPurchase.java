package iac.schobshop.Schobshop.converter;

import iac.schobshop.Schobshop.model.Purchase;
import iac.schobshop.Schobshop.model.PurchaseLine;
import iac.schobshop.Schobshop.model.ShoppingCart;
import iac.schobshop.Schobshop.model.ShoppingCartLine;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class ShoppingCartToPurchase implements Converter<ShoppingCart, Purchase> {

    private ShoppingCartLineToPurchaseLine shoppingCartLineToPurchaseLine;

    public ShoppingCartToPurchase(ShoppingCartLineToPurchaseLine shoppingCartLineToPurchaseLine) {
        this.shoppingCartLineToPurchaseLine = shoppingCartLineToPurchaseLine;
    }

    @Override
    public Purchase convert(ShoppingCart shoppingCart) {
        Purchase purchase = new Purchase();
        Set<PurchaseLine> purchaseLines = new HashSet<>();
        for (ShoppingCartLine shoppingCartLine: shoppingCart.getItems()){
            PurchaseLine purchaseLine = shoppingCartLineToPurchaseLine.convert(shoppingCartLine);
            purchaseLines.add(purchaseLine);
            purchaseLine.setPurchase(purchase);
        }
        purchase.setPurchaseLines(purchaseLines);
        return purchase;
    }
}
