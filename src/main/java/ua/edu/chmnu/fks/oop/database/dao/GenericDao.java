package ua.edu.chmnu.fks.oop.database.dao;

import ua.edu.chmnu.fks.oop.database.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {
    T create(T o) throws DaoException;
    T update(T o) throws DaoException;
    int delete(PK id) throws DaoException;
    T read(PK id) throws DaoException;
    List<T> read(int page, int size) throws DaoException;
    List<T> readAll() throws DaoException;
}
