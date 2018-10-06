package ru.bogdanov.todo.exception;

/**
 * Denis, 06.10.2018
 */
public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(String message) {
        super(message);
    }
}
