package com.services.accountmate.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.services.accountmate.bean.Company;

@Path("/")
public class CompanyResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Company> getCompanies(){
		return null;
	}
}
