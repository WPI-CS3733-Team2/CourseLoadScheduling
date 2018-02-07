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
import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.model.CourseLoad;
import org.dselent.scheduling.server.model.CourseLoadAssociation;
import org.dselent.scheduling.server.model.Faculty;
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
import org.dselent.scheduling.server.model.UserFacultyAssociation;;



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

		int userId = usersRolesLinksDao.insert(usersRolesLink, usersRolesLinksInsertColumnNameList,
				usersRolesLinksKeyHolderColumnNameList);
		rowsAffectedList.add(userId);
		
		
		//If role is faculty, add faculty&association
		if(dto.getRoleId() == 0) {
			int facultyId = addFaculty(dto.getRank());
			rowsAffectedList.add(facultyId);
			rowsAffectedList.add(addUFA(facultyId, userId));
			int courseLoadId = addCourseLoad();
			rowsAffectedList.add(courseLoadId);
			rowsAffectedList.add(addCourseLoadAssociation(courseLoadId, facultyId));
		}

		return rowsAffectedList;
	}


	@Override
	public User loginUser(String userName, String password)
	{
		// TODO Auto-generated method stub
		return null;
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
		rowsAffectedList.add(deleteFaculty(id)); //delete from usersRolesLinks table
		rowsAffectedList.add(deleteRoleLink(id));//delete from faculty table

		return rowsAffectedList;
	}
	
	private Integer addFaculty(int rank) throws SQLException{
		Faculty faculty = new Faculty();
		faculty.setRank(rank);
		
		List<String> facultyInsertColumnNameList = new ArrayList<>();
		List<String> facultyKeyHolderColumnNameList = new ArrayList<>();
		
		facultyInsertColumnNameList.add(Faculty.getColumnName(Faculty.Columns.RANK));
		
		facultyKeyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.ID));
		facultyKeyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.ASSIGNED));
		facultyKeyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.DELETED));
		facultyKeyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.CREATED_AT));
		
		int facultyId = facultyDao.insert(faculty, facultyInsertColumnNameList, facultyKeyHolderColumnNameList);
		return facultyId;
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
	
	private Integer deleteFaculty(Integer userId) throws SQLException{
		UserFacultyAssociation ufa =userFacultyAssociationDao.findByUserId(userId);
		int facultyId = ufa.getFacultyId();
		
		String updateColumnName = Faculty.getColumnName(Faculty.Columns.DELETED);
		
		List<QueryTerm> updateQueryTermList = new ArrayList<>();
		QueryTerm updateDeletedTerm = new QueryTerm();
		
		updateDeletedTerm.setColumnName(Faculty.getColumnName(Faculty.Columns.ID));
		updateDeletedTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		updateDeletedTerm.setValue(facultyId);
		updateQueryTermList.add(updateDeletedTerm);

		int result = facultyDao.update(updateColumnName, true, updateQueryTermList);
		return result;
	}

	private Integer addCourseLoad() throws SQLException{
		CourseLoad courseLoad = new CourseLoad();
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
