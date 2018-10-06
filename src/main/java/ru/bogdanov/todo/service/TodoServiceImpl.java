package ru.bogdanov.todo.service;

import ru.bogdanov.todo.dao.JdbcTodoDaoImpl;
import ru.bogdanov.todo.dao.TodoDao;
import ru.bogdanov.todo.exception.TodoNotFoundException;
import ru.bogdanov.todo.model.Todo;

import java.util.Collection;
import java.util.List;

/**
 * Denis, 06.10.2018
 */
public class TodoServiceImpl implements TodoService {

    TodoDao todoDao = new JdbcTodoDaoImpl();

    @Override
    public void add(Todo todo) {
        todoDao.add(todo);
    }

    @Override
    public Todo findById(Long id) {

        Todo foundedTodo = todoDao.findById(id);

        if (foundedTodo == null) {
            throw new TodoNotFoundException("Todo with such id not found");
        }

        return foundedTodo;
    }

    @Override
    public Collection<Todo> getAll() {
        return todoDao.getAll();
    }

    @Override
    public void update(Todo todo) {
        todoDao.getAll();
    }

    @Override
    public void deleteById(Long id) {
        todoDao.deleteById(id);
    }

    @Override
    public List<Todo> search(String searchString) {
        return todoDao.search(searchString);
    }
}
