package iac.schobshop.Schobshop.Listeners;

import iac.schobshop.Schobshop.model.Account;
import iac.schobshop.Schobshop.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@AllArgsConstructor
@Component
public class UserListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private HttpSession httpSession;
    private AccountRepository accountRepository;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        UserDetails userDetails = (UserDetails) authenticationSuccessEvent.getAuthentication().getPrincipal();
        Account account = accountRepository.findByEmail(userDetails.getUsername());
        httpSession.setAttribute("account", account);
    }
}
