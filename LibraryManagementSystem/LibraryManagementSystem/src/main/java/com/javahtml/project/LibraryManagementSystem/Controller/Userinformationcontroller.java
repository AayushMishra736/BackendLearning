package com.javahtml.project.LibraryManagementSystem.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.javahtml.project.LibraryManagementSystem.Entity.Booktransaction;
import com.javahtml.project.LibraryManagementSystem.Entity.Userinformation;
import com.javahtml.project.LibraryManagementSystem.Service.UserInformationservice;

@Controller
public class Userinformationcontroller {
    
    @Autowired 
    private UserInformationservice userInformationService;
    
    @GetMapping("/allUsers")
    public List<Userinformation> getAllUsers(){
        return userInformationService.getAllUsers();
    }


    @GetMapping("/usersById/{Id}")
    public Optional<Userinformation> getUserById(Long Id){
        return userInformationService.getUserById(Id);
    }

    @GetMapping("/userBystatus/{status}")
    public Optional<Userinformation> getuserBystatus(Long activeFlag){
        return userInformationService.getuserBystatus(activeFlag);
    }

    @GetMapping("/userByType/{userType}")
    public Optional<Userinformation> getuserByType(String userType){
        return userInformationService.getuserByType(userType);
    }
    
    @GetMapping("/userByName/{Name}")
    public Optional<Userinformation> getuserByName(String userName){
        return userInformationService.getuserByName(userName);
    }
    
    @PostMapping("/insertUserData")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String insertUserInformation(@RequestBody Userinformation userinformation){
        userInformationService.insertUserInformation(userinformation);
        return "Data Inserted Succesfully.";
    }

    @PutMapping("/updateUserInformation")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String updateUserInformation(@RequestBody Userinformation userinformation){
        userInformationService.updateUserInformation(userinformation);
        return "Data Updated Succesfully.";
    }

}
