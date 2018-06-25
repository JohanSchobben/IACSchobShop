package iac.schobshop.Schobshop.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;

@Getter
@Setter
@NoArgsConstructor
public class OrderCommand {
    @Valid
    private AddressCommand address;
    @AssertTrue
    private boolean confirmation;
}
