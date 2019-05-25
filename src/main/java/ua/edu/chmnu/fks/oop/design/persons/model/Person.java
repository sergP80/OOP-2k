package ua.edu.chmnu.fks.oop.design.persons.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person implements Serializable {
    @EqualsAndHashCode.Include
    private String name;

    @EqualsAndHashCode.Include
    private LocalDate birthDate;

    private String phone;

    private String address;

    public long age() {
        return YEARS.between(birthDate, LocalDate.now());
    }
}
