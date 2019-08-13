package ua.edu.chmnu.fks.oop.streams;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CityStatisticCollector implements Collector<City, CityStatistic, CityStatistic> {

    @Override
    public Supplier<CityStatistic> supplier() {
        return CityStatistic::new;
    }

    @Override
    public BiConsumer<CityStatistic, City> accumulator() {
        return (result, city) -> {
            result.setTotalPopulation(result.getTotalPopulation() + city.getPopulation());
            result.setTotalCitySquare(result.getTotalCitySquare() + city.getSquare());
            if (Objects.isNull(result.getLargestCity()) || result.getLargestCity().getSquare() < city.getSquare()) {
                result.setLargestCity(city);
            }

            if (Objects.isNull(result.getPopulousCity()) || result.getPopulousCity().getPopulation() < city.getPopulation()) {
                result.setPopulousCity(city);
            }
        };
    }

    @Override
    public BinaryOperator<CityStatistic> combiner() {
        return (r1, r2) -> {
            r1.setTotalCitySquare(r1.getTotalCitySquare() + r2.getTotalCitySquare());
            r1.setTotalPopulation(r1.getTotalPopulation() + r2.getTotalPopulation());
            if (r1.getLargestCity().getSquare() < r2.getLargestCity().getSquare()) {
                r1.setLargestCity(r2.getLargestCity());
            }
            if (r1.getPopulousCity().getPopulation() < r2.getPopulousCity().getPopulation()) {
                r1.setPopulousCity(r2.getPopulousCity());
            }
            return r1;
        };
    }

    @Override
    public Function<CityStatistic, CityStatistic> finisher() {
        return (cs) -> cs;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return new HashSet<>(Arrays.asList(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }

    public static Collector<City, CityStatistic, CityStatistic> create() {
        return new CityStatisticCollector();
    }
}
