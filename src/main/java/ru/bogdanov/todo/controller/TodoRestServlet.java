package ru.bogdanov.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bogdanov.todo.model.Todo;
import ru.bogdanov.todo.service.TodoService;
import ru.bogdanov.todo.service.TodoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Denis, 06.10.2018
 */
@WebServlet("/rest")
public class TodoRestServlet extends HttpServlet {

    private TodoService todoService = TodoServiceImpl.getInstance();

    static ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        Todo todo = todoService.findById(id);

        String jsonForClient = mapper.writeValueAsString(todo);

        PrintWriter writer = response.getWriter();
        writer.println(jsonForClient);
    }
}
