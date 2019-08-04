package ua.edu.chmnu.fks.oop.design.users.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserPersonalInformation implements Serializable {
    @EqualsAndHashCode.Exclude
    private Long id;

    private String firstName;

    private String lastName;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    private String phone;

    private String address;

    private LocalDate birthDate;

    public long age() {
        return YEARS.between(birthDate, LocalDate.now());
    }
}
