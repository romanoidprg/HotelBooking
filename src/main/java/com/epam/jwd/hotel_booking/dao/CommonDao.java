package com.epam.jwd.hotel_booking.dao;

import com.epam.jwd.hotel_booking.model.Login;

import java.util.List;
import java.util.Optional;

public abstract class CommonDao<T extends Login> {
    public abstract Optional<List<T>> findAll();
    public abstract Optional<T> findEntityById(long id);
    public abstract Optional<T> findEntityByName(String name);
    public abstract boolean delete(long id);
    public abstract boolean delete(T entity);
    public abstract boolean create(T entity);
    public abstract Optional<T> update(T entity);
}
