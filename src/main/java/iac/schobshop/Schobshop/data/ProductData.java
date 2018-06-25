package iac.schobshop.Schobshop.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductData {
    private Long id;
    private String name;
    private String Description;
    private double price;
    private boolean sellable;
    private String productPicture;
}
