package ru.bogdanov.todo.dao;

import ru.bogdanov.todo.model.Todo;

import java.util.Collection;
import java.util.List;

/**
 * Denis, 14.09.2018
 */
public interface TodoDao {

    void add(Todo todo);

    Todo findById(Long id);

    Collection<Todo> getAll();

    void update(Todo todo);

    void deleteById(Long id);

    List<Todo> search(String searchString);
}
