package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Faculty;
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
@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
public class FacultyDaoTest {

	@Autowired
	private FacultyDao facultyDao;

	@Test
	public void testFacultyDao() throws SQLException {
		 Faculty fac1 = new Faculty();
		 fac1.setRank(999);
		 fac1.setAssigned(false);
		
		 List<String> insertColumnNameList = new ArrayList<>();
		 List<String> keyHolderColumnNameList = new ArrayList<>();
		
		 insertColumnNameList.add(Faculty.getColumnName(Faculty.Columns.RANK));
		 insertColumnNameList.add(Faculty.getColumnName(Faculty.Columns.ASSIGNED));
		
		 keyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.ID));
		 keyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.DELETED));
		 keyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.CREATED_AT));
		 System.out.println(facultyDao.insert(fac1, insertColumnNameList, keyHolderColumnNameList));

		// String selectColumnName = Faculty.getColumnName(Faculty.Columns.RANK);
		// Integer selectRank = 999;
		//
		// List<QueryTerm> selectQueryTermList = new ArrayList<>();
		//
		// QueryTerm selectUseNameTerm = new QueryTerm();
		// selectUseNameTerm.setColumnName(selectColumnName);
		// selectUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		// selectUseNameTerm.setValue(selectRank);
		// selectQueryTermList.add(selectUseNameTerm);
		//
		// List<String> selectColumnNameList = Faculty.getColumnNameList();
		//
		// List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		// Pair<String, ColumnOrder> orderPair1 = new Pair<String,
		// ColumnOrder>(selectColumnName, ColumnOrder.ASC);
		// orderByList.add(orderPair1);
		//
		// @SuppressWarnings("unused")
		// List<Faculty> selectedFList = facultyDao.select(selectColumnNameList,
		// selectQueryTermList, orderByList);
		//
		// System.out.println(selectedFList);
		String updateColumnName = Faculty.getColumnName(Faculty.Columns.RANK);
		Integer oldRank = 999;
		Integer newRank = 0;
		List<QueryTerm> updateQueryTermList = new ArrayList<>();

		QueryTerm updateUseNameTerm = new QueryTerm();
		updateUseNameTerm.setColumnName(updateColumnName);
		updateUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
		updateUseNameTerm.setValue(oldRank);
		updateQueryTermList.add(updateUseNameTerm);

		facultyDao.update(updateColumnName, newRank, updateQueryTermList);
	}
}
