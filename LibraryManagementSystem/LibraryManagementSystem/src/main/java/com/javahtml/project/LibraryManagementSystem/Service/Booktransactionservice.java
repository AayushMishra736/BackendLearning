package com.javahtml.project.LibraryManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javahtml.project.LibraryManagementSystem.Entity.Booktransaction;

@Service
public interface Booktransactionservice {

    List<Booktransaction> getalltransactions();

    Optional<Booktransaction> gettransactionByBookName(String bookName);

    Optional<Booktransaction> gettransactionByStatus(String status);

    Optional<Booktransaction> gettransactionIssuedto(String issuedTo);

    void insertTransaction(Booktransaction booktransaction);

    void updateTransaction(Booktransaction booktransaction);
    
}
