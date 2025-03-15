package utils;

import org.aeonbits.owner.crypto.Decryptor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CipherManager implements Decryptor {
    private static final Logger log = LoggerFactory.getLogger(CipherManager.class);
    private static final SecretKey SECRET_KEY;

    static {
        String secret = System.getProperty("secret");
        if (StringUtils.isNotEmpty(secret)) {
            SECRET_KEY = new SecretKeySpec(secret.getBytes(), "AES");
        } else {
            SECRET_KEY = null;
            log.warn("Secret key is not set");
        }
    }

    private static synchronized String cipherAction(int cipherMode, SecretKey secretKey, String value) {
        String returnValue = StringUtils.EMPTY;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(cipherMode, secretKey);
            returnValue = switch (cipherMode) {
                case Cipher.ENCRYPT_MODE -> new String(Base64.getEncoder().encode(cipher.doFinal(value.getBytes())));
                case Cipher.DECRYPT_MODE -> new String(cipher.doFinal(Base64.getDecoder().decode(value)));
                default -> throw new RuntimeException("Unknown cipher mode");
            };
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException |
                 NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public String decrypt(SecretKey secretKey, String encryptedValue) {
        return cipherAction(Cipher.DECRYPT_MODE, secretKey, encryptedValue);
    }

    public String encrypt(SecretKey secretKey, String password) {
        return cipherAction(Cipher.ENCRYPT_MODE, secretKey, password);
    }

    public String encrypt(String secret, String password) {
        return encrypt(new SecretKeySpec(secret.getBytes(), "AES"), password);
    }

    public String decrypt(String encryptedValue) {
        return decrypt(SECRET_KEY, encryptedValue);
    }

    public String decrypt(String secret, String encryptedValue) {
        return decrypt(new SecretKeySpec(secret.getBytes(), "AES"), encryptedValue);
    }
}
