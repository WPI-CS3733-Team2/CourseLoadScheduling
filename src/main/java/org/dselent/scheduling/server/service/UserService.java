package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.dto.LoginUserDto;
import org.dselent.scheduling.server.dto.PasswordModificationDto;
import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.dto.UserSearchDto;
import org.dselent.scheduling.server.model.User;
import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 * 
 * @author dselent
 *
 */
@Service
public interface UserService
{
	public List<Integer> registerUser(RegisterUserDto registerUserDto) throws SQLException;
	/**
	 * Registers a user into the system
	 * Performs an insert into the users table and users_roles_links table as a transaction
	 * 
	 * @param registerUserDto DTO container information for the insertions
	 * @return A list of rows affected for each insert operation
	 * @throws SQLException
	 */

	public List<Integer> deleteUser(Integer id) throws SQLException;
	
    public boolean loginUser(LoginUserDto loginUserDto) throws SQLException;
	/**
	 * login user into the system
	 * Check if the password matches the existing one in a certain user
	 * @param loginUserDto DTO container userName and password.
	 * @return Boolean value showing login successfully or not.
	 * @throws SQLException
	 */
    
    public int changePassword(PasswordModificationDto dto) throws SQLException;
	/**
	 * check if the old password match existing one under certain user, 
	 * and update the new password to old one in the database
	 * @param PasswordModificationDto DTO container information for userName, oldPassword and newPassword
	 * @return the number of the row affected for update operation
	 * @throws SQLException
	 */
    
    public List<User> searchUser(UserSearchDto dto) throws SQLException;
    /**
	 * search the input variables: wpiID, userName, firstName, lastName and email,
	 * and return the selected list of user matching those values of input variables.
	 * @param UserSearchDto DTO container information for wpiID, userName, firstName, lastName and email
	 * @return the list of user selected for select operation
	 * @throws SQLException
	 */
}
