package ua.edu.chmnu.fks.oop.crypto.keypair;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Encoder {
    private Encoder() {}
    
    private static byte[] randomKey(int length) {
        byte[] buffer = new byte[length];
        new Random(System.currentTimeMillis()).nextBytes(buffer);
        return buffer;
    }
    
    public static KeyPair encrypt(String s, Charset charset) {
        byte[] source = s.getBytes(charset);
        byte[] key = randomKey(source.length);
        byte[] encryptedData = new byte[source.length];
        for (int i = 0; i < source.length; ++i) {
            encryptedData[i] = (byte)(source[i] ^ key[i]);
        }
        return new KeyPair(key, encryptedData);
    }
    
    public static KeyPair encrypt(String s) {
        return encrypt(s, StandardCharsets.UTF_8);
    }
}
