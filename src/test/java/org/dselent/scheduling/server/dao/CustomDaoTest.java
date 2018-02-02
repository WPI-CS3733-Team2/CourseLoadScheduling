package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.dao.impl.CustomDaoImpl;
import org.dselent.scheduling.server.model.Calendar;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class CustomDaoTest
{
	@Autowired
	private CustomDao customDao;
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
    @Test
    public void testCustomDao() throws SQLException
    {
    	//@SuppressWarnings("unused")
    	
		//List<User> userList = customDao.getAllUsersWithRole(1);
    	//List<Schedule> scheduleList = customDao.searchScheduleByFaculty(1);
    	//List<Calendar> calendarList = customDao.getCalendarsInSchedule(3);
    	//List<Calendar> calendarList = customDao.getCalendarsOfAFaculty(1);
    	//List<Calendar> calendarList = customDao.getCalendarsOfAFaculty(2);
    	//List<Faculty> facultyListInOneCourse = customDao.getFacultiesTeachingACourse(2);
    	//List<Faculty> facultyListWPIID = customDao.getFacultiesWithUserWPIID("2");
    	List<Faculty> facultyList = customDao.getFacultiesWithUserEmail("dselent@wpi.edu");
    	// see things in debugger
    	System.out.println(facultyList);
    }
}
