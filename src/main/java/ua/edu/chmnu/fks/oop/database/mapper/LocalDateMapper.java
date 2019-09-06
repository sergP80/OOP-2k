package ua.edu.chmnu.fks.oop.database.mapper;

import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.*;

@NoArgsConstructor
public class LocalDateMapper implements Converter<Timestamp, LocalDate> {

    @Override
    public LocalDate convertFrom(Timestamp timeStamp) {
        return timeStamp.toLocalDateTime().toLocalDate();
    }

    @Override
    public Timestamp convertTo(LocalDate localDate) {
        return Timestamp.valueOf(localDate.atStartOfDay());
    }
}
