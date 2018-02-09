package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
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
	public Section findById(int id) throws SQLException;
	public Integer updateColumns(List<String> columnName, List<Object> newValue, List<QueryTerm> queryTermList);
}
