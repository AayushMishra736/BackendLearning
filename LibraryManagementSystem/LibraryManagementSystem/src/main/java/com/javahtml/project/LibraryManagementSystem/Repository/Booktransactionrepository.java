package com.javahtml.project.LibraryManagementSystem.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javahtml.project.LibraryManagementSystem.Entity.Booktransaction;

@Repository
public interface Booktransactionrepository extends JpaRepository<Booktransaction,Long>{

    @Query("select u from Booktransaction u where u.bookName = ?1")
    Optional<Booktransaction> findTransactionBybookName(String bookName);

    @Query("select u from Booktransaction u where u.transactionStatus = ?1")
    Optional<Booktransaction> findTransactionBytransactionStatus(String status);

    @Query("select u from Booktransaction u where u.issuedTo = ?1")
    Optional<Booktransaction> findtransactionByissuedTo(String issuedTo);


}
