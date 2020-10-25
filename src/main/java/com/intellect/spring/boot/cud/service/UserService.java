package com.intellect.spring.boot.cud.service;

import java.util.List;

import com.intellect.spring.boot.cud.exception.ApplicationException;
import com.intellect.spring.boot.cud.model.User;

public interface UserService {
    
    User findById(long id)throws ApplicationException;
     
    User findByEmail(String name)throws ApplicationException;
     
    void saveUser(User user)throws ApplicationException;
     
    void updateUser(User user)throws ApplicationException;
     
    void deleteUserById(long id)throws ApplicationException;
 
    List<User> findAllUsers()throws ApplicationException;
     
    boolean isUserExist(User user)throws ApplicationException;

	void validateUserUpdate(User user)throws ApplicationException;
	
	void validateUserCreation(User user)throws ApplicationException;
     
}
