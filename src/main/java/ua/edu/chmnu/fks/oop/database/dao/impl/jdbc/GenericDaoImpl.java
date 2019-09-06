package ua.edu.chmnu.fks.oop.database.dao.impl.jdbc;

import ua.edu.chmnu.fks.oop.database.dao.DaoTable;
import ua.edu.chmnu.fks.oop.database.dao.GenericDao;
import ua.edu.chmnu.fks.oop.database.exceptions.DaoException;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK>, DaoTable {

    @Override
    public int deleteAll() throws DaoException {
        String sql = String.format("DELETE FROM %s", tableName());
        try(PreparedStatement statement = connection().prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
