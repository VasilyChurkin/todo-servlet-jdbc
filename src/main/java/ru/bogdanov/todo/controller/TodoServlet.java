package ru.bogdanov.todo.controller;

import ru.bogdanov.todo.model.Todo;
import ru.bogdanov.todo.service.TodoService;
import ru.bogdanov.todo.service.TodoServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Denis, 14.09.2018
 */
public class TodoServlet extends HttpServlet {

    private TodoService todoService;

    List<String> list = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        todoService = TodoServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "getAll";
        }
        Long id;
        RequestDispatcher requestDispatcher;

        switch (action) {
            case "search":
                request.setAttribute("allTodos", todoService.search(request.getParameter("find")));
                requestDispatcher = request.getRequestDispatcher("todo-list.jsp");
                break;
            case "addTodo":
                add(request);
                requestDispatcher = request.getRequestDispatcher("todo-list.jsp");
                break;
            case "show":
                id = getId(request);
                request.setAttribute("todoForJsp", todoService.findById(id));
                requestDispatcher = request.getRequestDispatcher("todo-with-description.jsp");
                break;
            case "updateTodo":
                id = getId(request);
                String description = request.getParameter("description");
                Todo todoForUpdate = todoService.findById(id);
                todoForUpdate.setDescription(description);
                todoService.update(todoForUpdate);
                request.setAttribute("todoForJsp", todoForUpdate);
                requestDispatcher = request.getRequestDispatcher("todo-with-description.jsp");
                break;
            case "remove":
                id = getId(request);
                todoService.deleteById(id);
                request.setAttribute("allTodos", todoService.getAll());
                requestDispatcher = request.getRequestDispatcher("todo-list.jsp");
                break;
            default:
                request.setAttribute("allTodos", todoService.getAll());
                requestDispatcher = request.getRequestDispatcher("todo-list.jsp");
                break;

        }

        requestDispatcher.forward(request, response);
    }

    private Long getId(HttpServletRequest request) {
        return Long.valueOf(request.getParameter("id"));
    }

    private void add(HttpServletRequest request) {
        String todoName = request.getParameter("todoName");
        String todoDescription = request.getParameter("todoDescription");
        todoService.add(new Todo(todoName, todoDescription));
        request.setAttribute("allTodos", todoService.getAll());
    }
}
