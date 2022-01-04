package ua.edu.chmnu.fks.oop.crypto.keypair;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncoderDecoderTest {
    
    @ParameterizedTest
    @ValueSource(strings = {"Hello, world!", "Мама мыла раму!!!", "Be or not to be!"})
    public void shouldSuccess(String data) {
        KeyPair keyPair = Encoder.encrypt(data);
        String result = Decoder.decrypt(keyPair);
        assertEquals(data, result);
    }
}
