package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.model.Schedule;
import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 * 
 * @author dselent
 *
 */
@Service
public interface ScheduleService
{
	public Schedule create(Integer facultyId, String scheduleName) throws SQLException;
	public List<Schedule> view(Integer id) throws SQLException;
	public Schedule findWithFacultyId(Integer facultyId) throws SQLException;
}
