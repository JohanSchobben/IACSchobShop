package iac.schobshop.Schobshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "address")
    private Customer customer;
    @OneToOne(mappedBy = "billingAddress")
    private Account account;
    private String street;
    private String number;
    private String postalCode;
    private String city;
    private String country;
}
