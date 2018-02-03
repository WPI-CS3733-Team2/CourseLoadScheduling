package org.dselent.scheduling.server.dao;

import java.sql.SQLException;

import org.dselent.scheduling.server.model.UserFacultyAssociation;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFacultyAssociationDao extends Dao<UserFacultyAssociation> {
	public UserFacultyAssociation findByFacultyId(int fid) throws SQLException;
	public UserFacultyAssociation findByUserId(int uid) throws SQLException;
}
