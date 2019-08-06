package ua.edu.chmnu.fks.oop.streams;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class City {
    private String name;
    private Integer population;
    private Double square;
}
