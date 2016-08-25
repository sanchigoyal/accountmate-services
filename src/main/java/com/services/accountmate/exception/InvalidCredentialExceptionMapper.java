package com.services.accountmate.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.services.accountmate.bean.ErrorMessage;
import com.services.accountmate.util.constant.ErrorConstants;

public class InvalidCredentialExceptionMapper implements ExceptionMapper<InvalidCredentialException> {

	@Override
	public Response toResponse(InvalidCredentialException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ErrorConstants.ERROR_INVALID_USER_CREDENTIAL,ex.getMessage());
		return Response.status(Status.UNAUTHORIZED).entity(errorMessage).build();
	}
	

}
