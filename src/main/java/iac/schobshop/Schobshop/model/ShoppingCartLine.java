package iac.schobshop.Schobshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShoppingCartLine {
    private Long productId;
    private String productName;
    private int Amount;
    private double price;
}


// todo valiatate item sellable
// todo offers
// todo unit testing