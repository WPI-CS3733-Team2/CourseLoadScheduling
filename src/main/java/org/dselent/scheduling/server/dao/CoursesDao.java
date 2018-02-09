package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesDao extends Dao<Course>
{
	public Course findByNumber(String number) throws SQLException;
	public Course findByName(String name) throws SQLException;
	public Course findByFrequency(Integer frequency) throws SQLException;
	public Integer updateColumns(List<String> columnName, List<Object> newValue, List<QueryTerm> queryTermList);
}
