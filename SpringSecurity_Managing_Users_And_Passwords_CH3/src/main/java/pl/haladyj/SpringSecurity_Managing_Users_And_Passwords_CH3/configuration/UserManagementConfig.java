package pl.haladyj.SpringSecurity_Managing_Users_And_Passwords_CH3.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultLdapUsernameToDnMapper;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import pl.haladyj.SpringSecurity_Managing_Users_And_Passwords_CH3.security.PlainTextPasswordEncoder;
import pl.haladyj.SpringSecurity_Managing_Users_And_Passwords_CH3.security.Sha512PasswordEncoder;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class UserManagementConfig {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){

        String usersByUsernameQuery = "SELECT username, password, enabled FROM users WHERE username = ?";

        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);

        return userDetailsManager;
    }

/*    @Bean
    public UserDetailsService userDetailsService() {
        var cs = new DefaultSpringSecurityContextSource("ldap://127.0.0.1:33389/dc=springframework,dc=org");
        cs.afterPropertiesSet();

        LdapUserDetailsManager manager = new LdapUserDetailsManager(cs);
        manager.setUsernameMapper(new DefaultLdapUsernameToDnMapper("ou=user", "cn"));
        manager.setGroupSearchBase("ou=groups");

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/

    /**
     * we are implementing 5 different waus of encrypting password, of which 2 own omplementation
     * each password receives a prefix in database, due to which password encoder is able to mach proper encoder
     * in this example NoOp instance 12345 is present in DB as {noop}12345
     * without a prefix password is unmetchable, invisible.
     *
     * as well we pisk up default way of encrypting password, in this example it would be bcrypt
     * @return
     */
    public PasswordEncoder passwordEncoder(){
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("sha512",new Sha512PasswordEncoder());
        encoders.put("plaintext", new PlainTextPasswordEncoder());

        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }

    /**
     * as above
     */
    //public PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    StringKeyGenerator stringKeyGenerator = KeyGenerators.string();
    String salt = stringKeyGenerator.generateKey();

    BytesKeyGenerator bytesKeyGenerator = KeyGenerators.secureRandom(16); //if empty by default it is 8
    byte[] key = bytesKeyGenerator.generateKey();
    int keyLength = bytesKeyGenerator.getKeyLength();

    BytesKeyGenerator bytesKeyGenerator2 = KeyGenerators.shared(16);
    byte[] key1 = bytesKeyGenerator2.generateKey();
    byte[] key2 = bytesKeyGenerator2.generateKey(); //key1==key2

    String saltX = KeyGenerators.string().generateKey();
    String passwordX = "secret";
    String valueToEncrypt = "HELLO";

    BytesEncryptor e = Encryptors.standard(passwordX,saltX);
    byte[] encryptedX = e.encrypt(valueToEncrypt.getBytes());
    byte[] decryptedX = e.decrypt(encryptedX);

    BytesEncryptor eS = Encryptors.stronger(passwordX,saltX);

    String valueToEncrypt2 = "Hello";
    TextEncryptor te = Encryptors.noOpText();
    String encrypted2 = te.encrypt(valueToEncrypt2);


}
