package ua.edu.chmnu.fks.oop.streams;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CityStatistic {
    private Long totalPopulation = 0L;
    private Double totalCitySquare = 0.0;
    private City largestCity;
    private City populousCity;
}
