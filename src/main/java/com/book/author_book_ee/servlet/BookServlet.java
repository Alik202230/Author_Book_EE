package com.book.author_book_ee.servlet;

import com.book.author_book_ee.model.Book;
import com.book.author_book_ee.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
  private final BookService bookService = new BookService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    List<Book> allBooks = bookService.getAllBooks();
    req.setAttribute("books", allBooks);
    req.getRequestDispatcher("/WEB-INF/books.jsp").forward(req, resp);


  }

//    HttpSession session = req.getSession();
//    User user = (User) session.getAttribute("user");
//
//    if (user != null) {
//      List<Book> allBooks = bookService.getAllBooks();
//      req.setAttribute("books", allBooks);
//      req.getRequestDispatcher("/WEB-INF/books.jsp").forward(req, resp);
//    } else {
//      resp.sendRedirect("/");
//    }
//  }
}
