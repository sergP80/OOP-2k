package ua.edu.chmnu.fks.oop.cipher;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ua.edu.chmnu.fks.oop.crypto.cipher.CesarCipher.encode;

public class CesarCipherTest {

    @Test
    public void test01() {
        String s0 = "Hello, zOomer";
        int offset = 3;
        String s1 = "Khoor, cRrphu";
        String s2 = encode(s0, offset);
        assertEquals(s1, s2);
    }

    @Test
    public void test02() {
        String s0 = "Hello, zOomer";
        int offset = 5;
        String s1 = "Mjqqt, eTtrjw";
        String s2 = encode(s0, offset);
        assertEquals(s1, s2);
    }
}
