package com.freeAppListing

import org.apache.commons.codec.binary.Base64
import sun.misc.BASE64Decoder

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter;

class SecurityService {


    private static byte[] key = "12345678".getBytes();// 64 bit
    private static byte[] iv = "12345678".getBytes();

    def String encryt(String data){

        String cypert = data;
        try {
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec k = new SecretKeySpec(key, "DES");
            Cipher c = Cipher.getInstance("DES/CBC/PKCS7Padding");
            c.init(Cipher.ENCRYPT_MODE, k, ivSpec);
            byte[] encryptedData = c.doFinal(data.getBytes());
            cypert = Base64.encodeBase64String(encryptedData);
        } catch (Exception e) {
            e.printStackTrace()
        }
        return cypert;
    }

    def String decrypt(String text){
        String plain=text;
        try {
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec keys = new SecretKeySpec(key, "DES");
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS7Padding");
            cipher.init(Cipher.DECRYPT_MODE, keys, ivSpec);
            // decryption pass
            byte[] cipherText = Base64.decodeBase64(text);
            int ctLength = cipherText.length;
            byte[] plainText = new byte[cipher.getOutputSize(ctLength)];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bos.write(cipher.doFinal(cipherText));
            plainText = bos.toByteArray();
            bos.close();
            plain = new String(plainText, "UTF8");
        } catch (Exception e) {
            e.printStackTrace()
        }
        return plain;
    }

}



