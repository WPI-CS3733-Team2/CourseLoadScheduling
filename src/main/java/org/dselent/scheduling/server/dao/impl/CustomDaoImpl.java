package org.dselent.scheduling.server.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.exceptions.InvalidUserIdException;
import org.dselent.scheduling.server.exceptions.InvalidUserNameException;
import org.dselent.scheduling.server.extractor.ScheduleExtractor;
import org.dselent.scheduling.server.extractor.SectionsExtractor;
import org.dselent.scheduling.server.extractor.UsersExtractor;
import org.dselent.scheduling.server.extractor.UsersInfoExtractor;
import org.dselent.scheduling.server.extractor.CalendarExtractor;
import org.dselent.scheduling.server.extractor.CoursesExtractor;
import org.dselent.scheduling.server.extractor.FacultyExtractor;
import org.dselent.scheduling.server.miscellaneous.QueryPathConstants;
import org.dselent.scheduling.server.model.Calendar;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.custom.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CustomDaoImpl implements CustomDao
{
	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// can make custom models and extractors as needed or reuse existing ones if applicable
	
	@Override
	public List<UserInfo> getAllUsersWithRole(int roleId)
	{
		UsersInfoExtractor extractor = new UsersInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.USERS_WITH_ROLE_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("roleId", roleId);
	    List<UserInfo> usersWithRoleList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return usersWithRoleList;
	}
	
	@Override
	public UserInfo getLoginInfo(String userName) throws InvalidUserNameException
	{
		UsersInfoExtractor extractor = new UsersInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.LOGIN_USER_INFO_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("userName", userName);
	    List<UserInfo> usersInfoList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    
	    if(usersInfoList.isEmpty())
	    {
	    	throw new InvalidUserNameException(userName, "Invalid userName: \"" + "\"" + userName + "\"");
	    }
	    
	    return usersInfoList.get(0);
	}
	
	@Override
	public UserInfo getUserInfo(Integer userId) throws InvalidUserIdException
	{
		UsersInfoExtractor extractor = new UsersInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.USER_INFO_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("userId", userId);
	    List<UserInfo> usersInfoList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    
	    if(usersInfoList.isEmpty())
	    {
	    	throw new InvalidUserIdException(userId, "Invalid userId: \"" + "\"" + userId + "\"");
	    }
	    
	    return usersInfoList.get(0);
	}

	@Override
	public List<Schedule> searchScheduleByFaculty(int faculty_id) {
		ScheduleExtractor extractor = new ScheduleExtractor();
		String queryTemplate = new String(QueryPathConstants.SCHEDULE_BY_FACULTY_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("facultyId", faculty_id);
		List<Schedule> scheduleByFacultyList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return scheduleByFacultyList;
	}
	
	@Override
	public List<Faculty> getFacultiesWithUserName(String userName){
		FacultyExtractor extractor = new FacultyExtractor();
		String queryTemplate = new String(QueryPathConstants.FACULTIES_WITH_USER_NAME_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userName", userName);
		List<Faculty> facultiesWithUserNameList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return facultiesWithUserNameList;
	}
	
	@Override
	public List<Faculty> getFacultiesWithUserEmail(String userEmail){
		FacultyExtractor extractor = new FacultyExtractor();
		String queryTemplate = new String(QueryPathConstants.FACULTIES_WITH_USER_EMAIL_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("email", userEmail);
		List<Faculty> facultiesWithUserEmailList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return facultiesWithUserEmailList;
	}
	
	@Override
	public List<Calendar> getCalendarsInSchedule(int schedule_id) {
		CalendarExtractor extractor = new CalendarExtractor();
		String queryTemplate = new String(QueryPathConstants.CALENDARS_IN_SCHEDULE_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", schedule_id);
		List<Calendar> calendarsInScheduleList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return calendarsInScheduleList;
	}
	
	@Override
	public List<Faculty> getFacultiesTeachingACourse(int course_id) {
		FacultyExtractor extractor = new FacultyExtractor();
		String queryTemplate = new String(QueryPathConstants.FACULTIES_TEACHING_A_COURSE_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("courseId", course_id);
		List<Faculty> facultiesTeachingACourseList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return facultiesTeachingACourseList;
	}

	@Override
	public List<Calendar> getCalendarsOfACourse(int course_id) {
		CalendarExtractor extractor = new CalendarExtractor();
		String queryTemplate = new String(QueryPathConstants.CALENDAR_OF_COURSE_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("courseId", course_id);
	    List<Calendar> calendarsOfACourseList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return calendarsOfACourseList;
	}

	@Override
	public List<Calendar> getCalendarsOfAFaculty(int faculty_id) {
		CalendarExtractor extractor = new CalendarExtractor();
		String queryTemplate = new String(QueryPathConstants.CALENDAR_OF_FACULTY_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("facultyId", faculty_id);
	    List<Calendar> calendarsOfAFacultyList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return calendarsOfAFacultyList;
	}
	
	@Override
	public List<Faculty> getFacultiesWithUserWPIID(String wpi_id) {
		FacultyExtractor extractor = new FacultyExtractor();
		String queryTemplate = new String(QueryPathConstants.FACULTIES_WITH_USER_WPI_ID_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("wpiId", wpi_id);
	    List<Faculty> facultiesWithUserWPIIDList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return facultiesWithUserWPIIDList;
	}
	
	@Override
	public List<Calendar> getMatchDateCalendar(int year, String semester, String days, String start_time, String end_time){
		CalendarExtractor extractor = new CalendarExtractor();
		String queryTemplate = new String(QueryPathConstants.MATCH_DATE_CALENDAR_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("year", year);
	    parameters.addValue("semester", semester);
	    parameters.addValue("days", days);
	    parameters.addValue("start_time", start_time);
	    parameters.addValue("end_time", end_time);
	    List<Calendar> calendarMatchList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    
	    return calendarMatchList;
	}
	
	@Override
	public List<User> facultyCourseMapping(){
		UsersExtractor extractor = new UsersExtractor();
		String queryTemplate = new String(QueryPathConstants.FACULTY_COURSE_MAPPING_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    List<User> usersWithCoursesList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return usersWithCoursesList;
	}
	
	@Override
	public List<Calendar> getCalendarsOfSection(int sectionId){
		CalendarExtractor extractor = new CalendarExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_CALENDARS_OF_SECTION_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("sectionId", sectionId);
	    List<Calendar> calendarMatchList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return calendarMatchList;
	}
	
	@Override
	public List<Course> getCoursesOfSchedule(int scheduleId){
		CoursesExtractor extractor = new CoursesExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_COURSES_OF_SCHEDULE_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("scheduleId", scheduleId);
	    List<Course> courseMatchList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return courseMatchList;
	}
	
	@Override
	public List<Section> getSectionsInSchedule(int scheduleId){
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_SECTIONS_IN_SCHEDULE_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("scheduleId", scheduleId);
	    List<Section> sectionMatchList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return sectionMatchList;
	}
	
	@Override
	public List<Faculty> getFacultyIDFromUser(int userId){
		FacultyExtractor extractor = new FacultyExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_FACULTY_ID_FROM_USER_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("usersId", userId);
	    List<Faculty> facultiesWithUserIDList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return facultiesWithUserIDList;
	}
	
	@Override
	public List<Course> getCoursesOfSection(int sectionId){
		CoursesExtractor extractor = new CoursesExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_COURSES_OF_SECTION_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("id", sectionId);
	    List<Course> courseMatchList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return courseMatchList;
	}
	
	@Override
	public List<Faculty> getFacultyIDFromUserSearch(String searchTerm){
		FacultyExtractor extractor = new FacultyExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_FACULTY_ID_FROM_USER_SEARCH_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("searchTerm", searchTerm);
	    List<Faculty> facultiesWithUserIDList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return facultiesWithUserIDList;
	}
	
	@Override
	public List<Schedule> getScheduleFromCourseSearch(String searchTerm){
		ScheduleExtractor extractor = new ScheduleExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_SCHEDULE_FROM_COURSE_SEARCH_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("searchTerm", searchTerm);
		List<Schedule> scheduleByCourseList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return scheduleByCourseList;
	}
	
	@Override
	public List<Schedule> getScheduleFromName(String searchTerm){
		ScheduleExtractor extractor = new ScheduleExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_SCHEDULE_FROM_NAME_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("searchTerm", searchTerm);
		List<Schedule> scheduleByNameList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return scheduleByNameList;
	}
	
	@Override
	public List<Schedule> getScheduleFromCalendarSearch(String searchTerm){
		ScheduleExtractor extractor = new ScheduleExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_SCHEDULE_FROM_CALENDAR_SEARCH_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("searchTerm", searchTerm);
		List<Schedule> scheduleByCourseList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return scheduleByCourseList;
	}
	
	@Override
	public List<User> getUserForSchedule(int scheduleId){
		UsersExtractor extractor = new UsersExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_USER_FOR_SCHEDULE_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("scheduleId", scheduleId);
	    List<User> usersWithSchedule = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return usersWithSchedule;
	}
	
	@Override
	public List<Pair<User, Integer>> getUnassignedFacultyUser(){
		List<Pair<User, Integer>> facultyNoCourse = new ArrayList<Pair<User, Integer>>();
		UsersExtractor extractor = new UsersExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_UNASSIGNED_FACULTY_USER_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    List<User> usersWithNoCourses = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    for (User user : usersWithNoCourses) {
	    	List<Faculty> faculty = getFacultyIDFromUser(user.getId());
	    	Pair<User, Integer> pair = new Pair<User,Integer>(user, faculty.get(0).getId());
	    	facultyNoCourse.add(pair);
	    }
	    
	    return facultyNoCourse;
	}
	
	@Override
	public List<Section> getSectionsFromCourseSearch(String searchTerm){
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_SECTIONS_FROM_COURSE_SEARCH_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("searchTerm", searchTerm);
	    List<Section> sectionMatchList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return sectionMatchList;
	}
	
	@Override
	public List<User> getUsersByFacultyIds(){
		UsersExtractor extractor = new UsersExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_USERS_BY_FACULTY_IDS_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    List<User> selectedUserList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return selectedUserList;
	}
	
	@Override
	public List<UserInfo> getUserFromSearch(String searchTerm){
		UsersInfoExtractor extractor = new UsersInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.GET_USER_FROM_SEARCH_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("searchTerm", searchTerm);
		List<UserInfo> selectedUserList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return selectedUserList;
	}
}
