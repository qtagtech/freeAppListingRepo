package utility


import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


class SecurityService {

    private String key = "Bar12345Bar12345";
    private Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
    private Cipher cipher = Cipher.getInstance("AES");

    def String encrypt(String text){
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        return new String(encrypted);
    }

    def String desEncrypt(String text){
        byte[] text1 = text.getBytes()
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(text1));
        return decrypted;

    }

}
