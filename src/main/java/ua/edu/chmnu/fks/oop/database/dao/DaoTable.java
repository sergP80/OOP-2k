package ua.edu.chmnu.fks.oop.database.dao;

import java.sql.Connection;

public interface DaoTable {
    Connection connection();
    String tableName();
}
