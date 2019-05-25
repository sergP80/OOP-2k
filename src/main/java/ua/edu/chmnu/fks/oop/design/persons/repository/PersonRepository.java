package ua.edu.chmnu.fks.oop.design.persons.repository;

import ua.edu.chmnu.fks.oop.design.persons.errors.PersonException;
import ua.edu.chmnu.fks.oop.design.persons.model.Person;
import ua.edu.chmnu.fks.oop.repository.Repository;

public interface PersonRepository extends Repository<Person> {
    @Override
    Person read() throws PersonException;

    @Override
    void write(Person o) throws PersonException;
}
