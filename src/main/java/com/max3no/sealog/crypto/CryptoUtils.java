package com.max3no.sealog.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtils {

    private static final String KEY = "sealog-secret-123"; // 16-char AES key
    private static final String ALGO = "AES";

    public static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGO);
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encVal = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encVal);
    }

    public static String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGO);
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), ALGO);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decValue = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decValue);
    }
}
