package com.javahtml.project.LibraryManagementSystem.Serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javahtml.project.LibraryManagementSystem.Entity.Booktransaction;
import com.javahtml.project.LibraryManagementSystem.Repository.Booktransactionrepository;
import com.javahtml.project.LibraryManagementSystem.Service.Booktransactionservice;

@Service
public class Booktransactionserviceimpl implements Booktransactionservice {


    @Autowired
    public Booktransactionrepository booktransactionrepository;

    @Override
    public List<Booktransaction> getalltransactions() {
        return booktransactionrepository.findAll();
    }

    @Override
    public Optional<Booktransaction> gettransactionByBookName(String bookName) {
        return booktransactionrepository.findTransactionBybookName(bookName);
    }

    @Override
    public Optional<Booktransaction> gettransactionByStatus(String status) {
        return booktransactionrepository.findTransactionBytransactionStatus(status);
    }

    @Override
    public Optional<Booktransaction> gettransactionIssuedto(String issuedTo) {
        return booktransactionrepository.findtransactionByissuedTo(issuedTo);
    }

    @Override
    public void insertTransaction(Booktransaction booktransaction) {
        booktransactionrepository.save(booktransaction);
        
    }

    @Override
    public void updateTransaction(Booktransaction booktransaction) {
        booktransactionrepository.save(booktransaction);
        
    }
    
}
