package org.dselent.scheduling.server.dao.impl;

import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.extractor.ScheduleExtractor;
import org.dselent.scheduling.server.extractor.UsersExtractor;
import org.dselent.scheduling.server.extractor.CalendarExtractor;
import org.dselent.scheduling.server.extractor.FacultyExtractor;
import org.dselent.scheduling.server.miscellaneous.QueryPathConstants;
import org.dselent.scheduling.server.model.Calendar;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.User;
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
	public List<User> getAllUsersWithRole(int roleId)
	{
		UsersExtractor extractor = new UsersExtractor();
		String queryTemplate = new String(QueryPathConstants.USERS_WITH_ROLE_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("roleId", roleId);
	    List<User> usersWithRoleList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return usersWithRoleList;
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
	
}
