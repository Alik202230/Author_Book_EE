package com.book.author_book_ee.service;

import com.book.author_book_ee.database.SQLConnectionProvider;
import com.book.author_book_ee.model.Role;
import com.book.author_book_ee.model.User;

import java.sql.*;

public class UserService {
  private final Connection connection = SQLConnectionProvider.getInstance().getConnection();

  public void add(User user) {
    String sql = """
        INSERT INTO user(name,surname,email,password,role)
        VALUES ('%s','%s','%s','%s','%s')
        """.formatted(
        user.getName(),
        user.getSurname(),
        user.getEmail(),
        user.getPassword(),
        user.getRole().name()
    );
    try {
      Statement statement = connection.createStatement();
      statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
      ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
        int id = generatedKeys.getInt(1);
        user.setId(id);
      }

    } catch (SQLException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public User getUserByEmailAndPassword(String email, String password) {
    String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
    try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        return new User(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("surname"),
            resultSet.getString("email"),
            resultSet.getString("password"),
            Role.valueOf(resultSet.getString("role"))
        );
      }
    } catch (SQLException e) {
      throw new RuntimeException(e.getMessage());
    }
    return null;
  }

  public User getUserByEmail(String email) {
    String sql = "SELECT * FROM user WHERE email = ?";
    try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
      preparedStatement.setString(1, email);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return new User(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("surname"),
            resultSet.getString("email"),
            resultSet.getString("password"),
            Role.valueOf(resultSet.getString("role"))
        );
      }
    }catch (SQLException e) {
      throw new RuntimeException(e.getMessage());
    }
    return null;
  }

}
