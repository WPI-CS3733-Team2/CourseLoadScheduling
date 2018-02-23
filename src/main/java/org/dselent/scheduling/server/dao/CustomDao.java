package org.dselent.scheduling.server.dao;

import java.util.List;

import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.custom.UserInfo;
import org.dselent.scheduling.server.exceptions.InvalidUserNameException;
import org.dselent.scheduling.server.model.Calendar;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Faculty;
import org.springframework.stereotype.Repository;

/**
 * Interface for all daos for custom queries.
 * 
 * @author dselent
 *
 */
@Repository
public interface CustomDao
{
	// custom queries here
	public List<UserInfo> getAllUsersWithRole(int roleId);
	public UserInfo getLoginInfo(String userName) throws InvalidUserNameException;
	public List<Schedule> searchScheduleByFaculty(int faculty_id);
	public List<Faculty> getFacultiesWithUserName(String userName);
	public List<Faculty> getFacultiesWithUserEmail(String userEmail);
	public List<Calendar> getCalendarsInSchedule(int schedule_id);
	public List<Faculty> getFacultiesTeachingACourse(int course_id);
	public List<Calendar> getCalendarsOfACourse(int course_id);
	public List<Calendar> getCalendarsOfAFaculty(int faculty_id);
	public List<Faculty> getFacultiesWithUserWPIID(String wpi_id);
	public List<Calendar> getMatchDateCalendar(int year, String semester, String days, String start_time, String end_time);
	public List<User> facultyCourseMapping();
	public List<Calendar> getCalendarsOfSection(int sectionId);
	public List<Course> getCoursesOfSchedule(int scheduleId);
	public List<Section> getSectionsInSchedule(int scheduleId);
	public List<Faculty> getFacultyIDFromUser(int userId);
	public List<Course> getCoursesOfSection(int sectionId);
	public List<Faculty> getFacultyIDFromUserSearch(String searchTerm);
	public List<Schedule> getScheduleFromCourseSearch(String searchTerm);
	public List<Schedule> getScheduleFromName(String searchTerm);
	public List<Schedule> getScheduleFromCalendarSearch(String searchTerm);
}
