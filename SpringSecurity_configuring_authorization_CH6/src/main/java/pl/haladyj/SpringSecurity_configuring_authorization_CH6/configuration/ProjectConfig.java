package pl.haladyj.SpringSecurity_configuring_authorization_CH6.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var mananger = new InMemoryUserDetailsManager();

        var user1 = User
                .withUsername("john")
                .password("12345")
                .roles("ADMIN") //folowed by ROLE_ -> role, with no prefix -> authority
                .build();

        var user2 = User
                .withUsername("jane")
                .password("12345")
                .roles("MANAGER")
                .build();

        mananger.createUser(user1);
        mananger.createUser(user2);

        return mananger;


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        //String expression = "hasAuthority('READ') and !hasAuthority('DELETE')";
        String expression2 = "hasRole('MANAGER')";

        http
                .authorizeRequests() //specify requests on endpoints
                .anyRequest()        //refers any incomming request
                //.permitAll();        //allows access to all requests
                //.hasAuthority("WRITE"); //only one authority indicated in method
                //.hasAnyAuthority("WRITE", "READ"); // consider all indicated params
                //.access(expression);
                                                                    // different syntax, allows to have varing content
                                                                    // depending on the logic, most flexible of all
                                                                    // may use both: hasAuthority, hasAnyAuthority
                                                                    // and combine logic
                                                                    // input as a String -> parameter
                //.hasRole("ADMIN"); // refers to roles, not authorities - works like hasAuthority()
                //.hasAnyRole("ADMIN","MANAGER"); //similar to hasAnyAuthority() -> refers to roles, not authorities
                .access(expression2);

    }


}
