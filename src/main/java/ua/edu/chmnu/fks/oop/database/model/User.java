package ua.edu.chmnu.fks.oop.database.model;

import lombok.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    private Long id;

    @EqualsAndHashCode.Include
    private String email;

    @EqualsAndHashCode.Include
    private String phone;

    @EqualsAndHashCode.Include
    private LocalDate birthDay;

    private String address;

    @ToString.Exclude
    private Set<Post> posts = new HashSet<>();

    public int age() {
        return (int)ChronoUnit.YEARS.between(this.birthDay, LocalDate.now(ZoneId.systemDefault()));
    }
}
