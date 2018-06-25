package iac.schobshop.Schobshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class ShoppingCart {
    private ArrayList<ShoppingCartLine> items;
    private double totalPrice;
}
