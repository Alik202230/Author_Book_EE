<%--
  Created by IntelliJ IDEA.
  User: alik
  Date: 11/17/24
  Time: 9:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login Page</title>
</head>
<body>
<span>
  <% if(session.getAttribute("msg") != null) {%>
  <h3><%=session.getAttribute("msg")%></h3>
  <% } %>
  <% session.removeAttribute("msg"); %>
</span>
<form action="/login" method="post">
  Email: <input type="text" name="email"><br>
  Password: <input type="password" name="password"><br>
  <input type="submit">
</form>
</body>
</html>
