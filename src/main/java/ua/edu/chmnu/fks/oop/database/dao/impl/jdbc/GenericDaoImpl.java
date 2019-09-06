package ua.edu.chmnu.fks.oop.database.dao.impl.jdbc;

import ua.edu.chmnu.fks.oop.database.dao.DaoTable;
import ua.edu.chmnu.fks.oop.database.dao.GenericDao;

import java.io.Serializable;

public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK>, DaoTable {
}
