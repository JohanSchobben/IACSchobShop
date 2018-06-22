package iac.schobshop.Schobshop.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CustomerCommand {
    @Size(min = 1, max = 50)
    private String firstName;
    @Size(min = 1, max = 50)
    private String lastName;
}
