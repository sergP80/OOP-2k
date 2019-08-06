package ua.edu.chmnu.fks.oop.streams;

import java.util.*;
import java.util.stream.Collectors;

import static ua.edu.chmnu.fks.oop.streams.ListStreamProcessing.SHADOW_CHAR;

public class CountryProcessing {
    public static void main(String[] args) {
        List<Country> countryList = Arrays.asList(
                new Country("United States of America",
                        Arrays.asList(
                                new City("New York", 8_623_000, 783.8),
                                new City("Albany", 98_251, 56.82),
                                new City("Los Angeles", 4_000_000, 1302.0),
                                new City("Kansas City", 488_943, 826.3),
                                new City("Washington", 633_427, 177.0)
                        )
                ),
                new Country("Germany",
                        Arrays.asList(
                                new City("Berlin", 3_520_031, 891.68),
                                new City("Hamburg", 1_787_408, 755.3 ),
                                new City("Munich", 1_450_381, 310.7),
                                new City("Dortmund", 586_181, 280.71 ),
                                new City("Stuttgart", 623_738, 207.35)
                        )
                ),
                new Country("Ukraine",
                        Arrays.asList(
                                new City("Kiev", 2_814_260 , 870.50),
                                new City("Odessa", 1_007_190, 236.90),
                                new City("Nikolayev", 496_300, 253.0),
                                new City("Lviv", 757_800, 182.01),
                                new City("Dnipro", 1_001_960, 405.0)

                        )
                )
        );

        System.out.println("========= The most population city =========");
        City mostPopulatedCity = countryList.stream()
                .flatMap(c -> c.getCities().stream())
                .max(Comparator.comparing(City::getPopulation))
                .orElseThrow(CityNotFoundException::new);

        System.out.println(mostPopulatedCity);

        System.out.println("========= The largest city =========");
        City largestCity = countryList.stream()
                .flatMap(c -> c.getCities().stream())
                .max(Comparator.comparing(City::getSquare))
                .orElseThrow(CityNotFoundException::new);

        System.out.println(largestCity);

        System.out.println("========= Density of population =========");

        Map<String, Double> densitiesOfPopulation = countryList.stream()
                .flatMap(c -> c.getCities().stream())
                .sorted(Comparator.comparing(c -> c.getPopulation()/c.getSquare(), Comparator.reverseOrder()))
                .collect(
                        Collectors.toMap(
                                City::getName,
                                c -> c.getPopulation() / c.getSquare(),
                                (c1, c2) -> c1,
                                LinkedHashMap::new
                        )
                );

        double maxPopulationDensity = densitiesOfPopulation.values().stream()
                .mapToDouble(a -> a)
                .max().orElseThrow(IllegalArgumentException::new);

        int maxCityNameLength = densitiesOfPopulation.keySet().stream()
                .mapToInt(String::length)
                .max().orElseThrow(IllegalArgumentException::new);

        densitiesOfPopulation.forEach((cityName, density) -> {
            System.out.print(cityName);
            for (int i = 0; i < maxCityNameLength - cityName.length(); ++i) {
                System.out.print(" ");
            }
            System.out.print(":");

            int percents = (int)(density/maxPopulationDensity*100);
            for (int i = 0; i < percents; ++i) {
                System.out.print(SHADOW_CHAR);
            }
            System.out.println(String.format(" %.2f", density));
        });
    }
}
