package com.services.accountmate.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.services.accountmate.bean.ErrorMessage;
import com.services.accountmate.util.constant.ErrorConstants;

public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {

	@Override
	public Response toResponse(ResourceNotFoundException rnfe) {
		
		ErrorMessage errorMessage = new ErrorMessage(ErrorConstants.ERROR_RESOURCE_NOT_FOUND,rnfe.getMessage());
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}

}
