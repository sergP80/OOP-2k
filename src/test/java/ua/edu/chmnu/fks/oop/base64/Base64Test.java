package ua.edu.chmnu.fks.oop.base64;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.edu.chmnu.fks.oop.crypto.base64.Base64;
import ua.edu.chmnu.fks.oop.crypto.base64.Base64Exception;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Base64Test {
    
    @ParameterizedTest
    @MethodSource("provideFixtureForEncode")
    public void shouldSuccessEncode(String input, String expected) {
        String actual = Base64.encode(input);
        Assertions.assertEquals(expected, actual);
    }
    
    private static Stream<Arguments> provideFixtureForEncode() {
        return Stream.of(
            Arguments.of("A", "QQ=="),
            Arguments.of("Ak", "QWs="),
            Arguments.of("Hel", "SGVs"),
            Arguments.of("Hello", "SGVsbG8="),
            Arguments.of("Hello, world!", "SGVsbG8sIHdvcmxkIQ=="),
            Arguments.of("Unsupported exception, bro!!!", "VW5zdXBwb3J0ZWQgZXhjZXB0aW9uLCBicm8hISE="),
            Arguments.of("S   dfdsr    adfsgeth   adfsgt      sdd", "UyAgIGRmZHNyICAgIGFkZnNnZXRoICAgYWRmc2d0ICAgICAgc2Rk"),
            Arguments.of("HFDfyr47gb  dasjfgtu   dfg    ", "SEZEZnlyNDdnYiAgZGFzamZndHUgICBkZmcgICAg")
//            Arguments.of("hsjdfg dfght u dvb    df ", "aHNqZGZnIGRmZ2h0IHUgZHZiICAgIGRmIA=="),
//            Arguments.of(" ", "IA=="),
//            Arguments.of("                    ", "ICAgICAgICAgICAgICAgICAgICA=")
        );
    }
    
    @Test
    public void shouldFailureEncodeWithThrowExceptionWhenStringIsNull() {
        assertThrows(Base64Exception.class, () -> Base64.encode(null));
    }
    
    @Test
    public void shouldFailureEncodeWithThrowExceptionWhenStringIsEmpty() {
        assertThrows(Base64Exception.class, () -> Base64.encode(""));
    }
}
