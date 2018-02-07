package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CourseLoadAssociationDao;
import org.dselent.scheduling.server.dao.CourseLoadDao;
import org.dselent.scheduling.server.dao.FacultyDao;
import org.dselent.scheduling.server.dao.UserFacultyAssociationDao;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.dao.UsersRolesLinksDao;
import org.dselent.scheduling.server.dto.PasswordModificationDto;
import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.model.CourseLoad;
import org.dselent.scheduling.server.model.CourseLoadAssociation;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.dto.UserSearchDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.UsersRolesLink;
import org.dselent.scheduling.server.service.UserService;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.dselent.scheduling.server.model.UserFacultyAssociation;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.LogicalOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;


@Service
public class UserServiceImpl implements UserService {
	public static final int FACULTY = 1;
	public static final int ADMIN = 0;
	
	@Autowired
	private UsersDao usersDao;

	@Autowired
	private UsersRolesLinksDao usersRolesLinksDao;
	
	@Autowired
	private UserFacultyAssociationDao userFacultyAssociationDao;
	
	@Autowired
	private FacultyDao facultyDao;
	
	@Autowired
	private CourseLoadDao courseLoadDao;
	
	@Autowired
	private CourseLoadAssociationDao courseLoadAssociationDao;

	public UserServiceImpl() {
		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dselent.scheduling.server.service.UserService#registerUser(org.dselent.
	 * scheduling.server.dto.RegisterUserDto)
	 */
	@Transactional
	@Override
	public List<Integer> registerUser(RegisterUserDto dto) throws SQLException {
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
		user.setAccountState("Active"); //

		List<String> userInsertColumnNameList = new ArrayList<>();
		List<String> userKeyHolderColumnNameList = new ArrayList<>();

		userInsertColumnNameList.add(User.getColumnName(User.Columns.WPI_ID)); //
		userInsertColumnNameList.add(User.getColumnName(User.Columns.USER_NAME));
		userInsertColumnNameList.add(User.getColumnName(User.Columns.FIRST_NAME));
		userInsertColumnNameList.add(User.getColumnName(User.Columns.LAST_NAME));
		userInsertColumnNameList.add(User.getColumnName(User.Columns.EMAIL));
		userInsertColumnNameList.add(User.getColumnName(User.Columns.ENCRYPTED_PASSWORD));
		userInsertColumnNameList.add(User.getColumnName(User.Columns.SALT));
		userInsertColumnNameList.add(User.getColumnName(User.Columns.ACCOUNT_STATE)); //
		userInsertColumnNameList.add(User.getColumnName(User.Columns.DELETED)); //

		userKeyHolderColumnNameList.add(User.getColumnName(User.Columns.ID));
		userKeyHolderColumnNameList.add(User.getColumnName(User.Columns.CREATED_AT));
		userKeyHolderColumnNameList.add(User.getColumnName(User.Columns.UPDATED_AT));

		rowsAffectedList.add(usersDao.insert(user, userInsertColumnNameList, userKeyHolderColumnNameList));
		//int userId = user.getId();


		UsersRolesLink usersRolesLink = new UsersRolesLink();
		usersRolesLink.setUserId(user.getId());
		usersRolesLink.setRoleId(dto.getRoleId()); // hard coded as regular user

		List<String> usersRolesLinksInsertColumnNameList = new ArrayList<>();
		List<String> usersRolesLinksKeyHolderColumnNameList = new ArrayList<>();

		usersRolesLinksInsertColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.USER_ID));
		usersRolesLinksInsertColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.ROLE_ID));

		usersRolesLinksKeyHolderColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.ID));
		usersRolesLinksKeyHolderColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.CREATED_AT));
		usersRolesLinksKeyHolderColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.DELETED));

		
		rowsAffectedList.add(usersRolesLinksDao.insert(usersRolesLink, usersRolesLinksInsertColumnNameList,
				usersRolesLinksKeyHolderColumnNameList));
		
		
		
		
		//If role is faculty, add faculty&association
		if(dto.getRoleId() == 0) {
			
			Faculty faculty = new Faculty();
			faculty.setRank(dto.getRank());
			rowsAffectedList.add( addFaculty(faculty));
			
			rowsAffectedList.add(addUFA(faculty.getId(), user.getId()));
			
			CourseLoad courseLoad  = new CourseLoad();
			rowsAffectedList.add(addCourseLoad(courseLoad));
			rowsAffectedList.add(addCourseLoadAssociation(courseLoad.getId(), faculty.getId()));
		}

		return rowsAffectedList;
	}

	
    
    
    // LoginUserDto --> Boolean
    /*TODO: The login function should do this:
	 * Find the user by User name.
	 * If the userName doesn't exist, return false;
	 * If the userName can be found, then check if the password is matched
	 * If yes, return true; otherwise, return false.
	 */

	@Override
	public boolean loginUser(String input_userName, String input_password) throws SQLException
	{
		
		// extract the matched user data from the input userName
		User selectedUser = null;
		selectedUser = usersDao.findByUserName(input_userName);
		
		if(selectedUser == null)
		{
			// debugging message
			System.out.println("The username does not exist.");
			return false;
		}
		else if (input_password == selectedUser.getEncryptedPassword()){
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
	 * Find the user by id in user table.
	 * If the id doesn't exist, return -1 and report error;
	 * 		If the userName can be found, then check if the input old password is matched with existed old password
	 * 			If yes, replace the new password with existed new password and return rowsAffected. 
	 * 			If not, return -1 and report error.
	 */
    @Transactional
	@Override
	public int changePassword(String input_id, String input_OldPassword, String input_NewPassword) throws SQLException
	{

		User selectedUser = usersDao.findById(Integer.parseInt(input_id));
		
		if(selectedUser == null)
		{
			// debugging message
			throw new SQLException("user ID is wrong.");
		}
		else if (input_OldPassword != selectedUser.getEncryptedPassword()){
			// debugging message
			throw new SQLException("The old password is wrong.");
		} 
		else 
		{
			//pack the query terms first (only one query term)
			List<QueryTerm> queryTermList = new ArrayList<>();
			String queryColumnName = User.getColumnName(User.Columns.ID);
			QueryTerm IdTerm = new QueryTerm (queryColumnName, ComparisonOperator.EQUAL, input_id, null);
			queryTermList.add(IdTerm);
			
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

	@Override
	public List<Integer> deleteUser(Integer id) throws SQLException {
		List<Integer> rowsAffectedList = new ArrayList<>();
		
		String updateColumnName = User.getColumnName(User.Columns.DELETED);
		List<QueryTerm> updateQueryTermList = new ArrayList<>();

		QueryTerm updateDeletedTerm = new QueryTerm();
		updateDeletedTerm.setColumnName(User.getColumnName(User.Columns.ID));
		updateDeletedTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		updateDeletedTerm.setValue(id);
		updateQueryTermList.add(updateDeletedTerm);

		rowsAffectedList.add(usersDao.update(updateColumnName, true, updateQueryTermList));  //delete from users table
		rowsAffectedList.add(deleteRoleLink(id));//delete from faculty table
		UserFacultyAssociation ufa =userFacultyAssociationDao.findByUserId(id);
		if(ufa != null) {
			rowsAffectedList.add(deleteFaculty(ufa.getFacultyId()));
			
			CourseLoadAssociation cla = courseLoadAssociationDao.findByFacultyId(ufa.getFacultyId());
			int courseLoadId = cla.getCourseLoadId();
			rowsAffectedList.add(deleteCourseLoadAssociation(ufa.getFacultyId()));
			rowsAffectedList.add(deleteCourseLoad(courseLoadId));
			
		}

		return rowsAffectedList;
	}
	
	private Integer deleteCourseLoad(int courseLoadId) throws SQLException{
		String updateColumnName = CourseLoad.getColumnName(CourseLoad.Columns.DELETED);
		
		List<QueryTerm> updateQueryTermList = new ArrayList<>();
		QueryTerm updateDeletedTerm = new QueryTerm();
		
		updateDeletedTerm.setColumnName(CourseLoad.getColumnName(CourseLoad.Columns.ID));
		updateDeletedTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		updateDeletedTerm.setValue(courseLoadId);
		updateQueryTermList.add(updateDeletedTerm);

		int result = usersRolesLinksDao.update(updateColumnName, true, updateQueryTermList);
		return result;
	}
	
	private Integer deleteCourseLoadAssociation(int facultyId) throws SQLException{
		String updateColumnName = CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.DELETED);
		
		List<QueryTerm> updateQueryTermList = new ArrayList<>();
		QueryTerm updateDeletedTerm = new QueryTerm();
		
		updateDeletedTerm.setColumnName(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.FACULTY_ID));
		updateDeletedTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		updateDeletedTerm.setValue(facultyId);
		updateQueryTermList.add(updateDeletedTerm);

		int result = usersRolesLinksDao.update(updateColumnName, true, updateQueryTermList);
		return result;
	}
	
	private Integer addFaculty(Faculty faculty) throws SQLException{
		
		
		List<String> facultyInsertColumnNameList = new ArrayList<>();
		List<String> facultyKeyHolderColumnNameList = new ArrayList<>();
		
		facultyInsertColumnNameList.add(Faculty.getColumnName(Faculty.Columns.RANK));
		
		facultyKeyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.ID));
		facultyKeyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.ASSIGNED));
		facultyKeyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.DELETED));
		facultyKeyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.CREATED_AT));
		
		return facultyDao.insert(faculty, facultyInsertColumnNameList, facultyKeyHolderColumnNameList);
		//return facultyId;
	}


	private Integer addUFA(int facultyId, int userId) throws SQLException{
		UserFacultyAssociation ufa = new UserFacultyAssociation();
		ufa.setFacultyId(facultyId);
		ufa.setUserId(userId);
		
		List<String> ufaInsertColumnNameList = new ArrayList<>();
		List<String> ufaKeyHolderColumnNameList = new ArrayList<>();
		
		ufaInsertColumnNameList.add(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.FACULTY_ID));
		ufaInsertColumnNameList.add(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.USER_ID));
		
		ufaKeyHolderColumnNameList.add(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.ID));
		ufaKeyHolderColumnNameList.add(UserFacultyAssociation.getColumnName(UserFacultyAssociation.Columns.CREATED_AT));
		return userFacultyAssociationDao.insert(ufa, ufaInsertColumnNameList, ufaKeyHolderColumnNameList);
	}
	
	private Integer deleteRoleLink(Integer userId) throws SQLException{
		String updateColumnName = UsersRolesLink.getColumnName(UsersRolesLink.Columns.DELETED);
		
		List<QueryTerm> updateQueryTermList = new ArrayList<>();
		QueryTerm updateDeletedTerm = new QueryTerm();
		
		updateDeletedTerm.setColumnName(UsersRolesLink.getColumnName(UsersRolesLink.Columns.USER_ID));
		updateDeletedTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		updateDeletedTerm.setValue(userId);
		updateQueryTermList.add(updateDeletedTerm);

		int result = usersRolesLinksDao.update(updateColumnName, true, updateQueryTermList);
		return result;
	}
	
	private Integer deleteFaculty(Integer facultyId) throws SQLException{
		String updateColumnName = Faculty.getColumnName(Faculty.Columns.DELETED);

		List<QueryTerm> updateQueryTermList = new ArrayList<>();
		QueryTerm updateDeletedTerm = new QueryTerm();

		updateDeletedTerm.setColumnName(Faculty.getColumnName(Faculty.Columns.ID));
		updateDeletedTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		updateDeletedTerm.setValue(facultyId);
		updateQueryTermList.add(updateDeletedTerm);

		return facultyDao.update(updateColumnName, true, updateQueryTermList);
	}

	private Integer addCourseLoad(CourseLoad courseLoad) throws SQLException{
		courseLoad.setAmount(0);
		
		List<String> insertColumnNameList = new ArrayList<>();
		List<String> keyHolderColumnNameList = new ArrayList<>();
		
		insertColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.AMOUNT));
		
		keyHolderColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.ID));
		keyHolderColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.TYPE));
		keyHolderColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.DELETED));
		keyHolderColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.CREATED_AT));
		
		return courseLoadDao.insert(courseLoad, insertColumnNameList, keyHolderColumnNameList);
		
	}
	
	private Integer addCourseLoadAssociation(int courseLoadId, int facultyId) throws SQLException{
		CourseLoadAssociation cla = new CourseLoadAssociation();
		cla.setCourseLoadId(courseLoadId);
		cla.setFacultyId(facultyId);
		
		List<String> insertColumnNameList = new ArrayList<>();
		List<String> keyHolderColumnNameList = new ArrayList<>();
		
		insertColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.COURSE_LOAD_ID));
		insertColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.FACULTY_ID));
		
		keyHolderColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.ID));
		keyHolderColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.DELETED));
		keyHolderColumnNameList.add(CourseLoadAssociation.getColumnName(CourseLoadAssociation.Columns.CREATED_AT));
		
		return courseLoadAssociationDao.insert(cla, insertColumnNameList, keyHolderColumnNameList);
	}


}
