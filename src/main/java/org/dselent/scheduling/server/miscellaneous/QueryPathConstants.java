package org.dselent.scheduling.server.miscellaneous;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Register all custom SQL query files here
 * 
 * @author dselent
 *
 */
public class QueryPathConstants
{
	private static String BASE_QUERY_PATH = "sql" + File.separator + "Active" + File.separator;
	private static String SQL_EXTENSION = ".sql";

	private static String USERS_WITH_ROLE_PATH = BASE_QUERY_PATH + "CustomUsersWithRole" + SQL_EXTENSION;
	private static String SCHEDULE_BY_FACULTY_PATH = BASE_QUERY_PATH + "SearchScheduleByFaculty" + SQL_EXTENSION;
	private static String FACULTIES_WITH_USER_NAME_PATH = BASE_QUERY_PATH + "GetFacultiesWithUserName" + SQL_EXTENSION;
	private static String FACULTIES_WITH_USER_EMAIL_PATH = BASE_QUERY_PATH + "GetFacultiesWithUserEmail" + SQL_EXTENSION;
	private static String CALENDARS_IN_SCHEDULE_PATH = BASE_QUERY_PATH + "GetCalendarsInSchedule" + SQL_EXTENSION;
	private static String FACULTIES_TEACHING_A_COURSE_PATH = BASE_QUERY_PATH + "GetFacultiesTeachingACourse" + SQL_EXTENSION;
	private static String CALENDAR_OF_COURSE_PATH = BASE_QUERY_PATH + "GetCalendarsOfACourse" + SQL_EXTENSION;
	private static String CALENDAR_OF_FACULTY_PATH = BASE_QUERY_PATH + "GetCalendarsOfAFaculty" + SQL_EXTENSION;
	private static String FACULTIES_WITH_USER_WPI_ID_PATH = BASE_QUERY_PATH + "GetFacultiesWithUserWPIID" + SQL_EXTENSION;
	private static String MATCH_DATE_CALENDAR_PATH = BASE_QUERY_PATH + "GetMatchDateCalendar" + SQL_EXTENSION;
	private static String FACULTY_COURSE_MAPPING_PATH = BASE_QUERY_PATH + "FacultyCourseMapping" + SQL_EXTENSION;
	private static String GET_CALENDARS_OF_SECTION_PATH = BASE_QUERY_PATH + "GetCalendarsOfSection" + SQL_EXTENSION;
	private static String GET_COURSES_OF_SCHEDULE_PATH = BASE_QUERY_PATH + "GetCoursesOfSchedule" + SQL_EXTENSION;
	private static String GET_SECTIONS_IN_SCHEDULE_PATH = BASE_QUERY_PATH + "GetSectionsInSchedule" + SQL_EXTENSION;
	private static String GET_FACULTY_ID_FROM_USER_PATH = BASE_QUERY_PATH + "GetFacultyIDFromUser" + SQL_EXTENSION;
	private static String GET_COURSES_OF_SECTION_PATH = BASE_QUERY_PATH + "GetCourseOfSection" + SQL_EXTENSION;
	private static String GET_FACULTY_ID_FROM_USER_SEARCH_PATH = BASE_QUERY_PATH + "GetFacultyIDFromUserSearch" + SQL_EXTENSION;
	private static String GET_SCHEDULE_FROM_COURSE_SEARCH_PATH = BASE_QUERY_PATH + "GetScheduleFromCourseSearch" + SQL_EXTENSION;
	private static String GET_SCHEDULE_FROM_NAME_PATH = BASE_QUERY_PATH + "GetScheduleFromName" + SQL_EXTENSION;
	private static String GET_SCHEDULE_FROM_CALENDAR_SEARCH_PATH = BASE_QUERY_PATH + "GetScheduleFromCalendarSearch" + SQL_EXTENSION;
	private static String GET_USER_FOR_SCHEDULE_PATH = BASE_QUERY_PATH + "GetUserForSchedule" + SQL_EXTENSION;


	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	

	public static String USERS_WITH_ROLE_QUERY = readFile(USERS_WITH_ROLE_PATH);
	public static String SCHEDULE_BY_FACULTY_QUERY = readFile(SCHEDULE_BY_FACULTY_PATH);
	public static String FACULTIES_WITH_USER_NAME_QUERY = readFile(FACULTIES_WITH_USER_NAME_PATH);
	public static String FACULTIES_WITH_USER_EMAIL_QUERY = readFile(FACULTIES_WITH_USER_EMAIL_PATH);
	public static String CALENDARS_IN_SCHEDULE_QUERY = readFile(CALENDARS_IN_SCHEDULE_PATH);
	public static String FACULTIES_TEACHING_A_COURSE_QUERY = readFile(FACULTIES_TEACHING_A_COURSE_PATH);
	public static String CALENDAR_OF_COURSE_QUERY = readFile(CALENDAR_OF_COURSE_PATH);
	public static String CALENDAR_OF_FACULTY_QUERY = readFile(CALENDAR_OF_FACULTY_PATH);
	public static String FACULTIES_WITH_USER_WPI_ID_QUERY = readFile(FACULTIES_WITH_USER_WPI_ID_PATH);
	public static String MATCH_DATE_CALENDAR_QUERY = readFile(MATCH_DATE_CALENDAR_PATH);
	public static String FACULTY_COURSE_MAPPING_QUERY = readFile(FACULTY_COURSE_MAPPING_PATH);
	public static String GET_CALENDARS_OF_SECTION_QUERY = readFile(GET_CALENDARS_OF_SECTION_PATH);
	public static String GET_COURSES_OF_SCHEDULE_QUERY = readFile(GET_COURSES_OF_SCHEDULE_PATH);
	public static String GET_SECTIONS_IN_SCHEDULE_QUERY = readFile(GET_SECTIONS_IN_SCHEDULE_PATH);
	public static String GET_FACULTY_ID_FROM_USER_QUERY = readFile(GET_FACULTY_ID_FROM_USER_PATH);
	public static String GET_COURSES_OF_SECTION_QUERY = readFile(GET_COURSES_OF_SECTION_PATH);
	public static String GET_FACULTY_ID_FROM_USER_SEARCH_QUERY = readFile(GET_FACULTY_ID_FROM_USER_SEARCH_PATH);
	public static String GET_SCHEDULE_FROM_COURSE_SEARCH_QUERY = readFile(GET_SCHEDULE_FROM_COURSE_SEARCH_PATH);
	public static String GET_SCHEDULE_FROM_NAME_QUERY = readFile(GET_SCHEDULE_FROM_NAME_PATH);
	public static String GET_SCHEDULE_FROM_CALENDAR_SEARCH_QUERY = readFile(GET_SCHEDULE_FROM_CALENDAR_SEARCH_PATH);
	public static String GET_USER_FOR_SCHEDULE_QUERY = readFile(GET_USER_FOR_SCHEDULE_PATH);
	

	
	private QueryPathConstants()
	{
		
	}
	
	public static String readFile(String path)
	{
		byte[] encodedbytes = null;
		
		try
		{
			Resource resource = new ClassPathResource(path);
			encodedbytes = Files.readAllBytes(Paths.get(resource.getURI()));
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		return new String(encodedbytes);
	}

}
