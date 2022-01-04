package ua.edu.chmnu.fks.oop.design.persons;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ua.edu.chmnu.fks.oop.design.persons.model.Person;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1972,4,11",
            "1976,5,10",
            "1988,11,19"
    })
    public void shouldSuccessAgeCalculation(int year, int month, int day) {
        Person person = new Person();
        LocalDate now = LocalDate.now();
        LocalDate birthDate = LocalDate.of(year, month, day);
        long expectedYears = YEARS.between(birthDate, now);
        person.setBirthDate(birthDate);
        assertEquals(expectedYears, person.age());
    }
}
