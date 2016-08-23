package com.services.accountmate.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.services.accountmate.bean.UserProfile;
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
	 * Operation to return UserProfile resource based on the unique userName
	 * 
	 * @param userName
	 * @return 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userName}")
	public Response getUser(@PathParam("userName")String userName){
		UserProfile user = userService.getUserProfile(userName);
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
	@Path("{userId}")
	public Response updateUser(@PathParam("userId")int userId, UserProfile user, @Context UriInfo uriInfo){
		user.setUserID(userId);
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
	@Path("{userId}")
	public Response deleteUser(@PathParam("userId")int userId){
		UserProfile deletedUser = userService.deleteUserProfile(userId);
		return Response.status(Status.OK)
				.entity(deletedUser)
				.build();
	}
}
