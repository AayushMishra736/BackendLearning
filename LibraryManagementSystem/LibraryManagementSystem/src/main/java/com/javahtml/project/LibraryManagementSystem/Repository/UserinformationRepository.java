package com.javahtml.project.LibraryManagementSystem.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javahtml.project.LibraryManagementSystem.Entity.Userinformation;


@Repository
public interface UserinformationRepository extends JpaRepository<Userinformation,Long>{

    @Query("select u from Userinformation u where u.activeFlag = ?1")
    Optional<Userinformation> findByactiveFlag(Long activeFlag);

    @Query("select u from Userinformation u where u.userType = ?1")
    Optional<Userinformation> findByuserType(String userType);

    @Query("select u from Userinformation u where u.userName = ?1")
    Optional<Userinformation> findByuserName(String userName);

}
