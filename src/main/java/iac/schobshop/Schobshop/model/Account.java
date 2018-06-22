package iac.schobshop.Schobshop.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Setter
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private @Getter String email;
    private String password;
    private boolean active;
    @OneToOne(cascade = CascadeType.ALL)
    private @Getter Customer customer;
    @OneToOne(cascade = CascadeType.ALL)
    private @Getter Address billingAddress;
    private boolean credentialsExpired;
    @Lob
    private @Getter Byte[] profilePicture;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( name = "account_role",
            joinColumns = { @JoinColumn(name = "account_id")},
            inverseJoinColumns = { @JoinColumn(name = "role_id")})
    private @Getter @Setter Set<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
