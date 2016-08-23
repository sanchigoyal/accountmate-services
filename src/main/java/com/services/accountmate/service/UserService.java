package com.services.accountmate.service;

import com.services.accountmate.bean.UserProfile;
import com.services.accountmate.exception.ResourceNotFoundException;

public interface UserService {
	public UserProfile getUserProfile(String userName)throws ResourceNotFoundException;

	public UserProfile createUserProfile(UserProfile user);

	public UserProfile updateUserProfile(UserProfile user)throws ResourceNotFoundException;

	public UserProfile deleteUserProfile(int userId)throws ResourceNotFoundException;
}
