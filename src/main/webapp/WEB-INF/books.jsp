<%@ page import="com.book.author_book_ee.model.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: alik
  Date: 11/14/24
  Time: 2:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Book Page</title>
</head>
<body>
<h1>Books</h1>
<% List<Book> books = (List<Book>) request.getAttribute("books");%>

<a href="/add-book">add book</a>

<table>
  <tr>
    <th>id</th>
    <th>title</th>
    <th>price</th>
    <th>qty</th>
    <th>author name</th>
  </tr>
  <% for (Book book : books) { %>
  <tr>
    <td><%=book.getId()%></td>
    <td><%=book.getTitle()%></td>
    <td><%=book.getPrice()%></td>
    <td><%=book.getQty()%></td>
    <td><%=book.getAuthor().getName()%></td>
  </tr>
  <% } %>
</table>
</body>
</html>
