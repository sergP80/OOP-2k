package ua.edu.chmnu.fks.oop.crypto.keypair;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Decoder {
    private Decoder() {}
    
    public static String decrypt(KeyPair keyPair, Charset charset) {
        byte[] result = new byte[keyPair.getKey().length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = (byte)(keyPair.getKey()[i] ^ keyPair.getData()[i]);
        }
        return new String(result, charset);
    }
    
    public static String decrypt(KeyPair keyPair) {
        return decrypt(keyPair, StandardCharsets.UTF_8);
    }
    }
