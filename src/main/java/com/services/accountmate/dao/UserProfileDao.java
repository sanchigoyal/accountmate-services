package com.services.accountmate.dao;

import com.services.accountmate.bean.UserProfileEntity;
import com.services.accountmate.exception.ResourceNotFoundException;

public interface UserProfileDao {
	
	/** method to retrieve a UserProfile */
	public UserProfileEntity getUserProfile(String userName) throws ResourceNotFoundException;
	
	/** method to create a new UserProfile resource */
	public UserProfileEntity createUserProfile(UserProfileEntity user);

	/** method to update a UserProfile resource */
	public UserProfileEntity updateUserProfile(UserProfileEntity user) throws ResourceNotFoundException;

	/** method to delete a UserProfile resource */
	public UserProfileEntity deleteUserProfile(String userUUID) throws ResourceNotFoundException;
}
