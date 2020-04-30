package pl.haladyj.SpringSecurity_Managing_Users_And_Passwords_CH3.security;

import org.springframework.security.crypto.encrypt.BytesEncryptor;

public class ByteEncrypt implements BytesEncryptor {
    @Override
    public byte[] encrypt(byte[] byteArray) {
        return new byte[0];
    }

    @Override
    public byte[] decrypt(byte[] encryptedByteArray) {
        return new byte[0];
    }
}
