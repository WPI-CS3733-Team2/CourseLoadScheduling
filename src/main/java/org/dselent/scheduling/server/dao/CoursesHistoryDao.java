package org.dselent.scheduling.server.dao;

import java.sql.SQLException;

import org.dselent.scheduling.server.model.CourseHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesHistoryDao extends Dao<CourseHistory>
{
	public CourseHistory findByCourseId(int course_id) throws SQLException;
}
