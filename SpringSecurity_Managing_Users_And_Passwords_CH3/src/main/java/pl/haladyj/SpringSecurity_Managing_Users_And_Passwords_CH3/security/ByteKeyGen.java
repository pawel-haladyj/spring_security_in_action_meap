package pl.haladyj.SpringSecurity_Managing_Users_And_Passwords_CH3.security;

import org.springframework.security.crypto.keygen.BytesKeyGenerator;

public class ByteKeyGen implements BytesKeyGenerator {
    @Override
    public int getKeyLength() {
        return 0;
    }

    @Override
    public byte[] generateKey() {
        return new byte[0];
    }
}
