package pl.haladyj.SpringSecurity_Managing_Users_And_Passwords_CH3.security;

public interface PasswordEncoder {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
    default boolean upgradeEncoding(String encodedPassword){
        return false;
    }
}
