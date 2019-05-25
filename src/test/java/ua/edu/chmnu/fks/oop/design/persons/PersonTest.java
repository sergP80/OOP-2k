package ua.edu.chmnu.fks.oop.design.persons;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.edu.chmnu.fks.oop.design.persons.model.Person;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class PersonTest {

    @Test
    @Parameters({
            "1972, 4, 11",
            "1976, 5, 10",
            "1988, 11, 19"
    })
    public void testAge01(int year, int month, int day) {
        Person person = new Person();
        LocalDate date = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        long expectedYears = YEARS.between(now, date);
        person.setBirthDate(date);
        assertEquals(expectedYears, person.age());
    }
}