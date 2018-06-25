package iac.schobshop.Schobshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PurchaseLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Product product;
    private int amount;
    @Column(columnDefinition = "decimal")
    private double price;
    @ManyToOne
    private Purchase purchase;



    public void setPrice(){
        this.price = this.product.getPrice() * this.amount;
    }
}
