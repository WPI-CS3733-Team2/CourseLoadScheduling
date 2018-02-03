package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.dao.CalendarDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Calendar;
import org.dselent.scheduling.server.model.CourseHistory;
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
@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
public class CoursesHistoryDaoTest {
	@Autowired
	private CoursesHistoryDao coursesHistoryDao;

	/*
	 * Not really an using this as a JUnit test More of an example on how to use the
	 * classes
	 */
	@Test
	public void CoursesHistoryDao() throws SQLException {
		String selectColumnName = CourseHistory.getColumnName(CourseHistory.Columns.COURSE_ID);
		Integer selectCourseId = 1;

		List<QueryTerm> selectQueryTermList = new ArrayList<>();

		QueryTerm selectUseNameTerm = new QueryTerm();
		selectUseNameTerm.setColumnName(selectColumnName);
		selectUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		selectUseNameTerm.setValue(selectCourseId);
		selectQueryTermList.add(selectUseNameTerm);

		List<String> selectColumnNameList = CourseHistory.getColumnNameList();

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
		orderByList.add(orderPair1);

		@SuppressWarnings("unused")
		List<CourseHistory> selectedUserList = coursesHistoryDao.select(selectColumnNameList, selectQueryTermList, orderByList);

		System.out.println(selectedUserList);
	}

}
