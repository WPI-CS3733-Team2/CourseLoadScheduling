package org.dselent.scheduling.server.dao;

import java.sql.SQLException;

import org.dselent.scheduling.server.model.UsersRolesLink;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRolesLinksDao extends Dao<UsersRolesLink>
{
	public UsersRolesLink findByUserId(int uid) throws SQLException;
	public UsersRolesLink findByRoleId(int rid) throws SQLException;
	public UsersRolesLink findByIfDeleted(Boolean ifDeleted) throws SQLException;
}
