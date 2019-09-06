package ua.edu.chmnu.fks.oop.patterns.facade;

import java.util.Collection;
import java.util.List;

public interface BasicDao<T> {
    List<T> all();

    List<T> all(int pageNumber, int count);

    T save(T order);

    void save(Collection<T> order);

    T update(T order);

    void delete(T order);
}
