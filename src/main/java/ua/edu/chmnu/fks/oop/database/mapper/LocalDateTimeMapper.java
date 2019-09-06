package ua.edu.chmnu.fks.oop.database.mapper;

import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@NoArgsConstructor
public class LocalDateTimeMapper implements Converter<Timestamp, LocalDateTime> {

    @Override
    public LocalDateTime convertFrom(Timestamp timeStamp) {
        return timeStamp.toLocalDateTime();
    }

    @Override
    public Timestamp convertTo(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return Timestamp.valueOf(localDateTime);
    }
}
