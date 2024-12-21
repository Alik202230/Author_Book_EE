package com.book.author_book_ee.servlet;

import com.book.author_book_ee.model.Author;
import com.book.author_book_ee.model.Gender;
import com.book.author_book_ee.service.AuthorService;
import com.book.author_book_ee.util.DateUtil;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addAuthor")
public class AddAuthorServlet extends HttpServlet {
  private final AuthorService authorService = new AuthorService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/addAuthor.jsp").forward(req, resp);
  }

  @SneakyThrows
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String name = req.getParameter("name");
    String surname = req.getParameter("surname");
    String phone = req.getParameter("phone");
    String dob = req.getParameter("dob");
    String gender = req.getParameter("gender");

    Author author = Author.builder()
        .name(name) 
        .surname(surname)
        .phone(phone)
        .dateOfBirthday(DateUtil.fromWebStringToDate(dob))
        .gender(Gender.valueOf(gender))
        .build();
    authorService.add(author);
    resp.sendRedirect("/authors");
  }
}
