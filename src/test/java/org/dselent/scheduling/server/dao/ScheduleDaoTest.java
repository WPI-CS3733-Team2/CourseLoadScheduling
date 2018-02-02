package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class ScheduleDaoTest {

	@Autowired
	private ScheduleDao scheduleDap;
	
	@Test
	public void testScheduleDao() throws SQLException{
		Schedule schedule1 = new Schedule();
		schedule1.setScheduleName("testSchedule");
		schedule1.setFacultyId(1);
		
		List<String> insertColumnNameList = new ArrayList<>();
		List<String> keyHolderColumnNameList = new ArrayList<>();
		
		insertColumnNameList.add(Schedule.getColumnName(Schedule.Columns.FACULTY_ID));
		insertColumnNameList.add(Schedule.getColumnName(Schedule.Columns.SCHEDULE_NAME));
		
		
		keyHolderColumnNameList.add(Schedule.getColumnName(Schedule.Columns.ID));
		keyHolderColumnNameList.add(Schedule.getColumnName(Schedule.Columns.CREATED_AT));
		
		scheduleDap.insert(schedule1, insertColumnNameList, keyHolderColumnNameList);
		
	}
}
