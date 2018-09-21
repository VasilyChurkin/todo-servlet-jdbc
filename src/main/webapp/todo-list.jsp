<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>TODO</title>
  <style>
    .container {
      width: 700px;
      margin: 0 auto;
    }

    .search {
      float: right;
    }
  </style>
</head>
<body>

<div class="container">
  <h1>ToDo List</h1>

  <div class="search">
    <form action="todo" method="get">
      <input type="hidden" name="action" value="search">
      <input type="text" name="find">
      <input type="submit" value="Search">
    </form>
  </div>

  <form action="todo">
    <input type="hidden" name="action" value="addTodo">
    <label>
      Name: <input type="text" name="todoName">
    </label><br>
    <label>
      Description: <input type="text" name="todoDescription">
    </label><br>
    <input type="submit" value="Add">
  </form>

  <ul>
    <c:forEach items="${allTodos}" var="todo">
      <li>
        <a href="todo?id=${todo.id}&action=remove" style="color:red">X</a>
        <a href="todo?id=${todo.id}&action=show">${todo.name}</a></li>
    </c:forEach>
  </ul>

</div>

</body>
</html>
