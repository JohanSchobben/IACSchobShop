package iac.schobshop.Schobshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date purchaseDate;
    @ManyToOne
    private Account account;
    @OneToOne(cascade = CascadeType.ALL)
    private Address deliveryAddress;
    @Enumerated(value = EnumType.STRING)
    private PurchaseState purchaseState;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchase")
    private Set<PurchaseLine> purchaseLines;

    @Transient
    public double getTotalPrice(){
        double totalPrice = 0;
        for (PurchaseLine purchaseLine : purchaseLines){
            totalPrice+= purchaseLine.getPrice();
        }
        return totalPrice;
    }
}
