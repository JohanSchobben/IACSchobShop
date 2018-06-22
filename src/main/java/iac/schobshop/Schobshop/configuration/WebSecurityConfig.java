package iac.schobshop.Schobshop.configuration;

import iac.schobshop.Schobshop.model.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final String userQuery = "select email, password, active from account where email=?";
    private final String rolesQuery = "select a.email, r.role from account a inner join account_role ar on(a.id=ar.account_id) inner join role r on(ar.role_id=r.id) where a.email=?";

    public WebSecurityConfig(DataSource dataSource, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.dataSource = dataSource;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .jdbcAuthentication()
                .usersByUsernameQuery(userQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home","/index", "/").permitAll()
                .antMatchers("/category").permitAll()
                .antMatchers("/profile")
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/profile")
                    .usernameParameter("email")
                    .passwordParameter("password").permitAll();
    }
}
