package com.epam.jwd.hotel_booking.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoFactory {
    private final Logger logger = LoggerFactory.getLogger(DaoFactory.class);

    public <T extends CommonDao> T getDao(Class<T> daoClass) {
        try {
            return daoClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
