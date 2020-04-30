package pl.haladyj.SpringSecurity_Managing_Users_And_Passwords_CH3.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserManagementConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails u = User
                .withUsername("bill")
                .authorities("read","write")
                .accountExpired(false)
                .disabled(true)
                .build();
    }
}
