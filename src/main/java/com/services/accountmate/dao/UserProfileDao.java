package com.services.accountmate.dao;

import com.services.accountmate.bean.UserProfile;
import com.services.accountmate.exception.ResourceNotFoundException;

public interface UserProfileDao {
	
	/** method to retrieve a UserProfile */
	public UserProfile getUserProfile(String userName) throws ResourceNotFoundException;
	
	/** method to create a new UserProfile resource */
	public UserProfile createUserProfile(UserProfile user);

	/** method to update a UserProfile resource */
	public UserProfile updateUserProfile(UserProfile user) throws ResourceNotFoundException;

	/** method to delete a UserProfile resource */
	public UserProfile deleteUserProfile(int userId) throws ResourceNotFoundException;
}
