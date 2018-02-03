package org.dselent.scheduling.server.dao;

import java.sql.SQLException;

import org.dselent.scheduling.server.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends Dao<User>
{
	public User findByWpiId(String wpiid) throws SQLException;
	public User findByUserName(String userName) throws SQLException;
	public User findByFirstName(String firstName) throws SQLException;
	public User findByLastName(String lastName) throws SQLException;
	public User findByEmail(String email) throws SQLException;
	public User findByState(String state) throws SQLException;
	public User findByIfDeleted(Boolean ifDeleted) throws SQLException;
}
