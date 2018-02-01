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
	private static String CALENDARS_IN_SCHEDULE_PATH = BASE_QUERY_PATH + "GetCalendarsInSchedule" + SQL_EXTENSION;
	private static String FACULTIES_TEACHING_A_COURSE_PATH = BASE_QUERY_PATH + "GetFacultiesTeachingACourse" + SQL_EXTENSION;


	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	

	public static String USERS_WITH_ROLE_QUERY = readFile(USERS_WITH_ROLE_PATH);
	public static String SCHEDULE_BY_FACULTY_QUERY = readFile(SCHEDULE_BY_FACULTY_PATH);
	public static String FACULTIES_WITH_USER_NAME_QUERY = readFile(FACULTIES_WITH_USER_NAME_PATH);
	public static String CALENDARS_IN_SCHEDULE_QUERY = readFile(CALENDARS_IN_SCHEDULE_PATH);
	public static String FACULTIES_TEACHING_A_COURSE_QUERY = readFile(FACULTIES_TEACHING_A_COURSE_PATH);

	
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
