package ua.edu.chmnu.fks.oop.database.mapper.builders.sql;

public interface SqlBuilder {
    String buildSelectAll();

    String buildSelectById();

    String buildPageSelect();

    String buildInsert();

    String buildUpdate();
}
