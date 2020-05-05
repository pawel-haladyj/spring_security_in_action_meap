package pl.haladyj.SpringSecurity_CH4_ImplementingAuthentication.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import pl.haladyj.SpringSecurity_CH4_ImplementingAuthentication.handler.CustomAuthenticationFailureHandler;
import pl.haladyj.SpringSecurity_CH4_ImplementingAuthentication.handler.CustomAuthenticationSuccessHandler;
import pl.haladyj.SpringSecurity_CH4_ImplementingAuthentication.security.CustomAuthenticationProvider;

@Configuration
@EnableAsync
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public UserDetailsService userDetailsService() {

        var uds = new InMemoryUserDetailsManager();
        var u = User
                .withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        uds.createUser(u);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public InitializingBean initializingBean(){
        return ()-> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic(c->{
            c.realmName("OTHER");
            c.authenticationEntryPoint(new CustomEntryPoint());
        });
        http
                .formLogin()
                //.defaultSuccessUrl("/home",true);
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler);

        http.authorizeRequests().anyRequest().authenticated();
    }
}
