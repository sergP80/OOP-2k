package ua.edu.chmnu.fks.oop.streams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Address {
    private String flatOrHouse;
    private String street;
    private String city;
    private String zipCode;
}

public class ListOfOptionals {
    public static void main(String[] args) {
        List<Address> addresses = Arrays.asList(
          new Address("h1", null, "Nk1", "zp1"),
          new Address(null, "st2", "Nk2", "zp2"),
          new Address("h3", "st3", "Nk2", null)
        );

        List<String> strAddresses = addresses.stream()
                .map(a ->
                        Stream.of(
                                Optional.ofNullable(a.getFlatOrHouse()),
                                Optional.ofNullable(a.getStreet()),
                                Optional.ofNullable(a.getCity()),
                                Optional.ofNullable(a.getZipCode())
                        )
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .collect(Collectors.joining(" "))
                ).collect(Collectors.toList());
        strAddresses.forEach(System.out::println);
    }
}
