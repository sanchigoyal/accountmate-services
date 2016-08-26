package com.services.accountmate.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.services.accountmate.bean.Company;

public interface CompanyService {

	List<Company> getCompanies(String userUUID);

	void addLinks(UriInfo uriInfo, List<Company> companies);

	Company getCompany(int companyId, String userUUID);

	void addLinks(UriInfo uriInfo, Company company);

	Company createCompany(Company company);

}
