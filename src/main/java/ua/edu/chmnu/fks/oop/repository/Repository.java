package ua.edu.chmnu.fks.oop.repository;

import ua.edu.chmnu.fks.oop.repository.errors.EmptyRepositoryException;
import ua.edu.chmnu.fks.oop.repository.errors.Errors;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public interface Repository<T> extends Closeable {
    T read() throws RuntimeException;

    default List<T> read(int limit, Predicate<T> filter) throws RuntimeException {
        if (limit <= 0) {
            throw new IllegalArgumentException(String.format(Errors.ERR_INVALID_LIMIT, limit));
        }

        List<T> list = new ArrayList<>();
        int count = 0;
        try {
            for (;count < limit;) {
                T o = read();
                if (Objects.isNull(filter) || filter.test(o)) {
                    list.add(read());
                    ++count;
                }
            }
            return list;
        } catch (EmptyRepositoryException e) {
            return list;
        }
    }

    default List<T> read(int n) throws RuntimeException {
        return read(n, null);
    }

    void write(T o) throws RuntimeException;

    default void write(T[] objects) throws RuntimeException {
        if (Objects.isNull(objects)) {
            return;
        }
        try {
            for (T o : objects) {
                write(o);
            }
            flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default void write(Collection<T> objects) throws RuntimeException {
        if (Objects.isNull(objects) || objects.isEmpty()) {
            return;
        }
        try {
            for (T o : objects) {
                write(o);
            }
            flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    default void close() throws IOException {
    }

    default void flush() throws IOException {}
}
