package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.dao.UsersRolesLinksDao;
import org.dselent.scheduling.server.dto.LoginUserDto;
import org.dselent.scheduling.server.dto.PasswordModificationDto;
import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.dto.UserSearchDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.UsersRolesLink;
import org.dselent.scheduling.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.LogicalOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;


@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private UsersRolesLinksDao usersRolesLinksDao;
	
    public UserServiceImpl()
    {
    	//
    }
    
    /*
     * (non-Javadoc)
     * @see org.dselent.scheduling.server.service.UserService#registerUser(org.dselent.scheduling.server.dto.RegisterUserDto)
     */
    @Transactional
    @Override
	public List<Integer> registerUser(RegisterUserDto dto) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();
		
		// TODO validate business constraints
			// ^^students should do this^^
			// password strength requirements
			// email requirements
			// null values
			// etc...
		
		String salt = KeyGenerators.string().generateKey();
		String saltedPassword = dto.getPassword() + salt;
		PasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncorder.encode(saltedPassword);
		
		User user = new User();
		user.setWpiId(dto.getWPIid());
		user.setUserName(dto.getUserName());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setEncryptedPassword(encryptedPassword);
		user.setSalt(salt);
		user.setAccountState("Active");										//
    	
    	List<String> userInsertColumnNameList = new ArrayList<>();
    	List<String> userKeyHolderColumnNameList = new ArrayList<>();
    	
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.WPI_ID));		//
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.USER_NAME));
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.FIRST_NAME));
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.LAST_NAME));
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.EMAIL));
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.ENCRYPTED_PASSWORD));
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.SALT));
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.ACCOUNT_STATE)); //
    	userInsertColumnNameList.add(User.getColumnName(User.Columns.DELETED));		//
    	
    	userKeyHolderColumnNameList.add(User.getColumnName(User.Columns.ID));
    	userKeyHolderColumnNameList.add(User.getColumnName(User.Columns.CREATED_AT));
    	userKeyHolderColumnNameList.add(User.getColumnName(User.Columns.UPDATED_AT));
		
    	rowsAffectedList.add(usersDao.insert(user, userInsertColumnNameList, userKeyHolderColumnNameList));

		//
     	
    	// for now, assume users can only register with default role id
    	// may change in the future
    	
		UsersRolesLink usersRolesLink = new UsersRolesLink();
		usersRolesLink.setUserId(user.getId());
		usersRolesLink.setRoleId(1); // hard coded as regular user
    	
    	List<String> usersRolesLinksInsertColumnNameList = new ArrayList<>();
    	List<String> usersRolesLinksKeyHolderColumnNameList = new ArrayList<>();
    	
    	usersRolesLinksInsertColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.USER_ID));
    	usersRolesLinksInsertColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.ROLE_ID));
    	
    	usersRolesLinksKeyHolderColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.ID));
    	usersRolesLinksKeyHolderColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.CREATED_AT));
    	usersRolesLinksKeyHolderColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.DELETED));
		
    	rowsAffectedList.add(usersRolesLinksDao.insert(usersRolesLink, usersRolesLinksInsertColumnNameList, usersRolesLinksKeyHolderColumnNameList));
		
		return rowsAffectedList;
	}
	
    
    
    // LoginUserDto --> Boolean
    /*TODO: The login function should do this:
	 * Find the user by User name.
	 * If the userName doesn't exist, return false;
	 * If the userName can be found, then check if the password is matched
	 * If yes, return true; otherwise, return false.
	 */
    @Transactional
	@Override
	public boolean loginUser(LoginUserDto dto) throws SQLException
	{
		
		// extract the matched user data from the input userName
		String input_userName = dto.getUserName();
		String input_Password = dto.getPassword();
		User selectedUser = null;
		selectedUser = usersDao.findByUserName(input_userName);
		
		if(selectedUser == null)
		{
			// debugging message
			System.out.println("The username does not exist.");
			return false;
		}
		else if (input_Password == selectedUser.getEncryptedPassword()){
			return true;
		} 
		else 
		{
			// debugging message
			System.out.println("The password is wrong.");
			return false;
		}
	}
    
    
    // LoginUserDto --> int
    /*TODO: The changePassword function should do this:
	 * Find the user by user name.
	 * If the userName doesn't exist, return -1 and report error;
	 * 		If the userName can be found, then check if the input old password is matched with existed old password
	 * 			If yes, replace the new password with existed new password and return rowsAffected. 
	 * 			If not, return -1 and report error.
	 */
    @Transactional
	@Override
	public int changePassword(PasswordModificationDto dto) throws SQLException
	{
		// extract the matched user data from the input userName
		String input_userName = dto.getUserName();
		String input_OldPassword = dto.getOldPassword();
		String input_NewPassword = dto.getNewPassword();
		User selectedUser = null;
		selectedUser = usersDao.findByUserName(input_userName);
		
		if(selectedUser == null)
		{
			// debugging message
			System.out.println("The username does not exist.");
			return -1;
		}
		else if (input_OldPassword != selectedUser.getEncryptedPassword()){
			// debugging message
			System.out.println("The old password is wrong.");
			return -1;
		} 
		else 
		{
			//pack the query terms first (only one query term)
			List<QueryTerm> queryTermList = new ArrayList<>();
			String queryColumnName = User.getColumnName(User.Columns.USER_NAME);
			QueryTerm userNameTerm = new QueryTerm (queryColumnName, ComparisonOperator.EQUAL, input_userName, null);
			queryTermList.add(userNameTerm);
			
			// Update the new password.
			String updateColumnName = User.getColumnName(User.Columns.ENCRYPTED_PASSWORD);
			int rowsAffected = usersDao.update(updateColumnName, input_NewPassword, queryTermList);
			
			return rowsAffected;
		}
	}
    
 // LoginUserDto --> List<User>
    /*TODO: The searchUser function should do this:
	 * 1. Read the input: wpiID, userName, firstName, lastName, and email;
	 * 2. pack the columnNameList: allColumnNameList;
	 * 3. pack the input variables to queryTermList;
	 * 4. pack the orderByList using sorting order: <LastName, Asc>
	 */
    @Transactional
	@Override
	public List<User> searchUser(UserSearchDto dto) throws SQLException
	{
		// 1. extract the matched user data from the input userName
		String input_WpiId = dto.getWpiId();
		String input_userName = dto.getUserName();
		String input_firstName = dto.getFirstName();
		String input_lastName = dto.getLastName();
		String input_email = dto.getEmail();
		
		// 2. create a columnName list and pack columnName data
		List<String> columnNamesList = new ArrayList<>();
		columnNamesList.addAll(User.getColumnNameList());
		
		// 3. create a query terms list and pack the query terms data
		List<QueryTerm> queryTermList = new ArrayList<>();
		
		/* startingFlag is used to mark the first queryTerm with "Not Null" value. When one queryTerm is the first one to add
		 * into queryTermList, then its logicalOperator (default in OR) should be set to null.
		 */
		boolean startingQueryFlag = true;
		if (input_WpiId != null) {
			String queryColumnName1 = User.getColumnName(User.Columns.WPI_ID);
			QueryTerm queryTerm1 = new QueryTerm (queryColumnName1, ComparisonOperator.EQUAL, input_WpiId, LogicalOperator.OR);
			if (startingQueryFlag == true) {
				queryTerm1.setLogicalOperator(null);
				startingQueryFlag = false;
			}
			queryTermList.add(queryTerm1);
		}
		if (input_userName != null) {
			String queryColumnName2 = User.getColumnName(User.Columns.USER_NAME);
			QueryTerm queryTerm2 = new QueryTerm (queryColumnName2, ComparisonOperator.EQUAL, input_userName, LogicalOperator.OR);
			if (startingQueryFlag == true) {
				queryTerm2.setLogicalOperator(null);
				startingQueryFlag = false;
			}
			queryTermList.add(queryTerm2);
		}
		if (input_firstName != null) {
			String queryColumnName3 = User.getColumnName(User.Columns.FIRST_NAME);
			QueryTerm queryTerm3 = new QueryTerm (queryColumnName3, ComparisonOperator.EQUAL, input_firstName, LogicalOperator.OR);
			if (startingQueryFlag == true) {
				queryTerm3.setLogicalOperator(null);
				startingQueryFlag = false;
			}
			queryTermList.add(queryTerm3);

		}
		if (input_lastName != null) {
			String queryColumnName4 = User.getColumnName(User.Columns.LAST_NAME);
			QueryTerm queryTerm4 = new QueryTerm (queryColumnName4, ComparisonOperator.EQUAL, input_lastName, LogicalOperator.OR);
			if (startingQueryFlag == true) {
				queryTerm4.setLogicalOperator(null);
				startingQueryFlag = false;
			}
			queryTermList.add(queryTerm4);

		}
		if (input_email != null) {
			String queryColumnName5 = User.getColumnName(User.Columns.EMAIL);
			QueryTerm queryTerm5 = new QueryTerm (queryColumnName5, ComparisonOperator.EQUAL, input_email, LogicalOperator.OR);
			if (startingQueryFlag == true) {
				queryTerm5.setLogicalOperator(null);
				startingQueryFlag = false;
			}
			queryTermList.add(queryTerm5);

		}
		
		// 4. create a sort list and pack sorting data
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> orderBy1 = new Pair(User.Columns.FIRST_NAME, ColumnOrder.ASC);
		orderByList.add(orderBy1);
		
		// return the selected users.
		List<User> selectedUsers = usersDao.select(columnNamesList, queryTermList, orderByList);
		return selectedUsers;
		
	}

}
