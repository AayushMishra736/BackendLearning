package com.javahtml.project.LibraryManagementSystem.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Booktransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transactionId", nullable = false)
    private long transactionId;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book bookId;
    
    @ManyToOne
    @JoinColumn(name = "bookName")
    private Book bookName;

    @ManyToOne
    @JoinColumn(name = "issuedTo")
    private Userinformation issuedTo;

    @ManyToOne
    @JoinColumn(name = "issuedBy")
    private Userinformation issuedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY")
    private Date bookIssueddate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY")
    private Date bookReturndate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY")
    private Date bookActualreturndate;

    public enum transactionStatus{
        APROVED,PENDING,COMPLETED;
    }
    @Enumerated(EnumType.ORDINAL)
    private transactionStatus transactionStatus;

    private String remarks;

}
