package com.services.accountmate.resource;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.services.accountmate.bean.UserProfile;
import com.services.accountmate.exception.InvalidCredentialException;
import com.services.accountmate.exception.ResourceNotFoundException;
import com.services.accountmate.service.UserService;

/**
 * Service class that handles REST request for User Profile
 * 
 * @author Sanchi
 *
 */
@Path("/users")
public class UserProfileResource {
	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	static Logger LOGGER = Logger.getLogger(UserProfileResource.class);
	
	/**
	 * Operation to return UserProfile resource only if credentials are correct
	 * 
	 * Adding credential validation wont expose all user's info
	 * 
	 * @param userName
	 * @return 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@QueryParam("email")String email, @QueryParam("password") String password, @Context UriInfo uriInfo){
		UserProfile user = new UserProfile();
		try{
			user = userService.getUserProfile(email);
			
			/* validate user credential */
			if(!userService.validate(user, password)){
				throw new InvalidCredentialException("User credentials are invalid");
			}
			
			/* add link to parent resource */
			userService.addLink(user, uriInfo);
			
		}catch(ResourceNotFoundException ex){
			throw new InvalidCredentialException("User credentials are invalid");
		}
		return Response.status(Response.Status.OK).entity(user).build();
	}
	
	/**
	 * Operation to create a UserProfile resource 
	 * @param user
	 * @param uriInfo
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(UserProfile user, @Context UriInfo uriInfo){
		
		/* adding unique id to user resource */
		UUID userUUID = UUID.randomUUID();
		user.setUserUUID(userUUID.toString());
		
		UserProfile createdUser = userService.createUserProfile(user);
		return Response.created(uriInfo.getAbsolutePathBuilder()
									.path(createdUser.getUserName())
									.build())
				.entity(createdUser)
				.build();
	}
	
	/**
	 * Operation to update a UserProfile resource
	 * @param userId
	 * @param user
	 * @param uriInfo
	 * @return
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userUUID}")
	public Response updateUser(@PathParam("userUUID")String userUUID, UserProfile user, @Context UriInfo uriInfo){
		
		/* reset UUID in case UUID in body is dummy or NULL */
		user.setUserUUID(userUUID);
		UserProfile updatedUser = userService.updateUserProfile(user);
		return Response.status(Status.OK)
				.entity(updatedUser)
				.header("Location", uriInfo.getAbsolutePathBuilder().path(updatedUser.getUserName()).build())
			    .build();
	}
	
	/**
	 * Operation to delete a UserProfile resource
	 * @param userId
	 * @return
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userUUID}")
	public Response deleteUser(@PathParam("userUUID")String userUUID){
		UserProfile deletedUser = userService.deleteUserProfile(userUUID);
		return Response.status(Status.OK)
				.entity(deletedUser)
				.build();
	}
}
