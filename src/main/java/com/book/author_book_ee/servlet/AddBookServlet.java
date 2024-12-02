package com.book.author_book_ee.servlet;

import com.book.author_book_ee.model.Author;
import com.book.author_book_ee.model.Book;
import com.book.author_book_ee.model.User;
import com.book.author_book_ee.service.AuthorService;
import com.book.author_book_ee.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/add-book")
@MultipartConfig(
    maxFileSize = 1024 * 1024 * 5,
    maxRequestSize = 1024 * 1024 * 10,
    fileSizeThreshold = 1024 * 1024
)
public class AddBookServlet extends HttpServlet {

  private final AuthorService authorService = new AuthorService();
  private final BookService bookService = new BookService();

  private static final String IMAGE_UPLOAD_FOLDER = "/home/alik/JavaEE/Author_Book_EE/images/";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Author> authors = this.authorService.getAllAuthors();
    req.setAttribute("authors", authors);
    req.getRequestDispatcher("/WEB-INF/addBook.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("user");

    String title = req.getParameter("title");
    double price = Double.parseDouble(req.getParameter("price"));
    int quantity = Integer.parseInt(req.getParameter("quantity"));
    int authorId = Integer.parseInt(req.getParameter("author_id"));
    Author author = this.authorService.getAuthorById(authorId);

    Part part = req.getPart("img");
    String fileName = null;

    if (part != null && part.getSize() > 0) {
      fileName = System.nanoTime() + "_" + part.getSubmittedFileName();
      part.write(IMAGE_UPLOAD_FOLDER + fileName);
    }

    Book book = new Book(title, author, price, quantity, new Date(), user, fileName);
    this.bookService.add(book);
    resp.sendRedirect("/books");
  }
}
