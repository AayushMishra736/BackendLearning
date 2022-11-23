package com.javahtml.project.LibraryManagementSystem.Repository;

import com.javahtml.project.LibraryManagementSystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface Bookrepository extends JpaRepository<Book,Long> {


    Optional<Book> findBybookName(String bookName);

    Optional<Book> findBookBybookAuthor(String authorName);
}
