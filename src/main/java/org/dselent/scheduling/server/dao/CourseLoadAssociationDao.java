package org.dselent.scheduling.server.dao;

import java.sql.SQLException;

import org.dselent.scheduling.server.model.CourseLoadAssociation;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseLoadAssociationDao extends Dao<CourseLoadAssociation>
{
	public CourseLoadAssociation findByFacultyId(int faculty_id) throws SQLException;
	public CourseLoadAssociation findByCourseLoadId(int courseLoad_id) throws SQLException;
}
