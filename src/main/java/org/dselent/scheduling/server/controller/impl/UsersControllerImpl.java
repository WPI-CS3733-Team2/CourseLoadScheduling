package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.UsersController;
import org.dselent.scheduling.server.dto.LoginUserDto;
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
	public ResponseEntity<String> register(@RequestBody Map<String, String> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String userName = request.get(Register.getBodyName(Register.BodyKey.USER_NAME));
		String firstName = request.get(Register.getBodyName(Register.BodyKey.FIRST_NAME));
		String lastName = request.get(Register.getBodyName(Register.BodyKey.LAST_NAME));
		String email = request.get(Register.getBodyName(Register.BodyKey.EMAIL));
		String password = request.get(Register.getBodyName(Register.BodyKey.PASSWORD));

		RegisterUserDto.Builder builder = RegisterUserDto.builder();
		RegisterUserDto registerUserDto = builder.withUserName(userName)
		.withFirstName(firstName)
		.withLastName(lastName)
		.withEmail(email)
		.withPassword(password)
		.build();
		
		userService.registerUser(registerUserDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

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


		LoginUserDto.Builder builder = LoginUserDto.builder();
		LoginUserDto loginUserDto = builder.withUserName(userName)
		.withPassword(password)
		.build();
		
		userService.loginUser(loginUserDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<String> passwordModification(@RequestBody Map<String, String> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("passwordModification controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String userName = request.get(PasswordModification.getBodyName(PasswordModification.BodyKey.USER_NAME));
		String oldPassword = request.get(PasswordModification.getBodyName(PasswordModification.BodyKey.OLD_PASSWORD));
		String newPassword = request.get(PasswordModification.getBodyName(PasswordModification.BodyKey.NEW_PASSWORD));


		PasswordModificationDto.Builder builder = PasswordModificationDto.builder();
		PasswordModificationDto passwordModificationDto = builder.withUserName(userName)
		.withOldPassword(oldPassword)
		.withNewPassword(newPassword)
		.build();
		
		userService.changePassword(passwordModificationDto);
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

	