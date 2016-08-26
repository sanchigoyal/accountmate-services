package com.services.accountmate.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.services.accountmate.bean.Company;
import com.services.accountmate.bean.CompanyEntity;
import com.services.accountmate.dao.CompanyDao;
import com.services.accountmate.exception.BeanEntityConversionException;
import com.services.accountmate.util.helper.LinkGenerator;

public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	CompanyDao companyDAO;
	Logger LOGGER = Logger.getLogger(CompanyServiceImpl.class);
	
	public CompanyDao getCompanyDAO() {
		return companyDAO;
	}


	public void setCompanyDAO(CompanyDao companyDAO) {
		this.companyDAO = companyDAO;
	}


	@Override
	public List<Company> getCompanies(String userUUID) {
		
		List<CompanyEntity> entities = companyDAO.getCompanies(userUUID);
		List<Company> companies = null;
			
		try {
			companies = copyCompanyEntityList(entities);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.error(e.getMessage(),e);
			throw new BeanEntityConversionException(e.getMessage());
		}
		
		return companies;
	}
	
	private List<Company> copyCompanyEntityList(List<CompanyEntity> entities)  throws IllegalAccessException, InvocationTargetException{
		if(entities == null){
			return null;
		}
		
		List<Company> companies = new ArrayList<Company>();
		
		for(CompanyEntity entity : entities){
			companies.add(new Company(entity));
		}
		
		return companies;
	}


	@Override
	public void addLinks(UriInfo uriInfo, List<Company> companies) {
		
		/*
		 * Using iterator framework as looping and updating both required together
		 */
		Iterator<Company> iterator = companies.iterator();
		Company company;
		while(iterator.hasNext()){
			company = iterator.next();
			company.addLink(LinkGenerator.getCompanyResourceLink(uriInfo, company.getCompanyID()), "self");
		}
		
	}


	@Override
	public Company getCompany(int companyId, String userUUID) {
		CompanyEntity entity = companyDAO.getCompany(companyId, userUUID);
		Company company = null;
		try {
			company = new Company(entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.error(e.getMessage(),e);
			throw new BeanEntityConversionException(e.getMessage());
		}
		
		return company;
	}


	@Override
	public void addLinks(UriInfo uriInfo, Company company) {
		company.addLink(LinkGenerator.getCompanyResourceLink(uriInfo, company.getCompanyID()), "self");
	}


	@Override
	public Company createCompany(Company company) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
