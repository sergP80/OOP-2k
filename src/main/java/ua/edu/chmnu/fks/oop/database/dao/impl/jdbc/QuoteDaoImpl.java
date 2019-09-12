package ua.edu.chmnu.fks.oop.database.dao.impl.jdbc;

import ua.edu.chmnu.fks.oop.database.dao.PostDao;
import ua.edu.chmnu.fks.oop.database.dao.QuoteDao;
import ua.edu.chmnu.fks.oop.database.dao.UserDao;
import ua.edu.chmnu.fks.oop.database.exceptions.DaoException;
import ua.edu.chmnu.fks.oop.database.mapper.LocalDateTimeMapper;
import ua.edu.chmnu.fks.oop.database.mapper.QuoteMapper;
import ua.edu.chmnu.fks.oop.database.mapper.UserQuoteMapper;
import ua.edu.chmnu.fks.oop.database.mapper.builders.sql.QuotePreparedSqlBuilder;
import ua.edu.chmnu.fks.oop.database.model.Quote;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuoteDaoImpl extends GenericDaoImpl<Quote, Long> implements QuoteDao {
    private final Connection connection;
    private final QuoteMapper quoteMapper;
    private final LocalDateTimeMapper dateTimeMapper;

    public QuoteDaoImpl(Connection connection, QuoteMapper userQuoteMapper, LocalDateTimeMapper dateTimeMapper) {
        this.connection = connection;
        this.quoteMapper = userQuoteMapper;
        this.dateTimeMapper = dateTimeMapper;
    }

    @Override
    public String tableName() {
        return TABLE_NAME;
    }

    @Override
    public Connection connection() {
        return this.connection;
    }

    @Override
    public Quote create(Quote quote) throws DaoException {
        String sql = QuotePreparedSqlBuilder.create(quote, tableName(), PostDao.TABLE_NAME, UserDao.TABLE_NAME).buildInsert();
        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, quote.getContent());
            statement.setTimestamp(2, dateTimeMapper.convertTo(LocalDateTime.now()));
            statement.setLong(3, quote.getUser().getId());
            statement.setLong(4, quote.getPost().getId());
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new DaoException("Could not inserted quote record");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    quote.setId(generatedKeys.getLong(1));
                } else {
                    throw new DaoException("Could not insert quote record with generation key");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quote;
    }

    @Override
    public Quote update(Quote quote) throws DaoException {
        String sql = QuotePreparedSqlBuilder.create(quote, TABLE_NAME, PostDao.TABLE_NAME, UserDao.TABLE_NAME).buildUpdate();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, quote.getContent());
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new DaoException("Could not update quote record");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quote;
    }

    @Override
    public int delete(Long id) throws DaoException {
        String sql = String.format("DELETE FROM %s WHERE id=?", tableName());
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new DaoException("Could not delete quote record");
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Quote read(Long id) throws RuntimeException {
        String sql = QuotePreparedSqlBuilder.create(null, TABLE_NAME, PostDao.TABLE_NAME, UserDao.TABLE_NAME).buildSelectById();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try(ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return quoteMapper.convertFrom(result);
                }
                throw new DaoException("Could not find quote by id=" + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Quote> read(int page, int size) throws DaoException {
        page = page > 0 ? page - 1 : 0;
        String sql = QuotePreparedSqlBuilder.create(null, TABLE_NAME, PostDao.TABLE_NAME, UserDao.TABLE_NAME).buildPageSelect();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, page);
            statement.setLong(2, size);
            try(ResultSet result = statement.executeQuery()) {
                List<Quote> listResult = new ArrayList<>();
                while (result.next()) {
                    listResult.add(quoteMapper.convertFrom(result));
                }
                return listResult;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Quote> readAll() throws DaoException {
        String sql = QuotePreparedSqlBuilder.create(null, TABLE_NAME, PostDao.TABLE_NAME, UserDao.TABLE_NAME).buildSelectAll();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            try(ResultSet result = statement.executeQuery()) {
                List<Quote> listResult = new ArrayList<>();
                while (result.next()) {
                    listResult.add(quoteMapper.convertFrom(result));
                }
                return listResult;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
