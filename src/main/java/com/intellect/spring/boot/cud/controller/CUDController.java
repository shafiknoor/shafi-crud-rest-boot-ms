package com.intellect.spring.boot.cud.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.intellect.spring.boot.cud.constants.CUDConstants;
import com.intellect.spring.boot.cud.exception.ApplicationException;
import com.intellect.spring.boot.cud.exception.ApplicationExceptionFactory;
import com.intellect.spring.boot.cud.model.User;
import com.intellect.spring.boot.cud.response.CUDResponse;
import com.intellect.spring.boot.cud.service.UserService;
 
@RestController
@RequestMapping("/v1/boot/api")
public class CUDController {
 
    public static final Logger logger = LoggerFactory.getLogger(CUDController.class);
 
    @Autowired
    UserService userService; //Service which will do all data retrieval/manipulation work
    
    @Autowired
	ApplicationExceptionFactory applicationExceptionFactory;
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() throws ApplicationException, ParseException {
        List<User> users = userService.findAllUsers();
        if (null ==users || users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 
    // -------------------Create a User-------------------------------------------
 
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<CUDResponse> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) throws ApplicationException{
    	CUDResponse response = new CUDResponse();
    	userService.validateUserCreation(user);
        if (userService.isUserExist(user)) {
            logger.error("Unable to create. A User with name {} already exist", user.getEmail());
            throw applicationExceptionFactory
			.createNewAppexceptionWithErrorDetails(
					CUDConstants.ERR_DATA_CONFLICT,
					"Unable to create. A User with name " + user.getEmail() + " already exist.");
        }       
        userService.saveUser(user); 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/boot/api/user/{id}").buildAndExpand(user.getId()).toUri());
        response.setResMsg("Created New User");
        response.setRespDesc("UserId : "+user.getId() + " Created");
        return new ResponseEntity<CUDResponse>(response,headers, HttpStatus.CREATED);
    }
 
    // ------------------- Update a User ------------------------------------------------
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CUDResponse> updateUser(@PathVariable("id") long id, @RequestBody User user) throws ApplicationException{
        logger.info("Updating User with id {}", id);
        CUDResponse response = new CUDResponse();
        userService.validateUserUpdate(user);
        User currentUser = userService.findById(id);
 
        if (currentUser == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            throw applicationExceptionFactory
			.createNewAppexceptionWithErrorDetails(CUDConstants.ERR_NO_DATA_FOUND, "Unable to upate. User with id " + id);
        }
        
        currentUser.setPinCode(user.getPinCode());
        currentUser.setBirthDate(user.getBirthDate());
        userService.updateUser(currentUser);
        response.setResMsg("Updated User");
        response.setRespDesc("UserId : "+ id + " Updated");
        return new ResponseEntity<CUDResponse>(response, HttpStatus.OK);
    }
 
    // ------------------- Delete a User-----------------------------------------
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CUDResponse> deleteUser(@PathVariable("id") long id) throws ApplicationException{
        logger.info("Fetching & Deleting User with id {}", id);
        CUDResponse response = new CUDResponse();
        User user = userService.findById(id);
        if (user == null) {
            logger.error("Unable to delete. User with id {} not found.", id);
            throw applicationExceptionFactory
			.createNewAppexceptionWithErrorDetails(CUDConstants.ERR_NO_DATA_FOUND, "Unable to delete. User with id " + id );
        }
        userService.deleteUserById(id);
        response.setResMsg("Updated User");
        response.setRespDesc("UserId : "+ id + " Updated");
        return new ResponseEntity<CUDResponse>(response,HttpStatus.NO_CONTENT);
    }
 
}