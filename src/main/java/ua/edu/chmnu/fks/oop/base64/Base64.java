package ua.edu.chmnu.fks.oop.base64;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Base64 {
    private Base64() {}
    
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    
    public static String encode(String input, Charset charset) {
        if (input == null || input.isEmpty()) {
            throw new Base64Exception("Empty input for Base64");
        }
        StringBuilder builder = new StringBuilder();
    
        int[] buffer = new int[3];
        
        for (int i = 0; i < input.length(); ++i) {
            buffer[i % 3] = input.charAt(i);
            if ( (i+1) % 3 == 0 ) {
                builder.append(encodeBuffer(buffer, false));
            }
        }

        if (input.length() % 3 != 0) {
            builder.append(encodeBuffer(buffer, true));
        }

        return builder.toString();
    }
    
    public static String encode(String input) {
        return encode(input, StandardCharsets.UTF_8);
    }
    
    private static String encodeBuffer(int[] buffer, boolean withFinal) {
        int[] codes = new int[4];
        
        codes[0] = buffer[0] >> 2;
        codes[1] = ((buffer[0] & 3) << 4) | (buffer[1] >> 4);
        codes[2] = ((buffer[1] & 15) << 2) | (buffer[2] >> 6);
        codes[3] = buffer[2] & 63;
    
        StringBuilder builder = new StringBuilder();
        for (int code: codes) {
            if (code == 0 && withFinal) {
                code = 64;
            }
            builder.append(ALPHABET.charAt(code));
        }
        Arrays.fill(buffer, 0);
        return builder.toString();
    }
}
