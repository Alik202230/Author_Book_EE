package com.book.author_book_ee.servlet;

import com.book.author_book_ee.model.User;
import com.book.author_book_ee.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private final UserService userService = new UserService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String email = req.getParameter("email");
    String password = req.getParameter("password");

    User user = this.userService.getUserByEmailAndPassword(email, password);

    HttpSession session = req.getSession();

    if (user != null) {
      session.setAttribute("user", user);
      resp.sendRedirect("/");
    } else {
      session.setAttribute("msg", "Email or password is incorrect");
      resp.sendRedirect("/login");
    }

//    if (user != null) {
//      req.setAttribute("user", user);
//      req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
//    } else {
//      req.setAttribute("msg", "Username or password is incorrect");
//      req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
//    }

  }
}
