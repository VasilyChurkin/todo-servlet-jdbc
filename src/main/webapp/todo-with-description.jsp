<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>${todoForJsp.name}</title>
</head>
<body>

<h1>${todoForJsp.name}</h1>

<form action="todo">
  <input type="hidden" name="action" value="updateTodo">
  <input type="hidden" name="id" value="${todoForJsp.id}">
  <input name="description" value="${todoForJsp.description}">
  <input type="submit" value="Update">
</form>


<a href="todo">Start Page</a>

</body>
</html>
