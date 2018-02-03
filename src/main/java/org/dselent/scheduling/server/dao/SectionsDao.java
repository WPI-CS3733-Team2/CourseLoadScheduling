package org.dselent.scheduling.server.dao;

import java.sql.SQLException;

import org.dselent.scheduling.server.model.Section;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionsDao extends Dao<Section>
{
	public Section findByCrn(int crn) throws SQLException;
	public Section findByName(String name) throws SQLException;
	public Section findByType(String type) throws SQLException;
	public Section findByPopulation(int population) throws SQLException;
	public Section findByCourseId(int courseId) throws SQLException;
	public Section findByScheduleId(int scheduleId) throws SQLException;
	
}
