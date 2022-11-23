package com.javahtml.project.LibraryManagementSystem.Controller;

import com.javahtml.project.LibraryManagementSystem.Entity.Book;
import com.javahtml.project.LibraryManagementSystem.Service.Bookservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class Bookcontroller {

    @Autowired
    private Bookservice bookservice;

    @GetMapping("/allBook")
    public List<Book> getAllBooks(){
        return bookservice.getAllBooks();
    }

    @GetMapping("/bookById/{Id}")
    public Optional<Book> getBookById(Long Id){
        return  bookservice.getBookById(Id);
    }

    @GetMapping("/bookByName/{bookName}")
    public Optional<Book> getBookByName(String bookName){
        return bookservice.getBookByName(bookName);
    }

    @GetMapping("/bookByAuthorName/{authorName}")
    public Optional<Book> getBookByAuthorName(String authorName){
        return bookservice.getBookByAuthorName(authorName);
    }

    @PostMapping("/books")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String insertBookInformation(@RequestBody Book book){
        bookservice.insertBookInformation(book);
        return "Data Inserted Succesfully.";
    }

    @PutMapping("/updateBook")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String updateBookInfo(@RequestBody Book book){
        bookservice.updateBookInfo(book);
        return "Data Updated Succesfully.";
    }
}
