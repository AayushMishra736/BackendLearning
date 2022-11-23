package com.javahtml.project.LibraryManagementSystem.Entity;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "bookId",nullable = false)
    private Long bookId;
    @Column(name = "bookName")
    private String bookName;
    @Column(name = "bookAuthor")
    private String bookAuthor;
    @Column(name = "publication")
    private String publication;
    @Column(name = "numberOfCopies")
    private long numberOfCopies;
    @Column(name = "activeFlag")
    private int activeFlag;
    public enum BookAvailable {
        AVAILABLE,UNAVAILABLE
    }
    @Column(name = "bookAvailable")
    @Enumerated(EnumType.STRING)
    private BookAvailable bookAvailable;
}
