package com.services.accountmate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.services.accountmate.bean.Company;
import com.services.accountmate.bean.CompanyEntity;
import com.services.accountmate.bean.UserProfileEntity;
import com.services.accountmate.exception.ResourceNotFoundException;

@Transactional
public class CompanyDaoImpl implements CompanyDao {

	@PersistenceContext(unitName="accountmateServicesPersistence")
	private EntityManager entityManager;
	
	@Override
	public List<CompanyEntity> getCompanies(String userUUID) {
		List<CompanyEntity> entities = null;
			
		String qString = "SELECT c FROM CompanyEntity c WHERE c.userUUID = ?1";
		TypedQuery<CompanyEntity> query = entityManager.createQuery(qString, CompanyEntity.class);		
		query.setParameter(1, userUUID);
		entities = query.getResultList();
		
		return entities;
	}

	@Override
	public CompanyEntity getCompany(int companyId, String userUUID) {
		try {
			String qString = "SELECT c FROM CompanyEntity c WHERE c.companyID = ?1 and c.userUUID = ?2";
			
			TypedQuery<CompanyEntity> query = entityManager.createQuery(qString, CompanyEntity.class);		
			query.setParameter(1, companyId);
			query.setParameter(2, userUUID);

			return query.getSingleResult();
		} catch (NoResultException e) {
			throw new ResourceNotFoundException("Company resource with ID - "+companyId+" was not found.");
		}
	}

}
