package com.javahtml.project.LibraryManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javahtml.project.LibraryManagementSystem.Entity.Userinformation;

@Service
public interface UserInformationservice {

    List<Userinformation> getAllUsers();

    Optional<Userinformation> getUserById(Long id);

    Optional<Userinformation> getuserBystatus(Long activeFlag);

    Optional<Userinformation> getuserByType(String userType);

    Optional<Userinformation> getuserByName(String userType);

    void insertUserInformation(Userinformation userinformation);

    void updateUserInformation(Userinformation userinformation);

}

