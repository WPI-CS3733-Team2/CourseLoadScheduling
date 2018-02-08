package org.dselent.scheduling.server.dao;

import java.util.List;

import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.Calendar;
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
	
	public List<User> getAllUsersWithRole(int roleId);
	public List<Schedule> searchScheduleByFaculty(int faculty_id);
	public List<Faculty> getFacultiesWithUserName(String userName);
	public List<Faculty> getFacultiesWithUserEmail(String userEmail);
	public List<Calendar> getCalendarsInSchedule(int schedule_id);
	public List<Faculty> getFacultiesTeachingACourse(int course_id);
	public List<Calendar> getCalendarsOfACourse(int course_id);
	public List<Calendar> getCalendarsOfAFaculty(int faculty_id);
	public List<Faculty> getFacultiesWithUserWPIID(String wpi_id);
	public List<Calendar> getMatchDateCalendar(int year, String semester, String days, String start_time, String end_time);
}
