package com.intellect.spring.boot.cud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.intellect.spring.boot.cud.constants.CUDConstants;
import com.intellect.spring.boot.cud.exception.ApplicationException;
import com.intellect.spring.boot.cud.exception.ApplicationExceptionFactory;
import com.intellect.spring.boot.cud.model.User;
import com.intellect.spring.boot.cud.util.DateUtil;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
    ApplicationExceptionFactory applicationExceptionFactory;
	
	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final AtomicLong counter = new AtomicLong();
     
    private static List<User> users;
    static{
        users= populateDummyUsers();
    }
 
    @Override
    public List<User> findAllUsers() throws ApplicationException{
        return users;
    }
     
    @Override
    public User findById(long id) {
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
     
    @Override
    public User findByEmail(String email) throws ApplicationException{
    	if (null !=users && !users.isEmpty()) {
    		for(User user : users){
                if(user.getEmail().equalsIgnoreCase(email)){
                    return user;
                }
            }
    	}
        
        return null;
    }
     
    @Override
    public void saveUser(User user) throws ApplicationException{
        user.setId(counter.incrementAndGet());
        user.setActive(true);
        logger.info("Creating User : {}", user);
        users.add(user);
    }
 
    @Override
    public void updateUser(User user) throws ApplicationException{
        int index = users.indexOf(user);
        users.set(index, user);
    }
 
    @Override
    public void deleteUserById(long id) throws ApplicationException{
    	for(User user : users){
    		if (user.getId() == id) {
    			user.setActive(false);
            }
    	}
        
    }
 
    @Override
    public boolean isUserExist(User user) throws ApplicationException{
        return findByEmail(user.getEmail())!=null;
    }
    
    private static List<User> populateDummyUsers(){
        List<User> users = new ArrayList<User>();
        return users;
    }

	@Override
	public void validateUserCreation(User user) throws ApplicationException {
		if(null == user.getfName() || user.getfName().isEmpty()){
        	throw this.applicationExceptionFactory.createNewAppexception(CUDConstants.ERR_INSUFFECIENT_MANDATORY_DATA, CUDConstants.ERR_DETAIL_FNAME);
        }else if(null == user.getlName() || user.getlName().isEmpty()){
        	throw this.applicationExceptionFactory.createNewAppexception(CUDConstants.ERR_INSUFFECIENT_MANDATORY_DATA, CUDConstants.ERR_DETAIL_LNAME);
        }else if(null == user.getEmail() || user.getEmail().isEmpty()){
        	throw this.applicationExceptionFactory.createNewAppexception(CUDConstants.ERR_INSUFFECIENT_MANDATORY_DATA, CUDConstants.ERR_DETAIL_EMAIL);
        }else if(null == Long.toString(user.getPinCode())){
        	throw this.applicationExceptionFactory.createNewAppexception(CUDConstants.ERR_INSUFFECIENT_MANDATORY_DATA, CUDConstants.ERR_DETAIL_PINCODE);
        }else if(null == user.getBirthDate() || user.getBirthDate().isEmpty()){
        	throw this.applicationExceptionFactory.createNewAppexception(CUDConstants.ERR_INSUFFECIENT_MANDATORY_DATA, CUDConstants.ERR_DETAIL_BIRTHDATE);
        }
		
		/*validating BirthDate*/
		if (StringUtils.hasText(user.getBirthDate())) {
			try {
				if (!DateUtil.isValidDate(user.getBirthDate())) {
					throw applicationExceptionFactory
							.createNewAppexceptionWithErrorDetails(
									CUDConstants.ERR_INVALID_FORMAT_DATE,
									CUDConstants.ERR_DETAIL_INVALID_DATE);
				}
				
			} catch (Exception ex) {
				throw applicationExceptionFactory
						.createNewAppexceptionWithErrorDetails(
								CUDConstants.ERR_INVALID_FORMAT_DATE,
								CUDConstants.ERR_DETAIL_INVALID_DATE);
			}
			try {
				if (!DateUtil.isFutureDate(user.getBirthDate())) {
					throw applicationExceptionFactory
					.createNewAppexceptionWithErrorDetails(
							CUDConstants.ERR_FUTURE_DATE,
							CUDConstants.ERR_DETAIL_FUTURE_DATE);
				}
			} catch (Exception e) {
				throw applicationExceptionFactory
				.createNewAppexceptionWithErrorDetails(
						CUDConstants.ERR_FUTURE_DATE,
						CUDConstants.ERR_DETAIL_FUTURE_DATE);
			}
		}
	}

	@Override
	public void validateUserUpdate(User user) throws ApplicationException {
		if(null == Long.toString(user.getPinCode())){
        	throw this.applicationExceptionFactory.createNewAppexception(CUDConstants.ERR_INSUFFECIENT_MANDATORY_DATA, CUDConstants.ERR_DETAIL_PINCODE);
        }else if(null == user.getBirthDate() || user.getBirthDate().isEmpty()){
        	throw this.applicationExceptionFactory.createNewAppexception(CUDConstants.ERR_INSUFFECIENT_MANDATORY_DATA, CUDConstants.ERR_DETAIL_BIRTHDATE);
        }
		
	}
 
}
