package ua.edu.chmnu.fks.oop.database.mapper;

public interface Converter<From, To> {
    To convertFrom(From from) throws RuntimeException;
    From convertTo(To to) throws RuntimeException;
}
