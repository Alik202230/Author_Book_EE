<%--
  Created by IntelliJ IDEA.
  User: alik
  Date: 11/17/24
  Time: 9:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register Page</title>
</head>
<body>
<span>
  <% if(session.getAttribute("msg") != null) {%>
  <h3><%=session.getAttribute("msg")%></h3>
  <%  session.removeAttribute("msg"); } %>
</span>
<form action="/register" method="post">
  Name: <input type="text" name="name"><br>
  Surname: <input type="text" name="surname"><br>
  Email: <input type="text" name="email"><br>
  Password: <input type="password" name="password"><br>
  Role: <select name="role"><br>
  <option value="USER">USER</option>
  <option value="ADMIN">ADMIN</option>
</select><br>
  <input type="submit" value="Register">
</form>
</body>
</html>
