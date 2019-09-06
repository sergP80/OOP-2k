package ua.edu.chmnu.fks.oop.database.mapper;

import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.*;

@NoArgsConstructor
public class LocalDateMapper implements Converter<Long, LocalDate> {

    @Override
    public LocalDate convertFrom(Long timeStamp) {
        return new Timestamp(timeStamp).toLocalDateTime().toLocalDate();
    }

    @Override
    public Long convertTo(LocalDate localDate) {
        return Timestamp.valueOf(localDate.atStartOfDay()).getTime();
    }
}
