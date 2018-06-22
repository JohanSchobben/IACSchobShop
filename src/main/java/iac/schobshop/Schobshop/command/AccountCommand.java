package iac.schobshop.Schobshop.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class AccountCommand {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(max = 64, min = 8)
    private String password;
    @NotBlank
    private String passwordConfirm;
    private MultipartFile profilePicture;
    private AddressCommand addressCommand;
    private CustomerCommand customerCommand;

}
