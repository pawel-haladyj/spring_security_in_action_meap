package pl.haladyj.SpringSecurity_Managing_Users_And_Passwords_CH3.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PlainTextPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
