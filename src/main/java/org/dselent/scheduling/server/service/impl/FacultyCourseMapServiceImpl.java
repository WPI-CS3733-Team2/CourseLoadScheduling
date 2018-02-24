package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.Triple;
import org.dselent.scheduling.server.model.Calendar;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.service.FacultyCourseMapService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.LogicalOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacultyCourseMapServiceImpl implements FacultyCourseMapService
{
	
	@Autowired
	private CustomDao customDao;
	
    public FacultyCourseMapServiceImpl()
    {
    
    }

    //@Transactional
    //@Override
	public List<Triple<String, String, String>> facultyCourseMap() throws SQLException
	{
    	List<User> usersWithCourse = customDao.facultyCourseMapping();
    	List<Triple<String, String, String>> facultyCourses = new ArrayList();
    	for (User person : usersWithCourse) {
    		List<Faculty> facultyList = customDao.getFacultyIDFromUser(person.getId());
    		List<Schedule> scheduleList = customDao.searchScheduleByFaculty(facultyList.get(0).getId());
    		for (Schedule schedule : scheduleList) {
    			List<Section> sectionList = customDao.getSectionsInSchedule(schedule.getId());
    			String coursesString = new String();
    			for (Section section : sectionList) {
    				List<Calendar> calendarList = customDao.getCalendarsOfSection(section.getId());
    				List<Course> courseList = customDao.getCoursesOfSection(section.getId());
    				coursesString += (courseList.get(0).getNumber() + " " + calendarList.get(0).getSemester() + " " + section.getName() + ", ");
    			}
    			Triple<String, String, String> triple = new Triple(person.getFirstName(), person.getLastName(), coursesString);
				facultyCourses.add(triple);
    		}
    	}
    	return facultyCourses;
	}
    
}
