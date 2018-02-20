package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.dto.CreateCourseDto;
import org.dselent.scheduling.server.dto.ModifyCourseDto;
import org.dselent.scheduling.server.dto.SearchCourseDto;
import org.dselent.scheduling.server.httpReturnObject.UserFaculty;
import org.dselent.scheduling.server.model.Course;
import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 * 
 * @author dselent
 *
 */
@Service
public interface CourseService
{
	public Course createCourse(CreateCourseDto createCourseDto) throws SQLException;
	public Integer modifyCourse(ModifyCourseDto modifyCourseDto) throws SQLException;
	public List<Course> searchCourse(SearchCourseDto searchCourseDto) throws SQLException;
	public int deleteCourse(String id) throws SQLException;
	public List<UserFaculty> getCourseFaculties(String courseId) throws SQLException;

}
