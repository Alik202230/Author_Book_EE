package com.book.author_book_ee.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/get-image")
public class GetImageServlet extends HttpServlet {
  
  private static final String IMAGE_UPLOAD_FOLDER = "/home/alik/JavaEE/Author_Book_EE/images/";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String imageName = req.getParameter("imageName");

    File imagFile = new File(IMAGE_UPLOAD_FOLDER, imageName);
    if (imagFile.exists()) {
      try(FileInputStream inputStream = new FileInputStream(imagFile)) {
        resp.setContentType("image/jpeg");
        resp.setContentLength((int) imagFile.length());
        OutputStream outputStream = resp.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
          outputStream.write(buffer, 0, bytesRead);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

}