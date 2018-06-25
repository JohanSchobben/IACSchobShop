package iac.schobshop.Schobshop.controller;

import iac.schobshop.Schobshop.command.OrderCommand;
import iac.schobshop.Schobshop.converter.AddressCommandToAddress;
import iac.schobshop.Schobshop.converter.AddressToAddressCommand;
import iac.schobshop.Schobshop.converter.ShoppingCartToPurchase;
import iac.schobshop.Schobshop.model.*;
import iac.schobshop.Schobshop.service.AccountService;
import iac.schobshop.Schobshop.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

@AllArgsConstructor
@Controller
public class PurchaseController {

    private ShoppingCartToPurchase shoppingCartToPurchase;
    private AddressToAddressCommand addressToAddressCommand;
    private AddressCommandToAddress addressCommandToAddress;
    private PurchaseService purchaseService;

    @RequestMapping(value = "/order")
    public String makeOrder(Model model, HttpSession session){
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        if(shoppingCart.getItems().size() == 0){
            return "redirect:/";
        }
        Account account = (Account) session.getAttribute("account");
        Purchase purchase = shoppingCartToPurchase.convert(shoppingCart);
        purchase.setPurchaseDate(new Date());
        Address address =  account.getBillingAddress();
        purchase.setPurchaseState(PurchaseState.IN_PROGRESS);
        purchase.setAccount(account);
        OrderCommand orderCommand = new OrderCommand();
        orderCommand.setAddress(addressToAddressCommand.convert(address));
        session.setAttribute("purchase", purchase);
        model.addAttribute("purchaseCommand", orderCommand);
        return "shop/order";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String makeOrder(@Valid @ModelAttribute("purchasecommand") OrderCommand orderCommand, HttpSession session, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "shop/order";
        }
        Address address = addressCommandToAddress.convert(orderCommand.getAddress());
        Purchase purchase = (Purchase) session.getAttribute("purchase");
        purchase.setDeliveryAddress(address);
        purchaseService.makePurchase(purchase);
        session.removeAttribute("shoppingCart");
        session.removeAttribute("purchase");
        return "redirect:/";
    }
}
