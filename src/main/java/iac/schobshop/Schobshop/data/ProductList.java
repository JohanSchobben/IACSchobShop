package iac.schobshop.Schobshop.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.Resource;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProductList {
    private Set<Resource<ProductData>> products;
}
