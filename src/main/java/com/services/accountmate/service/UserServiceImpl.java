package com.services.accountmate.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.services.accountmate.bean.UserProfile;
import com.services.accountmate.dao.UserProfileDao;
import com.services.accountmate.exception.ResourceNotFoundException;

/**
 * @author Sanchi
 *
 */
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserProfileDao userDAO;
	
	
	public UserProfileDao getUserDAO() {
		return userDAO;
	}


	public void setUserDAO(UserProfileDao userDAO) {
		this.userDAO = userDAO;
	}


	@Override
	public UserProfile getUserProfile(String userName)throws ResourceNotFoundException {
		return userDAO.getUserProfile(userName);
	}


	@Override
	public UserProfile createUserProfile(UserProfile user) {
		return userDAO.createUserProfile(user);
	}


	@Override
	public UserProfile updateUserProfile(UserProfile user)throws ResourceNotFoundException {
		return userDAO.updateUserProfile(user);
	}


	@Override
	public UserProfile deleteUserProfile(int userId)throws ResourceNotFoundException {
		return userDAO.deleteUserProfile(userId);
	}

}
