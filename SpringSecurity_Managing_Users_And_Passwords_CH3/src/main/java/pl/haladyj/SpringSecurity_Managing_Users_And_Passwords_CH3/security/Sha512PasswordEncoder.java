package pl.haladyj.SpringSecurity_Managing_Users_And_Passwords_CH3.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha512PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return hashWithSHA512(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String hashedPassword = hashWithSHA512(rawPassword.toString());
        return encodedPassword.equals(hashedPassword);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }

    private String hashWithSHA512(String input){
        StringBuilder result = new StringBuilder();
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte [] digest = md.digest(input.getBytes());
            for(int i =0; i<digest.length;i++){
                result.append(Integer.toHexString(0xFF & digest[i]));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        return result.toString();
    }
}
