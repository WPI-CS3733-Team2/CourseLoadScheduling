package org.dselent.scheduling.server.dao;

import java.sql.SQLException;

import org.dselent.scheduling.server.model.Schedule;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleDao extends Dao<Schedule>
{
	public Schedule findByFacultyId(int faculty_id) throws SQLException;
	public Schedule findByName(String name) throws SQLException;
	public Schedule findByFaculty(int facultyId) throws SQLException;
}
