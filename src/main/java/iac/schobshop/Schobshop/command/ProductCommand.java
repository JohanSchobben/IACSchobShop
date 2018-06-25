package iac.schobshop.Schobshop.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ProductCommand {
    private Long id;
    @NotBlank
    @Size(max = 50)
    private String name;
    @Size(max = 8000)
    private String Description;
    @Min(0)
    @Max(99999999)
    private double price;
    private boolean sellable;

}
