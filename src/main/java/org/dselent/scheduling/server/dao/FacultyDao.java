package org.dselent.scheduling.server.dao;

import java.sql.SQLException;

import org.dselent.scheduling.server.model.Faculty;
import org.springframework.stereotype.Repository;


@Repository
public interface FacultyDao extends Dao<Faculty> {
	public Faculty findByRank(int rank) throws SQLException;
	public Faculty findByIfAssigned(Boolean ifAssigned) throws SQLException;
	public Faculty findByIfDeleted(Boolean ifDeleted) throws SQLException;
}
