<%@ page import="com.book.author_book_ee.model.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="com.book.author_book_ee.util.DateUtil" %>
<%@ page import="com.book.author_book_ee.model.User" %>
<%@ page import="com.book.author_book_ee.model.Role" %><%--
  Created by IntelliJ IDEA.
  User: alik
  Date: 11/14/24
  Time: 2:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Authors Page</title>
</head>
<body>
<h2>Authors:</h2> <a href="/addAuthor">Add Author</a>

<%
  User user = (User) session.getAttribute("user");
  List<Author> authors = (List<Author>) request.getAttribute("authors");
%>

<table border="1">
  <tr>
    <th>id</th>
    <th>name</th>
    <th>surname</th>
    <th>phone</th>
    <th>dob</th>
    <th>gender</th>
    <% if (user.getRole() == Role.ADMIN) {%>
    <td>action</td>
    <% } %>
  </tr>
  <% for (Author author : authors) { %>
  <tr>
    <td><%= author.getId() %></td>
    <td><%= author.getName() %></td>
    <td><%= author.getSurname() %></td>
    <td><%= author.getPhone() %></td>
    <td><%= DateUtil.fromDateToString(author.getDateOfBirthday()) %></td>
    <td><%= author.getGender().name() %></td>
    <% if (user.getRole() == Role.ADMIN) {%>
    <td><a href="${pageContext.request.contextPath}/delete-author?id=<%= author.getId() %>">delete</a> /
      <a href="${pageContext.request.contextPath}/edit-author?id=<%= author.getId() %>">edit</a></td>
    <% } %>
  </tr>
  <% } %>
</table>
</body>
</html>
