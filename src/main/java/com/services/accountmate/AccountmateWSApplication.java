package com.services.accountmate;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.services.accountmate.exception.BeanEntityConversionExceptionMapper;
import com.services.accountmate.exception.GenericExceptionMapper;
import com.services.accountmate.exception.InvalidCredentialExceptionMapper;
import com.services.accountmate.exception.ResourceNotFoundExceptionMapper;
import com.services.accountmate.resource.CompanyResource;
import com.services.accountmate.resource.UOMResource;
import com.services.accountmate.resource.UserProfileResource;


/**
 * Registers the components to be used by the JAX-RS application
 * 
 * @author Sanchi
 *
 */
public class AccountmateWSApplication extends ResourceConfig {
	
	/**
	 * Registers JAX-RS application components
	 */
	public AccountmateWSApplication(){
		
		// register application resources
		register(UserProfileResource.class);
		register(CompanyResource.class);
		register(UOMResource.class);
	
		// register filters
		register(RequestContextFilter.class);
		
		// register exception mapper
		register(ResourceNotFoundExceptionMapper.class);
		register(BeanEntityConversionExceptionMapper.class);
		register(InvalidCredentialExceptionMapper.class);
		register(GenericExceptionMapper.class);
		
		// register features
		register(JacksonFeature.class);
	}
}
