package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CourseLoadAssociationDao;
import org.dselent.scheduling.server.dao.CourseLoadDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.CourseLoad;
import org.dselent.scheduling.server.model.CourseLoadAssociation;
import org.dselent.scheduling.server.service.CourseLoadService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseLoadServiceImpl implements CourseLoadService {
	@Autowired
	private CourseLoadDao courseLoadDao;
	
	@Autowired
	private CourseLoadAssociationDao claDao;

	public CourseLoadServiceImpl() {
		//
	}

	@Override
	public List<CourseLoad> searchCourseLoad(int facultyId) throws SQLException {
		CourseLoadAssociation cla = claDao.findByFacultyId(facultyId);
		int courseLoadId = cla.getCourseLoadId();
		
		String selectColumnName = CourseLoad.getColumnName(CourseLoad.Columns.ID);
		//String selectUserName = "???????????";

		List<QueryTerm> selectQueryTermList = new ArrayList<>();

		QueryTerm selectCLATerm = new QueryTerm();
		selectCLATerm.setColumnName(selectColumnName);
		selectCLATerm.setComparisonOperator(ComparisonOperator.EQUAL);
		selectCLATerm.setValue(courseLoadId);
		selectQueryTermList.add(selectCLATerm);

		List<String> selectColumnNameList = CourseLoad.getColumnNameList();

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
		orderByList.add(orderPair1);

		@SuppressWarnings("unused")
		List<CourseLoad> selectedCourseLoadList = courseLoadDao.select(selectColumnNameList, selectQueryTermList, orderByList);
		
		return selectedCourseLoadList;
	}

}
