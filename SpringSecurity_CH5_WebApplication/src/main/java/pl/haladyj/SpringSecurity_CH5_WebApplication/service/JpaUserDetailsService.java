package pl.haladyj.SpringSecurity_CH5_WebApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.haladyj.SpringSecurity_CH5_WebApplication.entity.User;
import pl.haladyj.SpringSecurity_CH5_WebApplication.model.CustomUserDetails;
import pl.haladyj.SpringSecurity_CH5_WebApplication.repository.UserRepository;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Supplier<UsernameNotFoundException> supplier =
                ()->new UsernameNotFoundException("Username not found");

        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(supplier);

        return new CustomUserDetails(user);
    }
}
