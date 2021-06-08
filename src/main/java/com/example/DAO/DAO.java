package com.example.DAO;

import java.util.List;
import java.util.Optional;

public interface DAO<T>{
    List<T> list();

    void create(T t);

    Optional<T> get(String id);

    void update(T t, String id);

    void delete(String id);
}
