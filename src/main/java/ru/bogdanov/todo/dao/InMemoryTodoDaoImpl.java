package ru.bogdanov.todo.dao;

import ru.bogdanov.todo.model.Todo;

import java.util.*;

/**
 * Denis, 14.09.2018
 */
public class InMemoryTodoDaoImpl implements TodoDao {

    private static Map<Long, Todo> map = new HashMap<>();
    private static Long currentId = 0L;

    static {
        Todo todo1 = new Todo("Spring", "Spring framework");
        todo1.setId(++currentId);

        Todo todo2 = new Todo("Spring Boot", "Spring boot framework");
        todo2.setId(++currentId);

        Todo todo3 = new Todo("JPA", "And Hibernate impl");
        todo3.setId(++currentId);

        map.put(todo1.getId(), todo1);
        map.put(todo2.getId(), todo2);
        map.put(todo3.getId(), todo3);
    }

    public void add(Todo todo) {
        todo.setId(++currentId);
        map.put(todo.getId(), todo);
    }

    public Todo findById(Long id) {
        return map.get(id);
    }

    public Collection<Todo> getAll() {
        return map.values();
    }

    public void update(Todo todo) {
        map.put(todo.getId(), todo);
    }

    public void deleteById(Long id) {
        map.remove(id);
    }

    public List<Todo> search(String searchString) {

        List<Todo> result = new ArrayList<>();

        for (Map.Entry<Long, Todo> entry : map.entrySet()) {
            if (entry.getValue().getName().toLowerCase().contains(searchString.toLowerCase())) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

}
