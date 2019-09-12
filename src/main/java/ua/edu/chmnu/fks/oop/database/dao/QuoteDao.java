package ua.edu.chmnu.fks.oop.database.dao;

import ua.edu.chmnu.fks.oop.database.model.Quote;

public interface QuoteDao extends GenericDao<Quote, Long> {
    String TABLE_NAME = "social_net.quotes";
}
