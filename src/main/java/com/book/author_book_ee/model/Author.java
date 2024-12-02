package com.book.author_book_ee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
  private int id;
  private String name;
  private String surname;
  private String phone;
  private Date dateOfBirthday;
  private Gender gender;
}
