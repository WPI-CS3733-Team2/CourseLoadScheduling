package org.dselent.scheduling.server.dao;

import java.sql.SQLException;

import org.dselent.scheduling.server.model.UsersHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersHistoryDao extends Dao<UsersHistory> {
	public UsersHistory findByUserId(int uid) throws SQLException;
}
