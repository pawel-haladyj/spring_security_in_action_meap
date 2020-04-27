package pl.haladyj.SpringSecurity_configuring_authorization_CH6.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.time.LocalTime;

import static java.time.LocalTime.now;

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
                .roles("ADMIN") //followed by ROLE_ -> role, with no prefix -> authority
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

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        //String expression = "hasAuthority('READ') and !hasAuthority('DELETE')";
        //String expression2 = "hasRole('MANAGER')";
        Boolean expression3 = now().isAfter(LocalTime.of(5,0));
        String expression4 = expression3.toString();

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
                //.access(expression2);
                //.access(expression4);
                .denyAll(); // restricts access to all

    }*/

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.authorizeRequests()
                .mvcMatchers("/hello").hasRole("ADMIN") //aims endpoints and restricts aimed
                .mvcMatchers("/ciao").hasRole("MANAGER")
                //.anyRequest().permitAll(); //permits access to all other - optional -> to make code clear
                .anyRequest().authenticated(); //authenticated but not authorized
                                                //bad credentials - blank response, no content
                                                //no credentials - 401
                                                //matching credentials 200 + content

    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.csrf().disable();

/*        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/a")
                    .authenticated()
                .mvcMatchers(HttpMethod.POST, "/a")
                    .permitAll()
                .anyRequest()
                    .denyAll();*/

        http.authorizeRequests()
                .mvcMatchers("a/b/**")//using blank card -> different behaviour for a, b, c (b, c equal)
                    .authenticated()
                .anyRequest()
                    .permitAll();
        //all authorized users has access to all endpoints
        //not authenticated users have access to A, if reached B or C 403 is returned (not authorized)
        //bad credentials user has 401 at all endpoints
        // ** replaces any String pattern -> ranges any amount of levels
        // * replaces any String pattern -> ranges 1 level
    }
}
