package com.javahtml.project.LibraryManagementSystem.Serviceimpl;

import com.javahtml.project.LibraryManagementSystem.Entity.Book;
import com.javahtml.project.LibraryManagementSystem.Repository.Bookrepository;
import com.javahtml.project.LibraryManagementSystem.Service.Bookservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Bookserviceimpl implements Bookservice {

    @Autowired
    private Bookrepository bookrepository;

    @Override
    public List<Book> getAllBooks() {
        return bookrepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookrepository.findById(id);
    }

    @Override
    public Optional<Book> getBookByName(String bookName) {
        return bookrepository.findBybookName(bookName);
    }

    @Override
    public Optional<Book> getBookByAuthorName(String authorName) {
        return bookrepository.findBookBybookAuthor(authorName);
    }

    @Override
    public void insertBookInformation(Book book) {
        bookrepository.save(book);
    }

    @Override
    public void updateBookInfo(Book book) {
    Book bookinfo = new Book();
    bookinfo.setBookAuthor(book.getBookAuthor());
    bookinfo.setBookName(book.getBookName());
    bookinfo.setPublication(book.getPublication());
    bookinfo.setNumberOfCopies(book.getNumberOfCopies());
    bookinfo.setActiveFlag(book.getActiveFlag());
    }
}
