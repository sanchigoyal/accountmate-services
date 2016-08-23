package com.services.accountmate.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.services.accountmate.bean.UserProfile;
import com.services.accountmate.exception.ResourceNotFoundException;

/**
 * Data access object implementation for handling UserProfile DB transactions
 * 
 * @author Sanchi
 *
 */
@Transactional
public class UserProfileDaoImpl implements UserProfileDao{

	@PersistenceContext(unitName="accountmateServicesPersistence")
	private EntityManager entityManager;
	
	/**
	 * Method to retrieve UserProfile resource based on the unique userName
	 * 
	 * @param userName
	 * @return UserProfile
	 * @throws ResourceNotFoundException
	 */
	@Override
	public UserProfile getUserProfile(String userName) throws ResourceNotFoundException{
		try {
			String qString = "SELECT u FROM UserProfile u WHERE u.userName = ?1";
			
			TypedQuery<UserProfile> query = entityManager.createQuery(qString, UserProfile.class);		
			query.setParameter(1, userName);

			return query.getSingleResult();
		} catch (NoResultException e) {
			throw new ResourceNotFoundException("UserProfile resource with userName - "+userName+" was not found.");
		}
	}

	/**
	 * Method to create a new UserProfile resource 
	 * 
	 * @param user
	 * @return UserProfile
	 */
	@Override
	public UserProfile createUserProfile(UserProfile user) {
		
		UserProfile createdUser = entityManager.merge(user);
		entityManager.flush();
		return createdUser;
	}
	
	/**
	 * Method to update UserProfile resource 
	 * 
	 * @param user
	 * @return UserProfile
	 * @throws ResourceNotFoundException
	 */
	@Override
	public UserProfile updateUserProfile(UserProfile user) throws ResourceNotFoundException {
		
		// if not exist throw not found exception
		if(entityManager.find(UserProfile.class, user.getUserID()) == null){
			throw new ResourceNotFoundException("UserProfile resource with userId - "+user.getUserID()+" was not found.");
		}
		
		UserProfile updatedUser = entityManager.merge(user);
		return updatedUser;
	}
	
	/**
	 * Method to delete UserProfile resource based on the unique userId
	 * 
	 * @param userId
	 * @return UserProfile
	 * @throws ResourceNotFoundException
	 */
	@Override
	public UserProfile deleteUserProfile(int userId) throws ResourceNotFoundException{

		UserProfile deletedUser = entityManager.find(UserProfile.class, userId);
		if(deletedUser == null){
			throw new ResourceNotFoundException("UserProfile resource with userId - "+userId+" was not found.");
		}
		
		entityManager.remove(deletedUser);
		return deletedUser;
	}

}
