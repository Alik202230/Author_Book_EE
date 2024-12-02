package com.book.author_book_ee.servlet;

import com.book.author_book_ee.service.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-author")
public class  DeleteAuthorServlet extends HttpServlet {

  private final AuthorService authorService = new AuthorService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id = Integer.parseInt(req.getParameter("id"));
    this.authorService.deleteAuthor(id);
    resp.sendRedirect("/authors");
  }
}
