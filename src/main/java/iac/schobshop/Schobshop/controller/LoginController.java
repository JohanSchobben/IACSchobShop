package iac.schobshop.Schobshop.controller;

import iac.schobshop.Schobshop.command.RegistrationCommand;
import iac.schobshop.Schobshop.service.AccountService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class LoginController {

    private AccountService accountService;

    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegistrationAccount(Model model){
        model.addAttribute("registration", new RegistrationCommand());
        return "account/registration";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String registrateShopper(@Valid @ModelAttribute("registration") RegistrationCommand registrationCommand, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "account/registration";
        }
        accountService.registerAccount(registrationCommand);
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "account/login";
    }

    @RequestMapping(value = "/profile")
    public String profilePage(Model model, HttpServletRequest request){

        return "account/profile";
    }
}
