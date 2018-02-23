package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.model.Schedule;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleDao extends Dao<Schedule>
{
	public Schedule findByFacultyId(int faculty_id) throws SQLException;
	public List<Schedule> findByName(String name) throws SQLException;
	public List<Schedule> getAll() throws SQLException;
}
