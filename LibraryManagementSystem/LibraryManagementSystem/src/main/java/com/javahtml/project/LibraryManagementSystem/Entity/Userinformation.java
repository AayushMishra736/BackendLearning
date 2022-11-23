package com.javahtml.project.LibraryManagementSystem.Entity;


import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table
public class Userinformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId",nullable = false)
    private long userId;
    @Column(name = "userName")
    private String userName;
    public enum Gender {
        MALE,FEMALE;
    }
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;
    public enum Usertype{
        LIBRARIAN,STUDENT;
    }
    @Column(name = "userType")
    private Usertype userType;
    @Column(name = "mobileNumber")
    private long mobileNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "activeFlag")
    private int activeFlag;
}
