package iac.schobshop.Schobshop.controller;

import iac.schobshop.Schobshop.model.ShoppingCart;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ShoppingCartController {

    private final String SHOPPING_CART_SESSION_NAME = "shoppingCart";

    @RequestMapping(value = "/shoppingcart/get", method = RequestMethod.GET, produces = "application/json")
    public ShoppingCart getShoppingCart(HttpSession session){
        if (session.getAttribute(SHOPPING_CART_SESSION_NAME) == null){
            session.setAttribute(SHOPPING_CART_SESSION_NAME, new ShoppingCart());
        }
        return (ShoppingCart) session.getAttribute(SHOPPING_CART_SESSION_NAME);
    }
    @RequestMapping(value = "/shoppingcart/update", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ShoppingCart setShoppingCart(@RequestBody ShoppingCart shoppingCart, HttpSession session){
        session.setAttribute(SHOPPING_CART_SESSION_NAME, shoppingCart);
        return shoppingCart;
    }
}
