package iac.schobshop.Schobshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String role;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( name = "account_role",
            joinColumns = { @JoinColumn(name = "role_id")},
            inverseJoinColumns = { @JoinColumn(name = "account_id")})
    private Set<Account> accounts;


}
