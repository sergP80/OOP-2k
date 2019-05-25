package ua.edu.chmnu.fks.oop.design.persons.app;

import ua.edu.chmnu.fks.oop.design.persons.model.Person;
import ua.edu.chmnu.fks.oop.design.persons.repository.PersonRepository;
import ua.edu.chmnu.fks.oop.design.persons.repository.impl.FilePersonRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException {
        String fileName = "persons.dat";
        try(PersonRepository repo = new FilePersonRepository(fileName)) {
            Person p1 = Person.builder()
                    .name("John Smith")
                    .birthDate(LocalDate.of(1982, 12, 22))
                    .address("Edisson square 15/3")
                    .build();
            Person p2 = Person.builder()
                    .name("Lessly Green")
                    .birthDate(LocalDate.of(1977, 11, 12))
                    .address("Palm beach 002")
                    .build();

            Person p3 = Person.builder()
                    .name("Oliver Bishop")
                    .birthDate(LocalDate.of(2001, 5, 7))
                    .address("Calgary suit 231")
                    .build();

            repo.write(new Person[]{p1, p2, p3});
        }

        try(PersonRepository repo = new FilePersonRepository(fileName)) {
            List<Person> personList = repo.read(1000, p -> p.age() > 30);
            personList.forEach(System.out::println);
        }
    }
}
