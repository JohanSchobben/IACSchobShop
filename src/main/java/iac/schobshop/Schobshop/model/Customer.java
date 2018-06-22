package iac.schobshop.Schobshop.model;

import com.sun.jndi.cosnaming.IiopUrl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToOne(mappedBy = "customer")
    private Account account;
}
