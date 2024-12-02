package com.book.author_book_ee.servlet;

import com.book.author_book_ee.model.Role;
import com.book.author_book_ee.model.User;
import com.book.author_book_ee.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

  private final UserService userService = new UserService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String name = req.getParameter("name");
    String surname = req.getParameter("surname");
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    Role role = Role.valueOf(req.getParameter("role"));

    HttpSession session = req.getSession();

    StringBuilder stringBuilder  = new StringBuilder();

    if (name == null || name.trim().isEmpty()) {
      stringBuilder.append("Name cannot be empty");
      stringBuilder.append("<br>");
    }

    if (surname == null || surname.trim().isEmpty()) {
      stringBuilder.append("Surname cannot be empty");
      stringBuilder.append("<br>");
    }

    if (email == null || email.trim().isEmpty()) {
      stringBuilder.append("Email cannot be empty");
      stringBuilder.append("<br>");
    }

    if (password == null || password.trim().isEmpty() || password.length() < 6) {
      stringBuilder.append("Password cannot be empty or less than 6 characters");
      stringBuilder.append("<br>");
    }

    if (!stringBuilder.isEmpty()) {
      session.setAttribute("msg", stringBuilder.toString());
    } else if (this.userService.getUserByEmail(email) != null) {
      stringBuilder.append("Email already exist");
      stringBuilder.append("<br>");
    } else {
      User user = new User(name, surname, email, password, role);
      this.userService.add(user);
      session.setAttribute("msg", "User successfully registered!");
    }
    resp.sendRedirect("/register");

//    if (this.userService.getUserByEmail(email) != null) {
//      req.setAttribute("msg", "User already exists");
//      req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
//    } else {
//      User user = new User(name, surname, email, password, role);
//      this.userService.add(user);
//      req.setAttribute("msg", "User registered successfully");
//      req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
//    }
  }
}
