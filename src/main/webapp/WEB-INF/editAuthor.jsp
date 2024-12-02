<%@ page import="com.book.author_book_ee.model.Author" %>
<%@ page import="com.book.author_book_ee.model.Gender" %>
<%@ page import="com.book.author_book_ee.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: alik
  Date: 11/14/24
  Time: 3:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit Author Page</title>
</head>
<body>
<% Author author = (Author) request.getAttribute("author"); %>
<h1>Edit Author</h1>
<a href="/authors">Authors</a> | <a href="index.jsp">Main</a> <br>

<form action="/edit-author" method="post">
  Id: <input type="hidden" name="id" value="<%=author.getId()%>"><br>
  Name: <input type="text" name="name" value="<%=author.getName()%>"><br>Add
  Surname: <input type="text" name="surname" value="<%=author.getSurname()%>"><br>
  Phone: <input type="text" name="phone" value="<%=author.getPhone()%>"><br>
  Date Of Birthday: <input type="date" name="dob" value="<%=DateUtil.fromDateToWebString(author.getDateOfBirthday())%>"><br>
  GENDER: <select name="gender">
  <%if (author.getGender() == Gender.MALE) {%>
  <option value="MALE" selected>MALE</option>
  <option value="FEMALE">FEMALE</option>
  dateStr
  <%} else {%>
  <option value="MALE">MALE</option>
  <option value="FEMALE" selected>FEMALE</option>
  <%}%>
</select><br>Add
  <input type="submit" value="UPDATE">
</form>
</body>
</html>
