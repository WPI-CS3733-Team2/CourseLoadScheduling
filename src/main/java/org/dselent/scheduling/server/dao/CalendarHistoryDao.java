package org.dselent.scheduling.server.dao;

import java.sql.SQLException;

import org.dselent.scheduling.server.model.CalendarHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarHistoryDao extends Dao<CalendarHistory>
{
	public CalendarHistory findByCalendarId(int calendar_id) throws SQLException;
}