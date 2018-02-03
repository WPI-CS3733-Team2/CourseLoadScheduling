package org.dselent.scheduling.server.dao;

import java.sql.SQLException;

import org.dselent.scheduling.server.model.CourseLoad;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseLoadDao extends Dao<CourseLoad>
{
	public CourseLoad findBytype(String type) throws SQLException;
	public CourseLoad findByAmount(Integer amount) throws SQLException;
}
