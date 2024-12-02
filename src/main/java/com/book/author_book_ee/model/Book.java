package com.book.author_book_ee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  private int id;
  private String title;
  private Author author;
  private double price;
  private int qty;
  private Date createdAt;
  private User user;
  private String imageName;

  public Book(String title, Author author, double price, int qty, Date createdAt) {
    this.title = title;
    this.author = author;
    this.price = price;
    this.qty = qty;
    this.createdAt = createdAt;
  }

  public Book(String title, Author author, double price, int qty, Date createdAt, User user, String imageName) {
    this.title = title;
    this.author = author;
    this.price = price;
    this.qty = qty;
    this.createdAt = createdAt;
    this.user = user;
    this.imageName = imageName;
  }
}
