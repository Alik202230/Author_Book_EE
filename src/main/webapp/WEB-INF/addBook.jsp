<%@ page import="java.util.List" %>
<%@ page import="com.book.author_book_ee.model.Author" %><%--
  Created by IntelliJ IDEA.
  User: alik
  Date: 11/14/24
  Time: 6:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add Book Page</title>
</head>
<body>

<% List<Author> authors = (List<Author>) request.getAttribute("authors"); %>

<h1>Add Book</h1>
<a href="/books">Book</a> | <a href="index.jsp">Main</a> <br>

<form action="/add-book" method="post" enctype="multipart/form-data">
  Title: <input type="text" name="title"><br>
  Price: <input type="number" name="price"><br>
  quantity: <input type="text" name="quantity"><br>
  Author: <select name="author_id">
  <% for (Author author : authors) { %>
  <option value="<%=author.getId()%>" name="<%=author.getName() + " " + author.getSurname()%>">
    <%=author.getName()%>
  </option>
  <%}%>
  </select><br>
  <input type="file" name="img">
  <input type="submit" value="ADD">

</form>
</body>
</html>
