package ua.edu.chmnu.fks.oop.database.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.*;
import java.util.Objects;

@NoArgsConstructor
public class LocalDateTimeMapper implements Converter<Long, LocalDateTime> {

    @Override
    public LocalDateTime convertFrom(Long timeStamp) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault()).toLocalDateTime();
    }

    @Override
    public Long convertTo(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
