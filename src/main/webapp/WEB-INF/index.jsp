<%@ page import="com.book.author_book_ee.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<%--  <link rel="preconnect" href="https://fonts.googleapis.com">--%>
<%--  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>--%>
<%--  <link--%>
<%--      href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"--%>
<%--      rel="stylesheet">--%>
  <style>
    <%@include file="css/style.css"%>
  </style>
</head>
<body>
<% User user = (User) session.getAttribute("user");%>
<h1 class="header">Author Book Application</h1>
<% if (user != null) { %>
<h3>Welcome <%=user.getName() + " " + user.getSurname()%> to our website</h3>
<a href="/logout">Logout</a>
<%} else {%>
<a href="/login">Login</a><br>
<a href="/register">Register</a><br>
<%}%>
<br/>
<a href="/books">Books</a><br>
<a href="/authors">Authors</a>
</body>
</html>