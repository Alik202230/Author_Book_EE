package com.book.author_book_ee.filter;

import com.book.author_book_ee.model.Role;
import com.book.author_book_ee.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/addAuthor", "/add-book", "/delete-author", "/edit-author"})
public class AdminAuthFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    User user = (User) req.getSession().getAttribute("user");
    if (user != null && user.getRole() == Role.ADMIN) {
      chain.doFilter(req, response);
    } else {
      ((HttpServletResponse)response).sendRedirect("/");
    }
  }
}
