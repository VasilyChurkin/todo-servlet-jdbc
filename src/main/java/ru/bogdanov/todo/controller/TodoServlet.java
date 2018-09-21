package ru.bogdanov.todo.controller;

import ru.bogdanov.todo.dao.InMemoryTodoDaoImpl;
import ru.bogdanov.todo.dao.TodoDao;
import ru.bogdanov.todo.model.Todo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Denis, 14.09.2018
 */
public class TodoServlet extends HttpServlet {

    private TodoDao todoDao;

    List<String> list = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        todoDao = new InMemoryTodoDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "getAll";
        }
        Long id;
        RequestDispatcher requestDispatcher;

        switch (action) {
            case "search":
                request.setAttribute("allTodos", todoDao.search(request.getParameter("find")));
                requestDispatcher = request.getRequestDispatcher("todo-list.jsp");
                break;
            case "addTodo":
                String todoName = request.getParameter("todoName");
                String todoDescription = request.getParameter("todoDescription");
                todoDao.add(new Todo(todoName, todoDescription));
                request.setAttribute("allTodos", todoDao.getAll());
                requestDispatcher = request.getRequestDispatcher("todo-list.jsp");
                break;
            case "show":
                id = Long.valueOf(request.getParameter("id"));
                request.setAttribute("todoForJsp", todoDao.findById(id));
                requestDispatcher = request.getRequestDispatcher("todo-with-description.jsp");
                break;
            case "updateTodo":
                id = Long.valueOf(request.getParameter("id"));
                String description = request.getParameter("description");
                Todo todoForUpdate = todoDao.findById(id);
                todoForUpdate.setDescription(description);
                todoDao.update(todoForUpdate);
                request.setAttribute("todoForJsp", todoForUpdate);
                requestDispatcher = request.getRequestDispatcher("todo-with-description.jsp");
                break;
            case "remove":
                id = Long.valueOf(request.getParameter("id"));
                todoDao.deleteById(id);
                request.setAttribute("allTodos", todoDao.getAll());
                requestDispatcher = request.getRequestDispatcher("todo-list.jsp");
                break;
            default:
                request.setAttribute("allTodos", todoDao.getAll());
                requestDispatcher = request.getRequestDispatcher("todo-list.jsp");
                break;

        }

        requestDispatcher.forward(request, response);
    }
}
