package ua.edu.chmnu.fks.oop.crypto.cipher;

public class CesarCipher {
    private static int ALPHA_LEN = 26;

    private static char encodeChar(char c, char start, int offset) {
        int codeOffs = c - start;
        int newPos = (codeOffs + offset) % ALPHA_LEN;
        return (char)(start + newPos);
    }

    public static String encode(String src, int offset) {
        char[] array = src.toCharArray();
        for (int i = 0; i < array.length; ++i) {
            if (array[i] >= 'A' && array[i] <= 'Z') {
                array[i] = encodeChar(array[i], 'A', offset);
            } else if (array[i] >= 'a' && array[i] <= 'z') {
                array[i] = encodeChar(array[i], 'a', offset);
            }
        }
        return new String(array);
    }

    public static void main(String[] args) {
        for (char c = 'A'; c <= 'z'; ++c) {
            if (Character.isAlphabetic(c)) {
                System.out.print(c + " ");
            }
        }
        System.out.println();
        String s1 = "Hello, zOomer";
        System.out.println(s1);

        String s2 = encode(s1, 3);
        System.out.println(s2);
        String s3 = encode(s1, 5);
        System.out.println(s3);
    }
}
