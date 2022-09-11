package ir.maktab.pairKey.partOne;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
//RSA Encrypt/Decrypt Ciphers for Client-Server Application : Android | Spring
public class RSA {
    private PrivateKey privatekey;
    private PublicKey publicKey;

    public RSA() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair pair = generator.generateKeyPair();
            privatekey = pair.getPrivate();
            publicKey = pair.getPublic();
        } catch (Exception ignored) {
        }
    }
    //decrypt  ===> رمزگشایی    encrypt ===>رمزگذاری      cipher==>  رمزگذاری   encode ==>     decode ==>


    //let’s create two methods to encode and decode Strings using the Base64 encoder and decoder
    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }


    //Let’s make the encrypt method

    //Don’t forget that the message you want to encrypt needs to be converted to bytes
    // the result of the encryption => cipher.doFinal(byte[]) => is a byte array that can be encoded to get the encrypted message
    public String encrypt(String message) throws Exception {//رمزگذاری
        byte[] messageToBytes = message.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
        return encode(encryptedBytes);
    }


    //decryption method is similar to the encryption but inverted.
    // Starting from decoding the string then initializing the cipher with the DECRYPT_MODE and returning the decrypted bytes using new String(bytes[])

    public String decrypt(String encryptedMessage) throws Exception {//رمزگشایی
        byte[] encryptedBytes = decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privatekey);
        byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
        return new String(decryptedMessage, "UTF8");
    }


    public static void main(String[] args) {
        RSA rsa = new RSA();
        try {
            String encryptedMessage = rsa.encrypt("Hello World");
            String decryptedMessage = rsa.decrypt(encryptedMessage);

            System.err.println("Encrypted (رمزگذاری شده):\n" + encryptedMessage);
            System.err.println("Decrypted(رمزگشایی شده):\n" + decryptedMessage);

        } catch (Exception ingored) {
        }
    }
}

