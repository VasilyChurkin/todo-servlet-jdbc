package ru.bogdanov.todo.dao;

import ru.bogdanov.todo.model.Todo;

import java.sql.*;
import java.util.*;

/**
 * Denis, 22.09.2018
 */
public class JdbcTodoDaoImpl implements TodoDao {

    private static long currentID = 0;
    //public static Map<Long, Todo> newCollection = new HashMap<>();

    public static final String URL = "jdbc:mysql://localhost:3306/vasya_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "21122015";

    public static final String SELECT_ALL = "select * from todo;";
    public static final String ADD_NEW = "insert into todo (name, description) values (?, ?);";
    public static final String FIND_BY_ID = "select * from todo where id = ?;";
    public static final String UPDATE = "update todo set name = ?, description = ? where id = ?;";
    public static final String DELETE_BY_ID = "delete from todo where id = ?;";
    public static final String SQL_SEARCH = "select * from todo where name like ?;";



    @Override
    public void add(Todo todo) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            PreparedStatement prst = connection.prepareStatement(ADD_NEW);
            prst.setString(1, todo.getName());
            prst.setString(2, todo.getDescription());
            prst.executeUpdate();

            prst.close();
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("mistake when add");
            e.printStackTrace();
        }
    }

    @Override
    public Todo findById(Long id) {
        Todo todo = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            PreparedStatement prst = connection.prepareStatement(FIND_BY_ID);
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();

            rs.first();
            todo = new Todo(rs.getLong(1), rs.getString("name"), rs.getString("description"));///----???

            rs.close();
            prst.close();
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("Mistake when find id");
            e.printStackTrace();
        }

        return todo;
    }

    @Override
    public Collection<Todo> getAll() {

        Collection<Todo> newCollection = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(SELECT_ALL);

            while (rs.next()){
                newCollection.add(new Todo(rs.getLong(1), rs.getString(2), rs.getString(3)));
            }
            rs.close();
            stm.close();
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("mistake connection in GetAll method");
            e.printStackTrace();
        }
        return newCollection;
    }

    @Override
    public void update(Todo todo) {

        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            PreparedStatement prst = connection.prepareStatement(UPDATE);
            prst.setString(1, todo.getName());
            prst.setString(2, todo.getDescription());
            prst.setLong(3, todo.getId());
            prst.executeUpdate();

            prst.close();
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("mistake when update");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {

        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            PreparedStatement prst = connection.prepareStatement(DELETE_BY_ID);
            prst.setLong(1, id);
            prst.executeUpdate();

            prst.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Todo> search(String searchString) {
        List<Todo> result = new ArrayList<>();
        String queryString = "%" + searchString.toLowerCase() + "%";
        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            PreparedStatement prst = connection.prepareStatement(SQL_SEARCH);
            prst.setString(1, queryString);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                result.add(new Todo(rs.getString("name"), rs.getString("description")));
            }
            rs.close();
            prst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
