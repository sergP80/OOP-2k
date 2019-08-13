package ua.edu.chmnu.fks.oop.streams;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Country {
    private String name;

    @EqualsAndHashCode.Exclude
    private List<City> cities;

    public double totalSquare() {
        return cities.stream().mapToDouble(City::getSquare).sum();
    }

    public CityStatistic statistic() {
        return cities.stream().collect(CityStatisticCollector.create());
    }
}
