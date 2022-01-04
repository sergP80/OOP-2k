package ua.edu.chmnu.fks.oop.crypto.keypair;

public class KeyPair {
    private final byte[] key;
    private final byte[] data;
    
    public KeyPair(byte[] key, byte[] data) {
        this.key = key;
        this.data = data;
    }
    
    public byte[] getKey() {
        return key;
    }
    
    public byte[] getData() {
        return data;
    }
}
