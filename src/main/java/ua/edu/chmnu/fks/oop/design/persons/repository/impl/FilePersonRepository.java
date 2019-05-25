package ua.edu.chmnu.fks.oop.design.persons.repository.impl;

import lombok.Getter;
import ua.edu.chmnu.fks.oop.design.persons.errors.PersonException;
import ua.edu.chmnu.fks.oop.design.persons.model.Person;
import ua.edu.chmnu.fks.oop.design.persons.repository.PersonRepository;
import ua.edu.chmnu.fks.oop.repository.EmptyRepositoryException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class FilePersonRepository implements PersonRepository {
    @Getter
    private final String fileName;

    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public FilePersonRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Person read() throws PersonException {
        try {
            if (Objects.isNull(ois)) {
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(this.fileName));
                this.ois = new ObjectInputStream(in);
            }
            return (Person)(ois.readObject());
        }
        catch (EOFException e) {
            throw new EmptyRepositoryException(e);
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new PersonException(e);
        }
    }

    @Override
    public List<Person> read(int limit, Predicate<Person> filter) throws RuntimeException {
        if (limit <= 0) {
            throw new IllegalArgumentException();
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Person> list = new ArrayList<>();
            int count = 0;
            try {
                for (;count < limit;) {
                    Person o = (Person) ois.readObject();
                    if (Objects.isNull(filter) || filter.test(o)) {
                        list.add(o);
                        ++count;
                    }
                }
                return list;
            }
            catch (EOFException e) {
                return list;
            }
        }
        catch (IOException | ClassNotFoundException e) {
            throw new PersonException(e);
        }
    }

    @Override
    public void write(Person o) throws PersonException {
        try {
           if (Objects.isNull(oos)) {
               BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName, false));
               oos = new ObjectOutputStream(out);
           }
           oos.writeObject(o);
        } catch (IOException e) {
            throw new PersonException(e);
        }
    }


    @Override
    public void flush() throws IOException {
        if (Objects.nonNull(oos)) {
            oos.flush();
        }
    }

    @Override
    public void close() throws IOException {
        if (Objects.nonNull(oos)) {
            oos.close();
        }

        if (Objects.nonNull(ois)) {
            ois.close();
        }
    }
}
