package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.UsersController;
import org.dselent.scheduling.server.dto.PasswordModificationDto;
import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.dto.UserSearchDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.Login;
import org.dselent.scheduling.server.requests.PasswordModification;
import org.dselent.scheduling.server.requests.Register;
import org.dselent.scheduling.server.requests.UserSearch;
import org.dselent.scheduling.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for handling requests related to the user such as logging in or registering.
 * Controller methods are the first methods reached by the request server-side (with special exception).
 * They parse the request and then call the appropriate service method to execute the business logic.
 * Any results or data is then sent back to the client.
 * 
 * @author dselent
 */
@Controller
public class UsersControllerImpl implements UsersController
{
	@Autowired
    private UserService userService;
    
	/**
	 * 
	 * @param request The body of the request expected to contain a map of String key-value pairs
	 * @return A ResponseEntity for the response object(s) and the status code
	 * @throws Exception 
	 */
	public ResponseEntity<String> register(@RequestBody Map<String, Object> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("register controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String wpiId = (String) request.get(Register.getBodyName(Register.BodyKey.WPI_ID));
		String userName = (String) request.get(Register.getBodyName(Register.BodyKey.USER_NAME));
		String firstName = (String) request.get(Register.getBodyName(Register.BodyKey.FIRST_NAME));
		String lastName = (String) request.get(Register.getBodyName(Register.BodyKey.LAST_NAME));
		String email = (String) request.get(Register.getBodyName(Register.BodyKey.EMAIL));
		String password = (String) request.get(Register.getBodyName(Register.BodyKey.PASSWORD));
		Integer roleId = (Integer) request.get(Register.getBodyName(Register.BodyKey.ROLE_ID)); 

		RegisterUserDto.Builder builder = RegisterUserDto.builder();
		RegisterUserDto registerUserDto = builder.withWPIid(wpiId)
		.withUserName(userName)
		.withFirstName(firstName)
		.withLastName(lastName)
		.withEmail(email)
		.withPassword(password)
		.withRoleId(roleId)
		.build();
		
		userService.registerUser(registerUserDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }

	
	
	@Override
	public ResponseEntity<String> delete(Map<String, Object> request) throws Exception {
		Integer id = (Integer) request.get("id");
		userService.deleteUser(id);
		List<Object> success = new ArrayList<Object>();
		String response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		return new ResponseEntity<String>(response, HttpStatus.OK);
		
	}

	
	
	public ResponseEntity<String> login(@RequestBody Map<String, String> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("login controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String userName = request.get(Login.getBodyName(Login.BodyKey.USER_NAME));
		String password = request.get(Login.getBodyName(Login.BodyKey.PASSWORD));
		
		userService.loginUser(userName, password);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	
	
	public ResponseEntity<String> passwordModification(@RequestBody Map<String, Object> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("passwordModification controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		Object id = request.get(PasswordModification.getBodyName(PasswordModification.BodyKey.ID));
		String oldPassword =(String) request.get(PasswordModification.getBodyName(PasswordModification.BodyKey.OLD_PASSWORD));
		String newPassword =(String) request.get(PasswordModification.getBodyName(PasswordModification.BodyKey.NEW_PASSWORD));
		
		userService.changePassword(id, oldPassword, newPassword);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<String> userSearch(@RequestBody Map<String, String> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("userSearch controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String wpiId = request.get(UserSearch.getBodyName(UserSearch.BodyKey.WPI_ID));
		String userName = request.get(UserSearch.getBodyName(UserSearch.BodyKey.USER_NAME));
		String firstName = request.get(UserSearch.getBodyName(UserSearch.BodyKey.FIRST_NAME));
		String lastName = request.get(UserSearch.getBodyName(UserSearch.BodyKey.LAST_NAME));
		String email = request.get(UserSearch.getBodyName(UserSearch.BodyKey.EMAIL));


		UserSearchDto.Builder builder = UserSearchDto.builder();
		UserSearchDto userSearchDto = builder.withWpiId(wpiId)
		.withUserName(userName)
		.withFirstName(firstName)
		.withLastName(lastName)
		.withEmail(email)
		.build();
		
		userService.searchUser(userSearchDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}

	