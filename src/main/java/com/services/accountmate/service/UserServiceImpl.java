package com.services.accountmate.service;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.services.accountmate.bean.UserProfile;
import com.services.accountmate.bean.UserProfileEntity;
import com.services.accountmate.dao.UserProfileDao;
import com.services.accountmate.exception.BeanEntityConversionException;
import com.services.accountmate.exception.InvalidCredentialException;
import com.services.accountmate.exception.ResourceNotFoundException;
import com.services.accountmate.util.helper.LinkGenerator;

/**
 * @author Sanchi
 *
 */
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserProfileDao userDAO;
	Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
	
	public UserProfileDao getUserDAO() {
		return userDAO;
	}


	public void setUserDAO(UserProfileDao userDAO) {
		this.userDAO = userDAO;
	}


	@Override
	public UserProfile getUserProfile(String userName)throws ResourceNotFoundException, BeanEntityConversionException {
		UserProfile user = new UserProfile();
		
		try{	
			user = new UserProfile(userDAO.getUserProfile(userName));
		}catch(IllegalAccessException iae){
			LOGGER.error(iae.getMessage(), iae);
			throw new BeanEntityConversionException(iae.getMessage());
		}catch(InvocationTargetException ite){
			LOGGER.error(ite.getMessage(), ite);
			throw new BeanEntityConversionException(ite.getMessage());
		}
		return user;
	}


	@Override
	public UserProfile createUserProfile(UserProfile user) throws BeanEntityConversionException {
		UserProfileEntity entity = new UserProfileEntity();
		UserProfile createdUser = new UserProfile();
		
		try{
			entity = new UserProfileEntity(user);
			createdUser = new UserProfile(userDAO.createUserProfile(entity));
		}catch(IllegalAccessException iae){
			LOGGER.error(iae.getMessage(), iae);
			throw new BeanEntityConversionException(iae.getMessage());
		}catch(InvocationTargetException ite){
			LOGGER.error(ite.getMessage(), ite);
			throw new BeanEntityConversionException(ite.getMessage());
		}
		
		return createdUser;
	}


	@Override
	public UserProfile updateUserProfile(UserProfile user)throws ResourceNotFoundException, BeanEntityConversionException {
		UserProfileEntity entity = new UserProfileEntity();
		UserProfile updatedUser = new UserProfile();
		
		try{
			entity = new UserProfileEntity(user);
			updatedUser = new UserProfile(userDAO.updateUserProfile(entity));
		}catch(IllegalAccessException iae){
			LOGGER.error(iae.getMessage(), iae);
			throw new BeanEntityConversionException(iae.getMessage());
		}catch(InvocationTargetException ite){
			LOGGER.error(ite.getMessage(), ite);
			throw new BeanEntityConversionException(ite.getMessage());
		}
		return updatedUser;
	}


	@Override
	public UserProfile deleteUserProfile(String userUUID)throws ResourceNotFoundException, BeanEntityConversionException {
		UserProfile deletedUser = new UserProfile();
		try{
			deletedUser = new UserProfile(userDAO.deleteUserProfile(userUUID));
		}catch(IllegalAccessException iae){
			LOGGER.error(iae.getMessage(), iae);
			throw new BeanEntityConversionException(iae.getMessage());
		}catch(InvocationTargetException ite){
			LOGGER.error(ite.getMessage(), ite);
			throw new BeanEntityConversionException(ite.getMessage());
		}
		
		return deletedUser;
	}


	@Override
	public boolean validate(UserProfile user, String password) {
		if(password!= null && password.equals(user.getPassword())){
			return true;
		}
		return false;
	}


	@Override
	public void addLink(UserProfile user, UriInfo uriInfo) {
		/* add link to self */
		user.addLink(LinkGenerator.getUserProfileResourceLink(uriInfo, user.getUserName(), user.getPassword()), "self");
	}

}
