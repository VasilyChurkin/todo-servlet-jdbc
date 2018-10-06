package ru.bogdanov.todo.service;

import ru.bogdanov.todo.model.Todo;

import java.util.Collection;
import java.util.List;

/**
 * Denis, 06.10.2018
 */
public interface TodoService {

    void add(Todo todo);

    Todo findById(Long id);

    Collection<Todo> getAll();

    void update(Todo todo);

    void deleteById(Long id);

    List<Todo> search(String searchString);
}
