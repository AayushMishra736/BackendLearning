package com.javahtml.project.LibraryManagementSystem.Service;

import com.javahtml.project.LibraryManagementSystem.Entity.Book;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public interface Bookservice {

   public List<Book> getAllBooks();
   public Optional<Book> getBookById(Long id);
   public Optional<Book> getBookByName(String bookName);
   public Optional<Book> getBookByAuthorName(String authorName);
   public void insertBookInformation(Book book);
   public void updateBookInfo(Book book);
}
