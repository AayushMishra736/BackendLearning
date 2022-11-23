package com.javahtml.project.LibraryManagementSystem.Serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javahtml.project.LibraryManagementSystem.Entity.Userinformation;
import com.javahtml.project.LibraryManagementSystem.Repository.UserinformationRepository;
import com.javahtml.project.LibraryManagementSystem.Service.UserInformationservice;


@Service
public class UserInformationserviceimpl implements  UserInformationservice{
    
    @Autowired
    private  UserinformationRepository userinformationRepository ;

    @Override
    public List<Userinformation> getAllUsers() {
        return userinformationRepository.findAll();
    }

    @Override
    public Optional<Userinformation> getUserById(Long id) {
        return userinformationRepository.findById(id);
    }

    @Override
    public Optional<Userinformation> getuserBystatus(Long activeFlag) {
        return userinformationRepository.findByactiveFlag(activeFlag);
    }

    @Override
    public Optional<Userinformation> getuserByType(String userType) {
        return userinformationRepository.findByuserType(userType); 
    }

    @Override
    public Optional<Userinformation> getuserByName(String userName) {
        return userinformationRepository.findByuserName(userName); 
    }

    @Override
    public void insertUserInformation(Userinformation userinformation) {
         userinformationRepository.save(userinformation); 
    }

    @Override
    public void updateUserInformation(Userinformation userinformation) {
        userinformationRepository.save(userinformation);      
    }   
}