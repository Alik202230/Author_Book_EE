package com.book.author_book_ee.service;

import com.book.author_book_ee.database.SQLConnectionProvider;
import com.book.author_book_ee.model.Author;
import com.book.author_book_ee.model.Book;
import com.book.author_book_ee.util.DateUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BookService {
  private final Connection connection = SQLConnectionProvider.getInstance().getConnection();
  private final AuthorService authorService = new AuthorService();

  public void add(Book book) {
    String sql = """
        INSERT INTO book(title, price,qty,author_id,created_at, user_id, image_name)
        VALUES ('%s', '%f', '%d','%d','%s', '%d', '%s');
        """.formatted(
        book.getTitle(),
        book.getPrice(),
        book.getQty(),
        book.getAuthor().getId(),
        DateUtil.fromDateToSqlDateTimeString(book.getCreatedAt()),
        book.getUser().getId(),
        book.getImageName());
    try {
      Statement statement = connection.createStatement();
      statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
      ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
        int anInt = generatedKeys.getInt(1);
        book.setId(anInt);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Book> getAllBooks() {
    String sql = "SELECT * from book";
    return mapToList(sql);
  }

  public List<Book> searchByBookName(String keyword) throws SQLException {
    String sql = "SELECT * from book WHERE title LIKE '%" + keyword + "%'";
    List<Book> result = new ArrayList<>();
    Statement statement = null;
    try {
      connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        Book book = this.mapResultSetToBook(resultSet);
        result.add(book);
      }
    } catch (SQLException | ParseException e) {
      throw new RuntimeException(e.getMessage());
    } finally {
      if (statement != null) {
        statement.close();
      }
    }
    return result;
  }

  public Book getBookById(int id) {
    String sql = "SELECT * from book WHERE id = " + id;
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);
      if (resultSet.next()) {
        return this.mapResultSetToBook(resultSet);
      }
    } catch (SQLException | ParseException e) {
      throw new RuntimeException(e.getMessage());
    }
    return null;
  }

  public void deleteBook(int bookId) throws SQLException {
    String sql = "DELETE FROM book WHERE id = " + bookId;
    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate(sql);
    } catch (SQLException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public List<Book> searchByPrice(double min, double max) {
    String sql = "SELECT * from book WHERE price BETWEEN " + min + " AND " + max;
    return mapToList(sql);
  }

  public List<Book> searchByAuthor(Author author) {
    String sql = "SELECT * from book WHERE author_id = " + author.getId();
    return mapToList(sql);
  }

  private List<Book> mapToList(String sql) {
    List<Book> result = new ArrayList<>();
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        Book book = this.mapResultSetToBook(resultSet);
        result.add(book);
      }
    } catch (SQLException | ParseException e) {
      throw new RuntimeException(e.getMessage());
    }
    return result;
  }

  private Book mapResultSetToBook(ResultSet resultSet) throws SQLException, ParseException {
    Book book = new Book();
    book.setId(resultSet.getInt("id"));
    book.setTitle(resultSet.getString("title"));
    book.setPrice(resultSet.getDouble("price"));
    book.setQty(resultSet.getInt("qty"));
    book.setCreatedAt(DateUtil.fromSqlStringToDateTime(resultSet.getString("created_at")));
    book.setAuthor(authorService.getAuthorById(resultSet.getInt("author_id")));
    book.setImageName(resultSet.getString("image_name"));
    return book;
  }

}
