package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CourseLoad;
//import org.dselent.scheduling.server.model.CourseLoadAssociation;
//import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
//import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 * 
 * @author dselent
 *
 */
@Service
public interface CourseLoadService
{
	public List<CourseLoad> searchCourseLoad(int facultyId) throws SQLException;
	public Integer addCourseLoad(CourseLoad courseLoad) throws SQLException;
	
	public Integer addCourseLoadAssociation(int courseLoadId, int facultyId) throws SQLException;
	
	public Integer deleteCourseLoad(int facultyId) throws SQLException;
	
	public Integer deleteCourseLoadAssociation(int facultyId) throws SQLException;
	
	//public CourseLoadAssociation findByFacultyId()
}
