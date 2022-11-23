package com.javahtml.project.LibraryManagementSystem.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javahtml.project.LibraryManagementSystem.Entity.Booktransaction;
import com.javahtml.project.LibraryManagementSystem.Service.Booktransactionservice;

@RestController
public class Booktransactioncontroller {


    @Autowired
    private Booktransactionservice booktransactionservice;

    @GetMapping("/alltransactions")
    public List<Booktransaction> getalltransactions(){
        return booktransactionservice.getalltransactions();
    }


    @GetMapping("/transactionByBookName/{bookName}")
    public Optional<Booktransaction> gettransactionByBookName(String bookName){
        return booktransactionservice.gettransactionByBookName(bookName);
    }

    @GetMapping("/transactionByStatus/{status}")
    public Optional<Booktransaction> gettransactionByStatus(String status){
        return booktransactionservice.gettransactionByStatus(status);
    }

    @GetMapping("/transactionIssuedto/{issuedTo}")
    public Optional<Booktransaction> gettransactionIssuedto(String issuedTo){
        return booktransactionservice.gettransactionIssuedto(issuedTo);
    }

    @PostMapping("/insertTransaction")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String insertBookInformation(@RequestBody Booktransaction booktransaction){
        booktransactionservice.insertTransaction(booktransaction);
        return "Data Inserted Succesfully.";
    }

    @PutMapping("/updateTransaction")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String updateTransaction(@RequestBody Booktransaction booktransaction){
        booktransactionservice.updateTransaction(booktransaction);
        return "Data Updated Succesfully.";
    }

}
