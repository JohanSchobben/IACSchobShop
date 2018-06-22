package iac.schobshop.Schobshop.command;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationCommand {
    @Valid
    private AccountCommand account;
    @Valid
    private AddressCommand address;
    @Valid
    private CustomerCommand customer;

}
