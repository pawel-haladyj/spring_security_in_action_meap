package pl.haladyj.SpringSecurity_Managing_Users_And_Passwords_CH3.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class UserManagementConfig {

    @Bean
    public UserDetailsService userDetailsService(){
/*        UserDetails u = User
                .withUsername("bill")
                .authorities("read","write")
                .accountExpired(false)
                .disabled(true)
                .build();*/

            User.UserBuilder builder1 = User.withUsername("bill");

            UserDetails u = builder1
                    .password("12345")
                    .authorities("read","write")
                    .accountExpired(false)
                    .disabled(true)
                    .build();

            User.UserBuilder builder2 = User.withUserDetails(u);

            return (UserDetailsService) builder2;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
