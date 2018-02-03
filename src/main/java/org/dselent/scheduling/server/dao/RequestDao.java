package org.dselent.scheduling.server.dao;
import java.sql.SQLException;

import org.dselent.scheduling.server.model.Request;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDao extends Dao<Request>
{
	public Request findByFacultyId(int facultyId) throws SQLException;
	public Request findByType(int type) throws SQLException;
	public Request findByState(int state) throws SQLException;
	public Request findByCourseId(int courseId) throws SQLException;
	public Request findBySectionId(int sectionId) throws SQLException;
}





