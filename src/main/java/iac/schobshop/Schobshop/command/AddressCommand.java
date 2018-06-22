package iac.schobshop.Schobshop.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class AddressCommand {
    @Size(min = 1, max = 50)
    private String street;
    @NotBlank
    @Size(min = 1, max = 20)
    private String number;
    @NotBlank
    @Pattern(regexp = "^[0-9]{4}[a-zA-Z]{2}$", message = "Postal code must start with 4 numbers and end with 2 small characters")
    private String postalCode;
    @Size(min = 2, max = 50)
    private String city;
    @Size(min = 2, max = 50)
    private String country;
}
