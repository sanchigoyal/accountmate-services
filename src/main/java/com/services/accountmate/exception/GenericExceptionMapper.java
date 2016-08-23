package com.services.accountmate.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.log4j.Logger;

import com.services.accountmate.bean.ErrorMessage;
import com.services.accountmate.util.constant.ErrorConstants;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	
	Logger LOGGER = Logger.getLogger(GenericExceptionMapper.class);
	
	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(ErrorConstants.ERROR_TECHNICAL, "Technical Error :: "+ex.getMessage());
		LOGGER.error(ex.getMessage(),ex);
		
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
		
	}
	

}
